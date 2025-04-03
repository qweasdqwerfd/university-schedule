package com.example.universityschedule.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.universityschedule.R
import kotlinx.coroutines.launch

@Composable
fun ScrollableTabRowPager(
    tabs: List<String>,
    pages: List<@Composable () -> Unit>
    ) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { tabs.size }
    )
    val coroutineScope = rememberCoroutineScope()

    Column {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .height(2.dp)
                        .background(color = colorResource(R.color.selectedBottom))
                )
            },

        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = title,
                            color = if (pagerState.currentPage == index)
                                colorResource(R.color.selectedBottom)
                            else androidx.compose.ui.graphics.Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            pages[page]()

        }
    }


}