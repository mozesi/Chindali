package com.kauzganga.chindali.data

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
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

    init {
        viewModelScope.launch {
           repository.refreshWords()
        }

    }
}