package com.example.universityschedule.presentation.screens.calendar.components.enums

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.Color
import com.example.universityschedule.R
import com.example.universityschedule.presentation.common.components.IconSpec
import com.example.universityschedule.presentation.common.dialog_controller.Displayable

enum class LessonType(override val displayName: String) : Displayable {
    Lecture("Лек"),
    Lab("Лаб"),
    Practice("Практ"),
    Consultation("Конс"),
    Exam("Экз"),
    RGZ("РГЗ"),
    IDZ("ИДЗ"),
    SupervisionOfSelfEmployment("Контроль самостоятельной работы"),
    SetOff("Зачет"),
    DifferentialSet("Диф зачет"),
    Coursework("Курс раб"),
    CourseProject("Курс проект"),
    Attestation("Аттестация"),
    InterimCertification("Промежуточная аттестация"),
    CurrentConsultation("Текущ конс")
}

fun lessonTypeData(type: LessonType): Triple<String, Any, Color> {
    return when (type) {
        LessonType.Lecture -> Triple(
            LessonType.Lecture.displayName,
            IconSpec.PainterRes(R.drawable.lessons),
            LessonColor.BLUE.color
        )

        LessonType.Practice -> Triple(
            LessonType.Practice.displayName,
            IconSpec.Vector(Icons.Default.Create),
            LessonColor.GREEN.color
        )

        LessonType.Lab -> Triple(
            LessonType.Lab.displayName,
            IconSpec.PainterRes(R.drawable.lab),
            AllColors.Purple.color
        )

        LessonType.Exam -> Triple(
            LessonType.Exam.displayName,
            IconSpec.PainterRes(R.drawable.warn),
            LessonColor.RED.color
        )

        LessonType.Consultation -> Triple(
            LessonType.Consultation.displayName,
            IconSpec.Vector(Icons.Default.Person),
            AllColors.WHITE_BLUE.color
        )

        LessonType.SetOff -> Triple(
            LessonType.SetOff.displayName,
            IconSpec.PainterRes(R.drawable.warn),
            LessonColor.RED.color
        )

        LessonType.DifferentialSet -> Triple(
            LessonType.DifferentialSet.displayName,
            IconSpec.PainterRes(R.drawable.warn),
            LessonColor.RED.color
        )

        LessonType.Coursework -> Triple(
            LessonType.Coursework.displayName,
            IconSpec.PainterRes(R.drawable.lessons),
            LessonColor.YELLOW.color
        )

        LessonType.CourseProject -> Triple(
            LessonType.CourseProject.displayName,
            IconSpec.PainterRes(R.drawable.lessons),
            LessonColor.YELLOW.color
        )


        LessonType.Attestation -> Triple(
            LessonType.Attestation.displayName,
            IconSpec.PainterRes(R.drawable.lessons),
            LessonColor.YELLOW.color
        )

        LessonType.InterimCertification -> Triple(
            LessonType.InterimCertification.displayName,
            IconSpec.PainterRes(R.drawable.lessons),
            LessonColor.YELLOW.color
        )

        LessonType.CurrentConsultation -> Triple(
            LessonType.CurrentConsultation.displayName,
            IconSpec.Vector(Icons.Default.Person),
            AllColors.WHITE_BLUE.color
        )

        LessonType.RGZ -> Triple(
            LessonType.RGZ.displayName,
            IconSpec.PainterRes(R.drawable.lessons),
            AllColors.GREEN.color
        )

        LessonType.IDZ -> Triple(
            LessonType.IDZ.displayName,
            IconSpec.PainterRes(R.drawable.lessons),
            AllColors.CYAN.color
        )

        LessonType.SupervisionOfSelfEmployment -> Triple(
            LessonType.SupervisionOfSelfEmployment.displayName,
            IconSpec.PainterRes(R.drawable.lessons),
            LessonColor.GREEN.color
        )

    }
}