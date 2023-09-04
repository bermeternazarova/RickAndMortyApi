package com.example.rickandmortyapi.presentation.screens.fragment.character

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.domain.model.character.Character
import com.example.rickandmortyapi.presentation.base.ProgressIndicatorScreen
import com.example.rickandmortyapi.presentation.base.ShowAlert
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterScreen(
) {
    val vm: CharacterViewModel = koinViewModel()
    val getAllCharacters = vm.getAllCharacters().collectAsLazyPagingItems()
    CharacterTabScreen(characters = getAllCharacters)
}

@Composable
private fun CharacterTabScreen(
    characters: LazyPagingItems<Character>
) {
    val context = LocalContext.current

    var stateShow = remember {
        mutableStateOf(false)
    }

    if (stateShow.value)
        ShowAlert(
            openDialog = stateShow,
            onCancel = {
                stateShow.value = true
            },
            onApply = {
                Toast.makeText(context, "Apply", Toast.LENGTH_SHORT).show()
            },
            onStatusClick = {
                Toast.makeText(context, "Status", Toast.LENGTH_SHORT).show()
            },
            onSpeciesClick = {
                Toast.makeText(context, "Species", Toast.LENGTH_SHORT).show()
            },
            onGenderClick = {
                Toast.makeText(context, "Gender", Toast.LENGTH_SHORT).show()
            }
        )

    LaunchedEffect(key1 = characters.loadState) {
        if (characters.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: ${(characters.loadState.refresh as LoadState.Error).error.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (characters.loadState.refresh is LoadState.Loading) {
            ProgressIndicatorScreen()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painter = painterResource(id = R.drawable.backf),
                        contentScale = ContentScale.FillBounds
                    )
            ) {
                val text = remember { mutableStateOf(value = "") }

                TextField(
                    modifier = Modifier
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
                    value = text.value,
                    onValueChange = {
                        text.value = it
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        textColor = Color.White
                    ),
                    placeholder = { Text(text = "Search..", color = Color.White) },
                )

                Text(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .wrapContentSize()
                        .clickable {
                            stateShow.value = true
                        },
                    text = "Sorting here..",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Monospace
                )

                LazyColumn(
                    contentPadding = PaddingValues(vertical = 6.dp),
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(
                        count = characters.itemCount,
                        key = characters.itemKey(),
                        contentType = characters.itemContentType(
                        )
                    ) { index ->
                        val item = characters[index]
                        if (item != null) {
                            CharacterItem(
                                item = item,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                    item {
                        if (characters.loadState.append is LoadState.Loading) {
                            ProgressIndicatorScreen()
                        }
                    }
                }
            }
        }
    }
}