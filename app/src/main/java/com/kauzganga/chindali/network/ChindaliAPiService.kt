package com.kauzganga.chindali.network


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kauzganga.chindali.Constants
import com.kauzganga.chindali.data.Word
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofitChindali = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(Constants.BASE_URL)
    .build()


interface ChindaliAPiService {
    @GET("/words")
    suspend fun getWords():List<Word>
}

object ChindaliApi{
    val retrofitService: ChindaliAPiService by lazy {
        retrofitChindali.create(ChindaliAPiService::class.java)
    }
}
