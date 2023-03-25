package com.kauzganga.chindali.data

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kauzganga.chindali.ChindaliApplication
import com.kauzganga.chindali.database.ChindaliDao
import com.kauzganga.chindali.database.ChindaliDatabase
import com.kauzganga.chindali.network.ChindaliAPiService
import com.kauzganga.chindali.network.ChindaliApi
import com.kauzganga.chindali.repository.MainRepository
import kotlinx.coroutines.launch
import kotlin.math.log

class WordsViewModel () : ViewModel(){


    private var chindaliDao = ChindaliDatabase.getInstance(ChindaliApplication.getAppContext()).chindaliDao
    val repository = MainRepository(chindaliDao)
    val words = repository.words
    var searchWords by mutableStateOf(listOf(Word(1,"","","","","","","")))

    fun searchWord(newText: String) {
       viewModelScope.launch {
           searchWords = repository.daoObject.search(newText)
           Log.i("search", searchWords.toString())
       }
    }

    init {
        viewModelScope.launch {
            try{
                repository.refreshWords()
            }catch (e: Exception){
               Log.i("error refreshing",e.message.toString())
            }

        }

    }
}