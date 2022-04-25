package com.example.myquotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myquotes.viewModels.MainViewModel
import com.example.myquotes.viewModels.QuoteViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = QuotesAdapter()
        recyclerView.adapter = adapter

        val repository = (application as QuoteApplication).quoteRepository

        viewModel = ViewModelProvider(this, QuoteViewModelFactory(repository))
                                    .get(MainViewModel::class.java)

        viewModel.quotes.observe(this, Observer {
            adapter.updateQuote(it)
        })
    }
}