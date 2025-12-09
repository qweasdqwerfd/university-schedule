package com.example.universityschedule.presentation.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.universityschedule.domain.model.GroupEntity
import com.example.universityschedule.presentation.util.UniversityScheduleTheme
import com.example.universityschedule.presentation.util.dimens
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScheduleScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
    titleColor: Color = MaterialTheme.colorScheme.onSurface,
    textMediumStyle: TextStyle = MaterialTheme.typography.labelSmall,
    textMediumColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
    space: Dp = MaterialTheme.dimens.space16
) {
    var textState by remember { mutableStateOf("") }
    val recentSelections = remember { mutableStateListOf<GroupEntity>() }

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val groupsList = searchViewModel.groupsList.value.collectAsState(initial = emptyList()).value
    val debouncedQuery by produceState(initialValue = "") {
        snapshotFlow { textState }
            .map { it.trim() }
            .debounce(220)
            .distinctUntilChanged()
            .collect { value = it }
    }

    var active by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
        searchViewModel.getGroups()
    }

    val filtered = remember(debouncedQuery, groupsList) {
        val normalized = debouncedQuery.lowercase()
        if (normalized.isEmpty()) {
            groupsList.take(20)
        } else {
            groupsList.filter { group ->
                group.name.lowercase().contains(normalized)
            }
        }
    }

    fun selectGroup(item: GroupEntity) {
        textState = item.name
        focusManager.clearFocus()
        keyboardController?.hide()
        active = false
        if (recentSelections.none { it.id == item.id }) {
            recentSelections.add(0, item)
            if (recentSelections.size > 6) {
                recentSelections.removeAt(recentSelections.lastIndex)
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.dimens.space16)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(vertical = MaterialTheme.dimens.space10)
                .fillMaxWidth(),
        ) {
            Spacer(Modifier.height(space))
            Text("Поиск расписания", style = titleStyle, color = titleColor)
            Spacer(Modifier.height(space))
            Text(
                "Введите номер группы или имя преподавателя и мы загрузим выбранное расписание на устройство",
                style = textMediumStyle,
                color = textMediumColor
            )
            Spacer(Modifier.height(space))

            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                query = textState,
                onQueryChange = { textState = it },
                onSearch = {
                    filtered.firstOrNull()?.let(::selectGroup)
                        ?: recentSelections.firstOrNull()?.let(::selectGroup)
                },
                active = active,
                onActiveChange = { active = it },
                placeholder = {
                    Text("Номер группы или имя преподавателя")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                trailingIcon = {
                    if (textState.isNotEmpty()) {
                        IconButton(onClick = {
                            textState = ""
                            focusRequester.requestFocus()
                        }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Очистить")
                        }
                    }
                }
            ) {
                when {
                    groupsList.isEmpty() -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Ищем группы…", style = MaterialTheme.typography.bodyMedium)
                        }
                    }

                    filtered.isEmpty() && debouncedQuery.isNotEmpty() -> {
                        EmptySearchState(query = debouncedQuery)
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 360.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            if (recentSelections.isNotEmpty() && debouncedQuery.isBlank()) {
                                item { SectionTitle("Недавний выбор") }
                                items(recentSelections) { item ->
                                    SearchResultRow(
                                        item = item,
                                        query = debouncedQuery,
                                        onClick = { selectGroup(item) }
                                    )
                                }
                                item {
                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                            }

                            if (filtered.isNotEmpty()) {
                                item { SectionTitle("Подходящие результаты") }
                                items(filtered) { item ->
                                    SearchResultRow(
                                        item = item,
                                        query = debouncedQuery,
                                        onClick = { selectGroup(item) }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Найдены ${filtered.size} / ${groupsList.size} групп",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    )
}

@Composable
private fun SearchResultRow(
    item: GroupEntity,
    query: String,
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        tonalElevation = 1.dp,
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = highlightQuery(
                    text = item.name,
                    query = query,
                    highlightColor = MaterialTheme.colorScheme.primary
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                item.course?.let {
                    AssistChip(
                        onClick = onClick,
                        label = { Text("Курс $it") },
                        colors = AssistChipDefaults.assistChipColors(
                            leadingIconContentColor = MaterialTheme.colorScheme.primary
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    )
                }
                item.studentCount?.let {
                    AssistChip(
                        onClick = onClick,
                        label = { Text("$it студентов") },
                        colors = AssistChipDefaults.assistChipColors()
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptySearchState(query: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        tonalElevation = 0.dp,
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Нет результатов",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold)
            )
            Text(
                text = "Мы не нашли групп по запросу \"$query\". Попробуйте сократить номер или выбрать из списка ниже.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

private fun highlightQuery(text: String, query: String, highlightColor: Color): AnnotatedString {
    if (query.isBlank()) return AnnotatedString(text)
    val lower = text.lowercase()
    val idx = lower.indexOf(query.lowercase())
    if (idx < 0) return AnnotatedString(text)

    return buildAnnotatedString {
        append(text.substring(0, idx))
        pushStyle(SpanStyle(fontWeight = FontWeight.Bold, color = highlightColor))
        append(text.substring(idx, idx + query.length))
        pop()
        append(text.substring(idx + query.length))
    }
}

@Preview
@Composable
private fun PreviewSearchScheduleScreen() {
    UniversityScheduleTheme(darkTheme = true) {
        SearchScheduleScreen()
    }
}