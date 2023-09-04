@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.rickandmortyapi.presentation.screens.fragment.episode

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.domain.model.episode.Episode
import com.example.rickandmortyapi.presentation.base.ProgressIndicatorScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun EpisodeScreen() {
    val vm = koinViewModel<EpisodeViewModel>()
    val getAllEpisodes = vm.getAllEpisodes().collectAsLazyPagingItems()
    EpisodeTabScreen(episodes = getAllEpisodes, vm = vm) }

@Composable
private fun EpisodeTabScreen(episodes: LazyPagingItems<Episode>, vm: EpisodeViewModel) {
    val searchText by vm.searchTxt.collectAsState()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.backf),
                contentScale = ContentScale.FillBounds
            )
    ) {
        LaunchedEffect(key1 = episodes.loadState) {
            if (episodes.loadState.refresh is LoadState.Error) {
                Toast.makeText(
                    context,
                    "Error: ${(episodes.loadState.refresh as LoadState.Error).error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        if (episodes.loadState.refresh is LoadState.Loading) {
            ProgressIndicatorScreen()
        } else {

            val text = remember { mutableStateOf(value = "") }
            TextField(
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 20.dp)
                    .fillMaxWidth()
                    .border(width = 5.dp,
                        brush = Brush.horizontalGradient(
                            listOf(Color.White, Color.Blue, Color.White,Color.White, Color.Blue, Color.White)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ),
                value = text.value,
                onValueChange = {
                    text.value = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    containerColor = Color.Transparent
                ),
                placeholder = { Text(text = "Search..", color = Color.White) },
            )

            LazyColumn(
                contentPadding = PaddingValues(vertical = 6.dp),
                modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    count = episodes.itemCount,
                    key = episodes.itemKey(),
                    contentType = episodes.itemContentType()
                ) { index ->
                    val item = episodes[index]
                    if (item != null) {
                        EpisodeItem(item = item,
                            modifier = Modifier.fillMaxWidth())
                    }
                }
                item {
                    if (episodes.loadState.append is LoadState.Loading) {
                        ProgressIndicatorScreen()
                    }
                }
            }
        }
    }
}
