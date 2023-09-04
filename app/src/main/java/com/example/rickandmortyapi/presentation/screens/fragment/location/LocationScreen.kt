package com.example.rickandmortyapi.presentation.screens.fragment.location

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.domain.model.location.Location
import com.example.rickandmortyapi.presentation.base.ProgressIndicatorScreen
import org.koin.androidx.compose.koinViewModel

@Preview
@Composable
fun LocationScreen() {
    val vm = koinViewModel<LocationViewModel>()
    val getAllLocation = vm.getAllLocation().collectAsLazyPagingItems()
      LocationTabScreen(location = getAllLocation, vm = vm)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationTabScreen(location: LazyPagingItems<Location>, vm: LocationViewModel) {
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
       TextField(modifier = Modifier
           .padding(vertical = 10.dp, horizontal = 20.dp)
           .fillMaxWidth()
           .border(
               width = 5.dp,
               brush = Brush.horizontalGradient(
                   listOf(
                       Color.White,
                       Color.Blue,
                       Color.White,
                       Color.White,
                       Color.Blue,
                       Color.White
                   )
               ),
               shape = RoundedCornerShape(16.dp)
           ),
           value =searchText ,
           onValueChange = vm::onSearchTextChange,
       colors = TextFieldDefaults.textFieldColors(
           containerColor = Color.Transparent,
           textColor = Color.White
       ),
           placeholder = { Text(text = "Search..", color = Color.White)}
           )

        LaunchedEffect(key1 = location.loadState) {
            if (location.loadState.refresh is LoadState.Error) {
                Toast.makeText(
                    context,
                    "Error: ${(location.loadState.refresh as LoadState.Error).error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        if (location.loadState.refresh is LoadState.Loading) {
            ProgressIndicatorScreen()
        } else {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    count = location.itemCount,
                    key = location.itemKey(),
                    contentType = location.itemContentType()
                ) { index ->
                    val item = location[index]
                    if (item != null) {
                        LocationItem(item = item,
                            modifier = Modifier.fillMaxWidth(),
                            onItemClick = {})
                    }
                }
                item {
                    if (location.loadState.append is LoadState.Loading) {
                        ProgressIndicatorScreen()
                    }
                }
            }
        }
    }
}
