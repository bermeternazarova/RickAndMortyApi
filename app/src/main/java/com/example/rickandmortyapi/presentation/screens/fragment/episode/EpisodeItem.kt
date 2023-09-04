package com.example.rickandmortyapi.presentation.screens.fragment.episode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmortyapi.domain.model.episode.Episode
import com.example.rickandmortyapi.presentation.ui.LocalCustomColorsPalette

@Composable
fun EpisodeItem(
    item:Episode,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(16.dp)
            .wrapContentSize()
            .clip(
                shape = RoundedCornerShape(20)
            )
            .background(color = Color.Gray)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 5.dp, end = 20.dp, start = 20.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = LocalCustomColorsPalette.current.violet, shape = RoundedCornerShape(20)),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = item.name,
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 5.dp, end = 20.dp, start = 20.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = LocalCustomColorsPalette.current.violet, shape = RoundedCornerShape(20)),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = item.episode,
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 20.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = LocalCustomColorsPalette.current.violet, shape = RoundedCornerShape(20)),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = item.created,
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,

                )
        }
    }
}