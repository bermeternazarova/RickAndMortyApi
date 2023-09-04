package com.example.rickandmortyapi.presentation.screens.fragment.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.domain.model.character.Character
import com.example.rickandmortyapi.presentation.ui.LocalCustomColorsPalette

@Composable
fun CharacterItem(
    item:Character,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .wrapContentSize()
            .clip(
                shape = RoundedCornerShape(20)
            )
            .background(
                color = LocalCustomColorsPalette.current.violet
            ),
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                )
        ) {
            AsyncImage(
                model = item.image,
                modifier = Modifier
                    .fillMaxSize(),
                contentDescription = "",
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(color = Color.White)
        )
        Text(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 10.dp),
            text = item.name,
            color = Color.White,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Cursive
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 5.dp),
            text = item.gender,
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Cursive
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 5.dp),
            text = item.status,
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Cursive
        )

    }
}