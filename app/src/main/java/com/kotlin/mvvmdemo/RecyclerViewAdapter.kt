package com.kotlin.mvvmdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.mvvmdemo.Entities.Quote

class RecyclerViewAdapter(private val list: List<Quote>) :
    ListAdapter<Quote, RecyclerViewAdapter.RecyclerViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemview, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val listitem = list[position]
        holder.author.text = listitem.author
        holder.tvquote.text = listitem.text
        holder.tvFirstLetter.text = listitem.id.toString()
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val author = view.findViewById<TextView>(R.id.tvAuthor)
        val tvquote = view.findViewById<TextView>(R.id.tvQuote)
        val tvFirstLetter = view.findViewById<TextView>(R.id.tvFirstLetter)
    }


    // this Code Prevents Our Recycler view to reload all the View and Only Update the New Items.
    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Quote>() {

        // This Override Function will Compare if the ID's Are Same of any Quote.
        override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem.id == newItem.id
        }

        // This Override Function will Compare the Whole Entries including quote and author name.
        override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem == newItem
        }
    }
}