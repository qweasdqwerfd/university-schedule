package com.example.universityschedule.presentation.screens.tasks

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import com.example.universityschedule.presentation.common.components.UniversalTextField
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.format.DateTimeFormatter
import java.util.*

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.view.ContextThemeWrapper
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import java.text.SimpleDateFormat
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateTimePickerField(
    value: LocalDateTime?,
    onValueChange: (LocalDateTime) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Установленный срок",
    placeholder: String = "Выберите дату и время"
) {
    val context = LocalContext.current

    val displayText = remember(value) {
        value?.format(
            DateTimeFormatter.ofPattern("EEE, d MMM, HH:mm", Locale("ru"))
        )?.replaceFirstChar { it.titlecase(Locale("ru")) } ?: ""
    }

    Box(modifier = modifier) {
        UniversalTextField(
            value = displayText,
            onValueChange = {},
            label = label,
            placeholder = placeholder,
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable {
                    val fragmentActivity = context.findFragmentActivityOrNull()
                    if (fragmentActivity != null) {
                        showMaterialDateThenTime(fragmentActivity, onValueChange)
                    } else {
                        showNativeDateThenTime(context, onValueChange)
                    }
                }
        )
    }
}



/** Вспомогательная: пробуем получить FragmentActivity из context безопасно */
private fun Context.findFragmentActivityOrNull(): FragmentActivity? {
    var ctx = this
    // если это ContextWrapper — оборачиваем до Activity
    while (ctx is android.content.ContextWrapper) {
        if (ctx is FragmentActivity) return ctx
        val base = ctx.baseContext ?: break
        if (base === ctx) break
        ctx = base
    }
    return null
}

/** MaterialDatePicker -> затем TimePickerDialog */
@RequiresApi(Build.VERSION_CODES.O)
fun showMaterialDateThenTime(
    activity: FragmentActivity,
    onPicked: (LocalDateTime) -> Unit
) {
    Locale.setDefault(Locale("ru", "RU"))
    val dateFormat = SimpleDateFormat("d MMM yyyy", Locale("ru", "RU"))

    val datePicker =
        MaterialDatePicker.Builder.datePicker()
            .setTitleText("Выберите дату")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

    datePicker.addOnPositiveButtonClickListener { selection ->
        val calendar = Calendar.getInstance().apply { timeInMillis = selection }
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Создаем TimePicker в стиле приложения
        val timePicker = TimePickerDialog(
            activity,
            { _, hour, minute ->
                val localDateTime = LocalDateTime.of(year, month + 1, day, hour, minute)
                onPicked(localDateTime)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )

        // Изменяем цвет кнопок, если нужно
        timePicker.setOnShowListener {
            val color = activity.getColor(com.google.android.material.R.color.m3_sys_color_light_primary)
            timePicker.getButton(DialogInterface.BUTTON_POSITIVE)?.setTextColor(color)
            timePicker.getButton(DialogInterface.BUTTON_NEGATIVE)?.setTextColor(color)
        }

        timePicker.show()
    }

    datePicker.show(activity.supportFragmentManager, "DATE_PICKER")
}

@RequiresApi(Build.VERSION_CODES.O)
fun showNativeDateThenTime(
    context: Context,
    onPicked: (LocalDateTime) -> Unit
) {
    val calendar = Calendar.getInstance()

    DatePickerDialog(
        context,
        { _, year, month, day ->
            TimePickerDialog(
                context,
                { _, hour, minute ->
                    val localDateTime = LocalDateTime.of(year, month + 1, day, hour, minute)
                    onPicked(localDateTime)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}

