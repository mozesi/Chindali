package com.kauzganga.chindali.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kauzganga.chindali.network.ChindaliAPiService
import com.kauzganga.chindali.network.ChindaliApi
import kotlinx.coroutines.launch
import kotlin.math.log

class WordsViewModel () : ViewModel(){
     val state = mutableStateOf(
         dummyWords
     )
    init {
        viewModelScope.launch {
            val dataa = ChindaliApi.retrofitService.getWords()
             Log.i("ERROR", dataa.toString())
            state.value = dataa

        }

    }
}