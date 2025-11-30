package com.example.universityschedule.presentation.screens.search

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.universityschedule.presentation.util.UniversityScheduleTheme
import com.example.universityschedule.presentation.util.dimens

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
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

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val groupsList = searchViewModel.groupsList.value.map { it.name }

    // активность SearchBar (показывать подсказки)
    var active by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
        searchViewModel.getGroups()
    }

    // Фильтрация списка: при пустом запросе — первые 10, иначе — все совпадающие по вхождению (ignoreCase)
    val filtered = remember(textState, groupsList) {
        val q = textState.trim()
        if (q.isEmpty()) {
            groupsList.take(10)
        } else {
            groupsList.filter { it.contains(q, ignoreCase = true) }
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
                    // при нажатии Enter можно выбрать первый результат (если есть)
                    filtered.firstOrNull()?.let { selected ->
                        textState = selected
                        focusManager.clearFocus()
                        keyboardController?.hide()
                        active = false
                    }
                },
                active = active,
                onActiveChange = { active = it },
                placeholder = {
                    Text("Номер группы или имя преподавателя")
                },
                trailingIcon = {
                    if (textState.isNotEmpty()) {
                        IconButton(onClick = { textState = "" }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Очистить")
                        }
                    }
                }
            ) {
                // Здесь находится выпадающая область подсказок (content у SearchBar)
                if (filtered.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Ничего не найдено", style = MaterialTheme.typography.bodyMedium)
                    }
                } else {
                    // Чипы в виде адаптивной сетки (чтобы переносились на новую строку)
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 120.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 300.dp)
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        content = {
                            items(filtered) { item ->
                                Surface(
                                    modifier = Modifier
                                        .wrapContentWidth()
                                        .heightIn(min = 40.dp)
                                        .clickable {
                                            // клик по чипу — ставим в поле и скрываем подсказки
                                            textState = item
                                            focusManager.clearFocus()
                                            keyboardController?.hide()
                                            active = false
                                        },
                                    tonalElevation = 2.dp,
                                    shape = RoundedCornerShape(20.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .padding(horizontal = 12.dp, vertical = 8.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = item,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                                        )
                                    }
                                }
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Для отладки / отображения текущего списка — можно убрать если не нужно
            Text(
                text = "Результатов: ${filtered.size}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 4.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Если нужно показать полный список (например когда SearchBar не активен), можно показать LazyColumn:
            // в текущем варианте — не обязательно, но оставлю пример, показывающий все элементы (или отфильтрованные)
            LazyColumn {
                items(filtered) { name ->
                    Text(
                        text = name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .clickable {
                                textState = name
                                focusManager.clearFocus()
                                keyboardController?.hide()
                                active = false
                            }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSearchScheduleScreen() {
    UniversityScheduleTheme(darkTheme = true) {
        SearchScheduleScreen()
    }
}