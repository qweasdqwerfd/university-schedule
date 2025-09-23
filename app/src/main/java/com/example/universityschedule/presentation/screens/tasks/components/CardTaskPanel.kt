package com.example.universityschedule.presentation.screens.tasks.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.universityschedule.R
import com.example.universityschedule.domain.model.TaskItem
import com.example.universityschedule.presentation.common.DialogEvent
import com.example.universityschedule.presentation.util.dimens

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
                .padding(MaterialTheme.dimens.space16)

            ,
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
                        contentDescription = "",
                        modifier = Modifier
                            .size(MaterialTheme.dimens.iconSizeLargePlus)
                            .align(Alignment.Center),
                        tint = Color.Green
                    )
                } else {

                    Icon(
                        painter = painterResource(R.drawable.circle),
                        contentDescription = "",
                        modifier = Modifier
                            .size(MaterialTheme.dimens.iconSizeMedium)
                            .align(Alignment.Center)

                        )
                }
            }




            Spacer(modifier = Modifier.width(MaterialTheme.dimens.widthSmallPlus))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${item.lessons} ${item.title}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    textDecoration = if (item.check) TextDecoration.LineThrough else TextDecoration.None
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    textDecoration = if (item.check) TextDecoration.LineThrough else TextDecoration.None
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
                            text = item.dueDate,
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
                            text = item.priority.name,
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