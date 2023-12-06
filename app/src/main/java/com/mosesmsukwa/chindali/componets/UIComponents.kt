package com.mosesmsukwa.chindali.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mosesmsukwa.chindali.R
import com.mosesmsukwa.chindali.words.Word


@Composable
fun WelcomeComponent(modifier: Modifier = Modifier, onShow: () -> Unit, name: String) {
    Column(modifier = modifier) {

        Text(text = "Welcome to Chindali", style = MaterialTheme.typography.titleMedium)
        Text(
            text = "Explore and enjoy learning chindali $name",
            style = MaterialTheme.typography.bodySmall
        )
        Button(onClick = onShow) {
            Text(text = "Show")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchItem() {
    Row() {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null
        )
        OutlinedTextField(
            value = "", onValueChange = {}, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Black
            )
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
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(names) { name ->
            WordType(wordTitle = name)
        }
    }
}

@Composable
fun WordCard(
    modifier: Modifier = Modifier,
    checked: Boolean,
    word: Word,
    onChecked: (Boolean) -> Unit
) {
    Card(modifier = Modifier.padding(10.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Text(text = word.word)
            Text(text = word.meaning)
            Checkbox(checked = checked, onCheckedChange = onChecked)
        }
    }
}

@Composable
fun WordCard(modifier: Modifier = Modifier, word: Word) {
    var nChecked by rememberSaveable { mutableStateOf(false) }
    WordCard(word = word, checked = nChecked, onChecked = { newValue -> nChecked = newValue })
}

/**
 * Buttons
 * */
//Button with Icon
@Composable
fun ButtonWithIcon(modifier: Modifier = Modifier, iconResource: Painter,label :String, onClickAction : ()->Unit) {
    Button(onClick = onClickAction) {
        Text(text = label)
        Icon(
            painter = iconResource,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
    }
}

//Corner shaped Button
@Composable
fun CornerShapedButton(buttonLabel: String) {
    Button(onClick = { /*TODO*/ }, shape = CutCornerShape(10)) {
        Text(text = buttonLabel)
    }
}

//Rounded corner button
@Composable
fun RoundedCornerShapeButton(buttonLabel: String) {
    Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(10)) {
        Text(text = buttonLabel)

    }
}

//Elevated Button
@Composable
fun ElevatedButton(buttonLabel: String) {
    Button(
        onClick = { /*TODO*/ },
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 8.dp, pressedElevation = 10.dp,
            disabledElevation = 0.dp
        )
    ) {
        Text(text = buttonLabel)
    }
}