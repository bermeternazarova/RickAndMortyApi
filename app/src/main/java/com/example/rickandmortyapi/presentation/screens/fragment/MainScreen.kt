@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalPagerApi::class
)

package com.example.rickandmortyapi.presentation.screens.fragment

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.rickandmortyapi.presentation.ui.LocalCustomColorsPalette
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavController
) {
    val tabs = listOf(
        TabItem.Character,
        TabItem.Location,
        TabItem.Episode
    )
    val pagerState = rememberPagerState(pageCount = tabs.size)

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            MyTabs(tabs = tabs, pagerState = pagerState)
            MyTabsContent(tabs = tabs, pagerState = pagerState)
        }
}
@Composable
fun MyTabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(
        state = pagerState
    ) { page ->
        tabs[page].screen()
    }
}

@Composable
fun MyTabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        contentColor = Color.White,
        backgroundColor = LocalCustomColorsPalette.current.violet,
        indicator = { tabPosition ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPosition)
            )
        }) {
        tabs.forEachIndexed { index, tab ->
            LeadingIconTab(
                text = { Text(text = tab.title,
                    color = Color.White
                ) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                icon = {})
        }
    }
}
