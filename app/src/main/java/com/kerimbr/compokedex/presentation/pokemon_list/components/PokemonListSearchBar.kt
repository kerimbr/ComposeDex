package com.kerimbr.compokedex.presentation.pokemon_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PokemonListSearchBar(
    modifier: Modifier = Modifier,
    hint: String = "Name or number",
    onSearch: (String) -> Unit = {}
) {

    var text: String by remember { mutableStateOf("") }
    var isHintVisible: Boolean by remember { mutableStateOf(true) }

    Row(
        modifier = modifier
            .height(48.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .clip(shape = MaterialTheme.shapes.small)
                .weight(0.75f)
                .background(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 8.dp),
                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
                Box {
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusChanged {
                                isHintVisible = !it.isFocused
                            },
                        value = text,
                        onValueChange = {
                            text = it
                        },
                        textStyle = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.W500,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                        ),
                    )
                    if (isHintVisible) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            text = hint,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.W500,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                            ),
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }

        }

        Box(modifier = Modifier.weight(0.05f))

        // Search Button
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .clip(shape = MaterialTheme.shapes.small)
                .weight(0.2f)
                .align(Alignment.CenterVertically)
                .background(
                    color = MaterialTheme.colorScheme.secondary
                )
                .clickable { onSearch(text) }
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.Center),
                tint = MaterialTheme.colorScheme.onSecondary,
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        }

    }
}