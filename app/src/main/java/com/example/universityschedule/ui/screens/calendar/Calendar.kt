package com.example.universityschedule.ui.screens.calendar

//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun Calendar(navController: NavController) {
//
//    val days = remember { generateDays() }
//    var selectedDay by remember { mutableStateOf(days.first()) }
//
//    Column {
//        ScrollableTabRow(
//            selectedTabIndex = days.indexOfFirst { it.id == selectedDay.id },
//            edgePadding = 0.dp
//
//        ) {
//            days.forEach { day ->
//                Tab(
//                    selected = day.id == selectedDay.id,
//                    onClick = {
//                        selectedDay = day
//                        navController.navigate("day/${day.id}")
//                    },
//                    text = {
//                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                            Text(day.dayName)
//                            Text("${day.date}")
//                            Text(day.month)
//                        }
//                    }
//                )
//            }
//        }
//
//        // Контент текущего дня
//        DayContent(day = selectedDay)
//        AddTaskButton {
//            navController.navigate("addTask")
//        }
//    }
//
//}