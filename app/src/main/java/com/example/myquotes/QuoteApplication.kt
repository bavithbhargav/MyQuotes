package com.example.myquotes

import android.app.Application
import com.example.myquotes.api.QuotesApi
import com.example.myquotes.api.RetrofitHelper
import com.example.myquotes.db.QuoteDatabase
import com.example.myquotes.repository.QuoteRepository

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuotesApi::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService, database, applicationContext)
    }
}