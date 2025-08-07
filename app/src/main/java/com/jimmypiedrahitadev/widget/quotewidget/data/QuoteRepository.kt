package com.jimmypiedrahitadev.widget.quotewidget.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuoteRepository {

    private  val retrofit = Retrofit.Builder()
        .baseUrl("https://frases-dfd5e-default-rtdb.firebaseio.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val apiService = retrofit.create(QuoteApiService::class.java)

    suspend fun getRandomQuote(): String {
        val response = apiService.getQuotes()
        return response.quotes.randomOrNull() ?: "No quotes available"
    }
}