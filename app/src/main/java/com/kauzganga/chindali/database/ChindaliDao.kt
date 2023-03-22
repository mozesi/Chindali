package com.kauzganga.chindali.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kauzganga.chindali.data.Word
import kotlinx.coroutines.flow.Flow


@Dao
interface ChindaliDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(words: List<Word>)

    @Query("SELECT * FROM words")
    fun getWords(): Flow<List<Word>>
}