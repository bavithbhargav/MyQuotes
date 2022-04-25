package com.example.myquotes.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myquotes.api.QuotesApi
import com.example.myquotes.db.QuoteDao
import com.example.myquotes.db.QuoteDatabase
import com.example.myquotes.model.QuoteList
import com.example.myquotes.utils.NetworkUtils

class QuoteRepository(private val quotesApi: QuotesApi,
                      private val quoteDatabase: QuoteDatabase,
                      private val context: Context) {

    private val quotesLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList>
        get() = quotesLiveData

    suspend fun getQuotes(page: Int){
        if(NetworkUtils.isConnectingToInternet(context)){
            val result = quotesApi.getQuotes(page)
            if(result?.body() != null){
                quotesLiveData.postValue(result.body())
                quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
            }
        }
        else{
            val quotes = quoteDatabase.quoteDao().getQuotes()
            val quoteList = QuoteList(1,1,1,quotes,1,1)
            quotesLiveData.postValue(quoteList)
        }
    }

}