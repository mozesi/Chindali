package com.mosesmsukwa.chindali.componets

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mosesmsukwa.chindali.R
import com.mosesmsukwa.chindali.words.WordDataSource

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun Home() {
   // val count: MutableState<Int> = mutableStateOf(0)
     var count by rememberSaveable{mutableStateOf(0)}
     var name by rememberSaveable{mutableStateOf("MOses Msuwka")}
  var words = remember{WordDataSource().loadWords().toMutableStateList()}
    Column {
        WelcomeComponent(modifier = Modifier, onShow = { name= name+" "+ count++},name)
        Spacer(modifier = Modifier.height(16.dp))
        SearchItem()
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(words) {
                WordCard(word = it)
            }
        }

    }
}