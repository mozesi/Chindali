package com.kauzganga.chindali.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kauzganga.chindali.data.Word
import com.kauzganga.chindali.data.WordsViewModel
import com.kauzganga.chindali.data.dummyWords

@Composable
fun HomeScreen () {

    val viewModel: WordsViewModel = viewModel()
    val words =  viewModel.words.collectAsState(initial = emptyList())
    var text by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = text,
        onValueChange = { newText ->
            text = newText
            viewModel.searchWord(newText.text)

        }

    )

    LazyColumn(contentPadding = PaddingValues(vertical =8.dp, horizontal = 8.dp)){
        items(words.value){
            word ->
            WordItem(word = word)
        }
    }
}




@Composable
fun WordItem(word: Word) {
    Card(elevation = 4.dp, modifier = Modifier.padding(8.dp)){
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)){
            WordDetails(word)
        }

    }
}

@Composable
fun WordDetails(word : Word) {
    Text(text = word.word, style = MaterialTheme.typography.h6)
}
