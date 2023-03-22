package com.kauzganga.chindali.repository

import android.util.Log
import com.kauzganga.chindali.database.ChindaliDao
import com.kauzganga.chindali.network.ChindaliApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository (val daoObject: ChindaliDao){
    val words = daoObject.getWords()


    suspend fun refreshWords(){
        withContext(Dispatchers.IO){
            val words = ChindaliApi.retrofitService.getWords()
            daoObject.insertWords(words)
            Log.i("Inserted", words.toString())
        }
    }
}