package com.example.rickandmortyapi.presentation.screens.fragment.location

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.rickandmortyapi.domain.model.location.Location

@Composable
fun LocationItem(item: Location, modifier: Modifier, onItemClick: (Int) -> Unit) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (name, type, idTv) = createRefs()

        Text(
            text = item.id.toString(),
            modifier = Modifier
                .wrapContentWidth()
                .constrainAs(idTv) {
                    top.linkTo(parent.top, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                },
            color = Color.Green,
            fontSize = 20.sp,
           fontFamily = FontFamily.Monospace
        )

        Text(
            text = item.name,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .wrapContentWidth()
                .constrainAs(name) {
                    top.linkTo(parent.top, margin = 20.dp)
                    start.linkTo(idTv.end)
                    end.linkTo(parent.end)
                },
            color = Color.Green,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = item.type,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .wrapContentWidth()
                .constrainAs(type) {
                    top.linkTo(name.bottom, margin = 8.dp)
                    start.linkTo(name.start)
                    end.linkTo(name.end)
                },
            color = Color.Green,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}