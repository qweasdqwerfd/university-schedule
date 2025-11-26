package com.example.universityschedule.presentation.screens.search

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.universityschedule.presentation.util.UniversityScheduleTheme
import com.example.universityschedule.presentation.util.dimens

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


    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val groupsList = searchViewModel.groupsList.value.map { it.name }


    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()

        searchViewModel.getGroups()
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
//            UniversalTextField(
//                value = textState,
//                onValueChange = { textState = it },
//                label = "ПВ-242/Алексей",
//                placeholder = "Номер группы или имя преподавателя",
//                modifier = Modifier.focusRequester(focusRequester)
//            )

            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                placeholder = {
                    Text(
                        "Номер группы или имя преподавателя"
                    )
                },
                query = textState,
                onQueryChange = { textState = it },
                onSearch = {

                },
                active = false,
                onActiveChange = {

                }

            ) {}

            LazyColumn {
                items(groupsList) {
                    Text(it)
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