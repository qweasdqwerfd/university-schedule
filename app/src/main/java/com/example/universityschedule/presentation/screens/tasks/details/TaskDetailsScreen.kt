package com.example.universityschedule.presentation.screens.tasks.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.universityschedule.R
import com.example.universityschedule.R.drawable
import com.example.universityschedule.presentation.common.components.DetailsButton
import com.example.universityschedule.presentation.screens.tasks.details.components.ModalBottomSheetDetails
import com.example.universityschedule.presentation.util.Constants.lessonsColors
import com.example.universityschedule.presentation.util.dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen(
    taskId: Int,
    viewModel: TaskDetailsViewModel,
) {




    val task by viewModel.task.collectAsState()

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    LaunchedEffect(sheetState.currentValue) {                                                //fix bug
        if (sheetState.currentValue == SheetValue.Hidden) {
            viewModel.isSheetOpen.value = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                horizontal = MaterialTheme.dimens.space20,
                vertical = MaterialTheme.dimens.space20
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = task?.title.toString(),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold
            )
            if (task?.check == true) {
                Icon(
                    painter = painterResource(R.drawable.tick2),
                    contentDescription = "",
                    modifier = Modifier
                        .size(MaterialTheme.dimens.iconSizeLargePlus),
                    tint = Color.Green
                )
            } else {

                Icon(
                    painter = painterResource(R.drawable.circle),
                    contentDescription = "",
                    modifier = Modifier
                        .size(MaterialTheme.dimens.iconSizeLarge)

                )
            }
        }
        Box(
            modifier = Modifier
                .padding(top = MaterialTheme.dimens.space4)
                .background(
                    color = Color(0xFFf7c602),
                    shape = RoundedCornerShape(MaterialTheme.dimens.cornerLarge)
                )
                .padding(
                    horizontal = MaterialTheme.dimens.space10,
                    vertical = MaterialTheme.dimens.space8
                ),

            ) {
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = MaterialTheme.dimens.space4),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(drawable.warn),
                    contentDescription = "warning",
                    modifier = Modifier
                        .size(MaterialTheme.dimens.iconSizeSmall),
                    tint = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.width(MaterialTheme.dimens.space8))

                Text(
                    text = "${task?.priority} Priority",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.dimens.space28),
            shape = RoundedCornerShape(MaterialTheme.dimens.cornerLarge),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            border = BorderStroke(
                MaterialTheme.dimens.widthOne,
                MaterialTheme.colorScheme.outlineVariant
            )
        ) {

            Column(
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.dimens.space16,
                        vertical = MaterialTheme.dimens.space14
                    ),
            ) {
                Row {
                    Icon(
                        painter = painterResource(drawable.calendar),
                        contentDescription = "calendar",
                        Modifier
                            .size(MaterialTheme.dimens.iconSizeSmall)
                            .align(Alignment.CenterVertically),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(Modifier.width(MaterialTheme.dimens.widthSmall))
                    Text(
                        text = "Due Date",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                Spacer(Modifier.height(MaterialTheme.dimens.heightSmall))

                Text(
                    text = task?.dueDate.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.dimens.space28),
            shape = RoundedCornerShape(MaterialTheme.dimens.cornerLarge),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            border = BorderStroke(
                MaterialTheme.dimens.widthOne,
                MaterialTheme.colorScheme.outlineVariant
            )
        ) {

            Column(
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.dimens.space16,
                        vertical = MaterialTheme.dimens.space14
                    ),
            ) {
                Row {
                    Icon(
                        painter = painterResource(drawable.lessons),
                        contentDescription = "calendar",
                        Modifier
                            .size(MaterialTheme.dimens.iconSizeSmall)
                            .align(Alignment.CenterVertically),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(Modifier.width(MaterialTheme.dimens.widthSmall))
                    Text(
                        text = "Related Lesson",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                Spacer(Modifier.height(MaterialTheme.dimens.heightSmall))

                Row {

                    VerticalDivider(
                        modifier = Modifier
                            .height(MaterialTheme.dimens.heightLarge)
                            .width(MaterialTheme.dimens.widthSmallExtra),
                        color = lessonsColors[viewModel.dialogRelatedLesson.value.name]!!,
                        thickness = MaterialTheme.dimens.thicknessExtraSmall
                    )


                    Column {

                        Text(
                            text = task?.lessons.toString(),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(Modifier.height(MaterialTheme.dimens.heightExtraSmall))

                        Row {
                            Icon(
                                painter = painterResource(drawable.clock),
                                contentDescription = "clock",
                                modifier = Modifier
                                    .size(MaterialTheme.dimens.iconSizeExtraSmall)
                                    .align(Alignment.CenterVertically),
                                tint = MaterialTheme.colorScheme.onBackground
                            )

                            Spacer(Modifier.width(MaterialTheme.dimens.widthSmall))

                            Text(
                                text = "10:00 - 11:30",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.dimens.space28),
            shape = RoundedCornerShape(MaterialTheme.dimens.cornerLarge),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            border = BorderStroke(
                MaterialTheme.dimens.widthOne,
                MaterialTheme.colorScheme.outlineVariant
            )
        ) {

            Column(
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.dimens.space16,
                        vertical = MaterialTheme.dimens.space14
                    ),
            ) {
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(Modifier.height(MaterialTheme.dimens.heightSmall))

                Text(
                    text = task?.description.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Spacer(Modifier.height(MaterialTheme.dimens.heightMedium))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.space6),
        ) {
            DetailsButton(
                modifier = Modifier.weight(1f),
                text = "Edit Task",
                color = Color(0xFF4f6fa7),
                icon = drawable.edit,
                sizeIcon = MaterialTheme.dimens.iconSizeMedium,
                onClick = {
                    viewModel.onBottomDialogEvent(DetailsEvent.Edit)
                },
            )

            DetailsButton(
                modifier = Modifier.weight(1f),
                text = "Delete Task",
                color = Color(0xFFE01C42),
                icon = drawable.trash,
                sizeIcon = MaterialTheme.dimens.iconSizeSmallPlus,
                onClick = {
                    viewModel.onBottomDialogEvent(DetailsEvent.Delete)
                }
            )

        }
    }

    if (viewModel.isSheetOpen.value)
        ModalBottomSheetDetails(
            sheetState = sheetState,
            onDismiss = { viewModel.onBottomDialogEvent(DetailsEvent.OnCancel) },
            title = viewModel.dialogTitle,
            description = viewModel.dialogDescription,
            selectedLesson = viewModel.dialogRelatedLesson,
            dueDate = viewModel.dialogDueDate,
            dialogPriority = viewModel.dialogPriority,
            check = viewModel.checkState,
            lessonList = viewModel.listLessons,
            viewModel = viewModel
        )
}