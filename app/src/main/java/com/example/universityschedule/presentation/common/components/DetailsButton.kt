package com.example.universityschedule.presentation.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import com.example.universityschedule.presentation.util.dimens

@Composable
fun DetailsButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    icon: Int?,
    sizeIcon: Dp?,
    onClick: () -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.ExtraBold),
    textColor: Color = MaterialTheme.colorScheme.onPrimary
) {

    Button(
        onClick = { onClick() },
        modifier = modifier
            .height(MaterialTheme.dimens.heightLargePlus)
            .padding(horizontal = MaterialTheme.dimens.space4),
        shape = RoundedCornerShape(MaterialTheme.dimens.cornerLarge),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = MaterialTheme.dimens.elevationMedium,
            pressedElevation = MaterialTheme.dimens.elevationHigh
        ),
        border = BorderStroke(MaterialTheme.dimens.widthOne, MaterialTheme.colorScheme.outline)
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            icon?.let { icon ->
                sizeIcon?.let { size ->
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = "edit",
                        modifier = Modifier
                            .size(size),
                    )
                }
                Spacer(Modifier.width(MaterialTheme.dimens.widthSmallMinus))
            }
            Text(
                text = text,
                style = textStyle,
                color = textColor
            )
        }
    }
}