package com.example.universityschedule.ui.screens.tasks

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.universityschedule.R.drawable
import com.example.universityschedule.ui.custom_components.IconButton.def.DetailsButton

@Composable
fun TaskDetailsScreen(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                horizontal = 20.dp,
                vertical = 20.dp
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Essay Draft",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold
            )
            Icon(
                painter = painterResource(drawable.circle),
                contentDescription = "circle or tick",
                modifier = Modifier
                    .size(30.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .background(
                    color = Color(0xFFf7c602),
                    shape = RoundedCornerShape(13.dp)
                )
                .padding(
                    horizontal = 10.dp,
                    vertical = 8.dp
                ),

            ) {
            Row(
                modifier = Modifier
                    .width(127.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(drawable.warn),
                    contentDescription = "ward",
                    modifier = Modifier
                        .size(14.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )

                Text(
                    text = "Medium Priority",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 27.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {

            Column(
                modifier = Modifier
                    .padding(
                        horizontal = 15.dp,
                        vertical = 13.dp
                    ),
            ) {
                Row {
                    Icon(
                        painter = painterResource(drawable.calendar),
                        contentDescription = "calendar",
                        Modifier
                            .size(18.dp)
                            .align(Alignment.CenterVertically),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Due Date",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                Spacer(Modifier.height(15.dp))

                Text(
                    text = "Wednesday, April 2 at 09:34 PM",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 27.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {

            Column(
                modifier = Modifier
                    .padding(
                        horizontal = 15.dp,
                        vertical = 13.dp
                    ),
            ) {
                Row {
                    Icon(
                        painter = painterResource(drawable.lessons),
                        contentDescription = "calendar",
                        Modifier
                            .size(18.dp)
                            .align(Alignment.CenterVertically),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Related Lesson",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                Spacer(Modifier.height(15.dp))

                Row {

                    VerticalDivider(
                        modifier = Modifier
                            .height(43.dp)
                            .width(16.dp),
                        color = Color.Green,
                        thickness = 3.dp
                    )


                    Column {

                        Text(
                            text = "Literature",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(Modifier.height(7.dp))

                        Row {
                            Icon(
                                painter = painterResource(drawable.clock),
                                contentDescription = "clock",
                                modifier = Modifier
                                    .size(12.dp)
                                    .align(Alignment.CenterVertically),
                                tint = MaterialTheme.colorScheme.onBackground
                            )

                            Spacer(Modifier.width(7.dp))

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
                .padding(top = 27.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {

            Column(
                modifier = Modifier
                    .padding(
                        horizontal = 15.dp,
                        vertical = 13.dp
                    ),
            ) {
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(Modifier.height(15.dp))

                Text(
                    text = "Write first draft of comparative essay",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Spacer(Modifier.height(33.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            DetailsButton(
                modifier = Modifier.weight(1f),
                text = "Edit Task",
                color = Color(0xFF4f6fa7),
                icon = drawable.edit,
                sizeIcon = 25,
            )
            DetailsButton(
                modifier = Modifier.weight(1f),
                text = "Delete Task",
                color = Color(0xFFE01C42),
                icon = drawable.trash,
                sizeIcon = 21,
            )
        }
    }
}