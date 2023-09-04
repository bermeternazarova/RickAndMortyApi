@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.rickandmortyapi.presentation.base

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.presentation.ui.LocalCustomColorsPalette

@Composable
fun ShowAlert(
    openDialog: MutableState<Boolean>,
    onCancel :() -> Unit,
    onApply :() -> Unit,
    onStatusClick: () -> Unit,
    onSpeciesClick: () -> Unit,
    onGenderClick: () -> Unit
){
    Dialog(
        onDismissRequest = { openDialog.value = false }
    ) {
        AlertContent(
            onCancel = { onCancel() },
            onApply = { onApply() },
            onStatusClick = {onStatusClick()},
            onSpeciesClick = {onSpeciesClick()},
            onGenderClick = {onGenderClick()}
        )
    }
}


@Composable
fun AlertContent(onCancel: () -> Unit,
                 onStatusClick: () -> Unit,
                 onSpeciesClick: () -> Unit,
                 onGenderClick: () -> Unit,
                 onApply: () -> Unit) {

    val statusOptions = listOf("Alive", "Dead", "Unknown")
    val speciesOptions = listOf("Human", "Humanoid", "ALien")
    val genderOptions = listOf("Female", "Male", "Unknown")
    val (onSelectedStatus, onOptionsSelectedStatus) = remember {
        mutableStateOf(statusOptions[1])
    }
    val (onSelectedSpecies, onOptionsSelectedSpecies) = remember {
        mutableStateOf(speciesOptions[1])
    }
    val (onSelectedGender, onOptionsSelectedGender) = remember {
        mutableStateOf(genderOptions[1])
    }

    Card(
        shape = RoundedCornerShape(10),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column (
            modifier = Modifier
                .paint(
                    painter = painterResource(id = R.drawable.backf),
                    contentScale = ContentScale.FillBounds
                ),
        ){
            Text(
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp),
                text = "Status",
                color = LocalCustomColorsPalette.current.violet,
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace
            )
            Row {
                statusOptions.forEach { statusText ->

                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .selectable(selected = (statusText == onSelectedStatus),
                                onClick = {
                                    onOptionsSelectedStatus(statusText)
                                }),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        RadioButton(
                            selected = (statusText == onSelectedStatus),
                            onClick = {
                                onOptionsSelectedStatus(statusText)
                                onStatusClick()
                            })
                        Text(
                            text = statusText,
                            color = Color.White,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 12.sp
                        )

                    }

                }
            }
            Text(
                modifier = Modifier
                    .padding(top = 10.dp, start = 20.dp),
                text = "Species",
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace,
                color = LocalCustomColorsPalette.current.violet
            )
            Row {
                speciesOptions.forEach{speciesText ->
                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .selectable(selected = (speciesText == onSelectedSpecies),
                                onClick = {
                                    onOptionsSelectedSpecies(speciesText)
                                    onSpeciesClick()
                                }),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        RadioButton(
                            selected = (speciesText == onSelectedSpecies),
                            onClick = {
                                onOptionsSelectedSpecies(speciesText)
                            })
                        Text(
                            modifier = Modifier
                                .padding(end = 10.dp),
                            text = speciesText,
                            color = Color.White,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 12.sp
                        )

                    }
                }
            }
            Text(
                modifier = Modifier
                    .padding(top = 10.dp, start = 20.dp),
                text ="Gender",
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace,
                color = LocalCustomColorsPalette.current.violet
            )
            Row {
                genderOptions.forEach {genderText ->
                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .selectable(selected = (genderText == onSelectedGender),
                                onClick = {
                                    onOptionsSelectedGender(genderText)
                                }),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        RadioButton(
                            selected = (genderText == onSelectedGender),
                            onClick = {
                                onOptionsSelectedGender(genderText)
                                onGenderClick()
                            })
                        Text(
                            text = genderText,
                            color = Color.White,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Row (
                modifier = Modifier
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Button(
                    modifier = Modifier
                        .padding(horizontal = 30.dp),
                    onClick = {
                        onCancel()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LocalCustomColorsPalette.current.violet
                    )
                ) {
                    Text(
                        text = "Cancel",
                        fontFamily = FontFamily.Serif,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }

                Button(
                    modifier = Modifier
                        .padding(horizontal = 30.dp),
                    onClick = {
                        onApply()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LocalCustomColorsPalette.current.violet
                    )) {
                    Text(
                        text = "Apply",
                        fontFamily = FontFamily.Serif,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun ProgressIndicatorScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun TextFieldShow(text: String) {
    val text = remember {
        mutableStateOf("")
    }
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
        colors = androidx.compose.material3.TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            textColor = Color.White
        ),
        placeholder = { Text(text = "Search..", color = Color.White) })
}
