package com.kauzganga.chindali.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kauzganga.chindali.data.Word


@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class ChindaliDatabase: RoomDatabase() {

    abstract val chindaliDao: ChindaliDao

    companion object {

        @Volatile
        private var INSTANCE: ChindaliDatabase? = null

        fun getInstance(context: Context): ChindaliDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ChindaliDatabase::class.java,
                        "Chindali database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }

    }

}