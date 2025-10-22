package com.example.universityschedule.presentation.screens.lessons

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.universityschedule.R
import com.example.universityschedule.presentation.common.components.EnumChipsRow
import com.example.universityschedule.presentation.common.components.UniversalTextField
import com.example.universityschedule.presentation.screens.tasks.components.UniversalEnumChip
import com.example.universityschedule.presentation.util.dimens

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun NewLessonScreen() {

    var dialogTitle = mutableStateOf("")
    var dialogProfessor = mutableStateOf("")
    var dialogLocation = mutableStateOf("")
    var dialogStartTime = mutableStateOf("")
    var dialogEndTime = mutableStateOf("")
    var dialogDayOfWeek = mutableStateOf(DayOfWeek.Mon)
    var dialogColor = mutableStateOf(AllColors.BLUE)



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = MaterialTheme.dimens.space16,
                top = MaterialTheme.dimens.space14,
                end = MaterialTheme.dimens.space16
            ),

        ) {
        Spacer(Modifier.height(MaterialTheme.dimens.space6))
        Text(
            "Title",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        UniversalTextField(
            value = dialogTitle.value,
            onValueChange = { dialogTitle.value = it },
            label = "Enter lesson title",
            placeholder = "Title",
            maxChars = 30
        )

        Spacer(Modifier.height(MaterialTheme.dimens.heightExtraSmall))

        Text(
            "Professor",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        UniversalTextField(
            value = dialogProfessor.value,
            onValueChange = { dialogProfessor.value = it },
            label = "Enter professor name",
            placeholder = "Professor",
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.human2),
                    contentDescription = "human",
                    Modifier.size(20.dp)
                )
            },
            maxChars = 30
        )

        Spacer(Modifier.height(MaterialTheme.dimens.heightExtraSmall))

        Text(
            "Location",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        UniversalTextField(
            value = dialogLocation.value,
            onValueChange = { dialogLocation.value = it },
            label = "Enter classroom location",
            placeholder = "Location",
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.location),
                    contentDescription = "location",
                    Modifier.size(21.dp)
                )
            },
            maxChars = 30
        )

        Spacer(Modifier.height(MaterialTheme.dimens.heightExtraSmall))

        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.space20)
        ) {

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "Start Time",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                UniversalTextField(
                    value = dialogLocation.value,
                    onValueChange = { dialogLocation.value = it },
                    label = "09:00",
                    placeholder = "Enter time",
                )
            }


            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "End Time",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                UniversalTextField(
                    value = dialogLocation.value,
                    onValueChange = { dialogLocation.value = it },
                    label = "10:30",
                    placeholder = "Enter time",
                )
            }
        }

        Spacer(Modifier.height(MaterialTheme.dimens.heightExtraSmall))

        Text(
            "Day of Week",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(Modifier.height(MaterialTheme.dimens.heightExtraSmall))

        EnumChipsRow(
            selectedItem = dialogDayOfWeek.value,
            onItemSelected = { dialogDayOfWeek.value = it }
        ) { dayOfWeek, isSelected, onClick ->
            UniversalEnumChip(
                item = dayOfWeek,
                icon = null,
                colorProvider = { Color(0xFF506ea8) },
                isSelected = isSelected,
                onClick = onClick,
            )
        }

        Spacer(Modifier.height(MaterialTheme.dimens.heightExtraSmall))

        Text(
            "Color",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(Modifier.height(MaterialTheme.dimens.heightExtraSmall))

//        Row {
//            RadioButton(
//                selected = dialogColor == AllColors[dialogColor.value.color],
//                onClick = { TODO() },
//                colors = RadioButtonDefaults.colors(
//                    selectedColor =
//                )
//            )
//        }
    }
}