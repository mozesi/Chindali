package com.kauzganga.chindali.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "words")
data class Word(@PrimaryKey val ID : Int,
                @ColumnInfo(name = "word") val word : String,
                @ColumnInfo(name = "meaning_one") val meaning_one : String,
                @ColumnInfo(name = "ndali_sentence_one") val ndali_sentence_one : String,
                @ColumnInfo(name = "english_sentence_one") val english_sentence_one: String,
                @ColumnInfo(name = "meaning_two") val meaning_two : String,
                @ColumnInfo(name = "ndali_sentence_two") val ndali_sentence_two : String,
                @ColumnInfo(name = "english_sentence_two") val english_sentence_two : String
                )

val dummyWords = listOf(
    Word(1,"abaa","those (people) of","likubddpo lishiku Uilyo booshi dbaa pdmbunmba ydabo bakukomdana, abdliishan 'dbakolo, pakumwiita iingamu ydd mwaana ",
        "there is a day when all those in the extended family, men and women, meet to give it\n","those who","iinga abaa kukuumba ubwiinabddmala bakugabuusha amafumu",
        "if those who were digging the grave are done, they tell the chiefs"),Word(1,"abaa","those (people) of","likubddpo lishiku Uilyo booshi dbaa pdmbunmba ydabo bakukomdana, abdliishan 'dbakolo, pakumwiita iingamu ydd mwaana ",
        "there is a day when all those in the extended family, men and women, meet to give it\n","those who","iinga abaa kukuumba ubwiinabddmala bakugabuusha amafumu",
        "if those who were digging the grave are done, they tell the chiefs"))
