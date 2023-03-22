package com.kauzganga.chindali.data

import com.google.gson.annotations.SerializedName

data class Word(val iD : Int,
                val word : String,
                val meaning_one : String,
                val ndali_sentence_one : String,
                val english_sentence_one: String,
                val meaning_two : String,
                val ndali_sentence_two : String,
                val english_sentence_two : String
                )

val dummyWords = listOf(
    Word(1,"abaa","those (people) of","likubddpo lishiku Uilyo booshi dbaa pdmbunmba ydabo bakukomdana, abdliishan 'dbakolo, pakumwiita iingamu ydd mwaana ",
        "there is a day when all those in the extended family, men and women, meet to give it\n","those who","iinga abaa kukuumba ubwiinabddmala bakugabuusha amafumu",
        "if those who were digging the grave are done, they tell the chiefs"),Word(1,"abaa","those (people) of","likubddpo lishiku Uilyo booshi dbaa pdmbunmba ydabo bakukomdana, abdliishan 'dbakolo, pakumwiita iingamu ydd mwaana ",
        "there is a day when all those in the extended family, men and women, meet to give it\n","those who","iinga abaa kukuumba ubwiinabddmala bakugabuusha amafumu",
        "if those who were digging the grave are done, they tell the chiefs"))
