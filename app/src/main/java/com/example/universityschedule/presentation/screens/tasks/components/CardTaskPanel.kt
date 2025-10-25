package com.example.universityschedule.presentation.screens.tasks.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.example.universityschedule.R
import com.example.universityschedule.domain.model.TaskItem
import com.example.universityschedule.presentation.common.DialogEvent
import com.example.universityschedule.presentation.screens.tasks.components.dialog_controller.LessonChip
import com.example.universityschedule.presentation.util.dimens
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CardTaskPanel(
    item: TaskItem,
    onEvent: (DialogEvent) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(MaterialTheme.dimens.space16)
            .fillMaxWidth()
            .clickable {
                onEvent(DialogEvent.OnItemClick(item.id))
            },
        shape = RoundedCornerShape(MaterialTheme.dimens.cornerExtraLarge),
        elevation = CardDefaults.cardElevation(MaterialTheme.dimens.elevationHigh),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(MaterialTheme.dimens.space16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(MaterialTheme.dimens.heightExtraLarge)
                    .width(MaterialTheme.dimens.widthMedium)
                    .clickable {
                        onEvent(DialogEvent.OnCircleClick(item.id))
                    },
            ) {
                if (item.check) {
                    Icon(
                        painter = painterResource(R.drawable.tick2),
                        contentDescription = "tick",
                        modifier = Modifier
                            .size(MaterialTheme.dimens.iconSizeLargePlus)
                            .align(Alignment.Center),
                        tint = Color.Green
                    )
                } else {

                    Icon(
                        painter = painterResource(R.drawable.circle),
                        contentDescription = "circle",
                        modifier = Modifier
                            .size(MaterialTheme.dimens.iconSizeMedium)
                            .align(Alignment.Center)

                    )
                }
            }


            Spacer(modifier = Modifier.width(MaterialTheme.dimens.widthSmallPlus))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text =
                        if (item.lessons.name != LessonChip.NONE.name) {
                            "${item.lessons} " +
                                    if (item.title.length > 15)
                                        item.title.take(15) + "..."
                                    else " ${item.title}"
                        } else {
                            if (item.title.length > 15)
                                item.title.take(15) + "..."
                            else
                                item.title
                        },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    textDecoration = if (item.check) TextDecoration.LineThrough else TextDecoration.None
                )
                Text(
                    text =
                        if (item.description.length > 20)
                            item.description.take(20) + "..."
                        else
                            item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    textDecoration = if (item.check) TextDecoration.LineThrough else TextDecoration.None,
                )
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.heightExtraSmall))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.clock),
                            contentDescription = "Time",
                            modifier = Modifier.size(MaterialTheme.dimens.iconSizeSmall),
                            tint = Color.Gray
                        )
                        Text(
                            text = item.dueDate.format(
                                DateTimeFormatter.ofPattern("EE, MMM d, HH:mm", Locale("ru"))
                            ).toString(),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            modifier = Modifier.padding(start = MaterialTheme.dimens.space4),
                            textDecoration = if (item.check) TextDecoration.LineThrough else TextDecoration.None
                        )
                    }


                    Box(
                        modifier = Modifier
                            .padding(
                                start = MaterialTheme.dimens.space8,

                                )
                            .background(
                                color =
                                    when (item.priority.name) {
                                        "High" -> Color(0xFFca2244)
                                        "Medium" -> Color(0xFFf6c610)
                                        else -> Color(0xFF31b947)
                                    }, shape = RoundedCornerShape(MaterialTheme.dimens.cornerLarge)
                            )
                            .padding(
                                horizontal = MaterialTheme.dimens.space12,
                                vertical = MaterialTheme.dimens.space6
                            ),

                        ) {
                        Text(
                            text = item.priority.displayName,
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }


        }
    }
}