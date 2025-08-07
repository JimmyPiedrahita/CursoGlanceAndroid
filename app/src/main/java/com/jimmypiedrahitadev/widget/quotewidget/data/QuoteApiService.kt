package com.jimmypiedrahitadev.widget.quotewidget.data

import com.jimmypiedrahitadev.widget.quotewidget.data.response.QuoteResponse
import retrofit2.http.GET

interface QuoteApiService {
    @GET(".json")
    suspend fun getQuotes(): QuoteResponse
}