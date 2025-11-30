package com.example.universityschedule.presentation.bottom_bar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.universityschedule.R
import com.example.universityschedule.presentation.util.dimens

@Composable
fun BottomNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    textStyle: TextStyle = MaterialTheme.typography.bodySmall,
    selectedIconColor: Color = colorResource(R.color.selectedBottom),
    selectedTextColor: Color = colorResource(R.color.selectedBottom),
    unselectedIconColor: Color = Color.Gray,
    unselectedTextColor: Color = Color.Gray,
    selectedIndicatorColor: Color = MaterialTheme.colorScheme.surface,
    disabledIconColor: Color = MaterialTheme.colorScheme.surface,
    disabledTextColor: Color = MaterialTheme.colorScheme.surface
) {

    val listItems = listOf(
        BottomItem.UniversitySchedule,
        BottomItem.Tasks,
    )

    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = containerColor,
        contentColor = Color.Gray
    ) {


        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        listItems.forEach { item ->

            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        modifier = Modifier.size(MaterialTheme.dimens.space20),
                        contentDescription = "Icon",
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = textStyle,
                    )
                },

                colors = NavigationBarItemColors(
                    selectedIconColor = selectedIconColor,
                    selectedTextColor = selectedTextColor,
                    unselectedIconColor = unselectedIconColor,
                    unselectedTextColor = unselectedTextColor,
                    selectedIndicatorColor = selectedIndicatorColor,
                    disabledIconColor = disabledIconColor,
                    disabledTextColor = disabledTextColor
                )

            )
        }


    }

}