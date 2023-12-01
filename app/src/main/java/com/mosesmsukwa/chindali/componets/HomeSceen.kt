package com.mosesmsukwa.chindali.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mosesmsukwa.chindali.R

@Composable
fun WelcomeComponent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Welcome to Chindali", style = MaterialTheme.typography.titleMedium)
        Text(
            text = "Explore and enjoy learning chindali.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun WordType(modifier: Modifier = Modifier, wordTitle: String) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = wordTitle,
            modifier = Modifier
                .paddingFromBaseline(24.dp, 8.dp)
                .heightIn(40.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
    }
}

@Composable
fun WordTypeSection(modifier: Modifier = Modifier, names: List<String>) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = Modifier.height(200.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(names) { name ->
            WordType(wordTitle = name)
        }
    }
}

@Preview
@Composable
fun Home() {
    Column {
        WelcomeComponent()
        Spacer(modifier = Modifier.height(16.dp))
        val names = listOf("Moses One", "Moses One", "Moses One", "Moses One")
        WordTypeSection(names = names)
    }
}