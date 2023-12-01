package com.mosesmsukwa.chindali.componets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mosesmsukwa.chindali.R
import com.mosesmsukwa.chindali.words.WordDataSource

@Preview(showBackground = true)
@Composable
fun Home() {
    Column {
        WelcomeComponent()
        Spacer(modifier = Modifier.height(16.dp))
        SearchItem()
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(WordDataSource().loadWords()) {
                WordCard(word = it)
            }
        }

    }
}