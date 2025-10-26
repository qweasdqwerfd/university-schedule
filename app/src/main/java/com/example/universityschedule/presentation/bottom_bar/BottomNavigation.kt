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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.universityschedule.R

@Composable
fun BottomNavigation(
    navController: NavHostController,
    ) {

    val listItems = listOf(
        BottomItem.UniversitySchedule,
        BottomItem.Tasks,
    )

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
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
                        modifier = Modifier.size(20.dp),
                        contentDescription = "Icon",
                        
                    )
                },
                label = { Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodySmall,
                ) },

                colors = NavigationBarItemColors(
                    selectedIconColor = colorResource(R.color.selectedBottom),
                    selectedTextColor = colorResource(R.color.selectedBottom),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    selectedIndicatorColor = MaterialTheme.colorScheme.surface,
                    disabledIconColor = MaterialTheme.colorScheme.surface,
                    disabledTextColor = MaterialTheme.colorScheme.surface
                )

            )
        }




    }

}