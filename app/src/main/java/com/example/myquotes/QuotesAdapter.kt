package com.example.myquotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myquotes.model.QuoteList
import com.example.myquotes.model.Result

class QuotesAdapter : RecyclerView.Adapter<QuotesAdapter.QuotesViewHolder>() {

    private val quoteList : ArrayList<Result> = ArrayList()

    class QuotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false)
        return QuotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        val item = quoteList[position]
        holder.textView.text = item.content
    }

    override fun getItemCount(): Int = quoteList.size

    fun updateQuote(quotes: QuoteList){
        quoteList.clear()
        quoteList.addAll(quotes.results)

        notifyDataSetChanged()
    }
}