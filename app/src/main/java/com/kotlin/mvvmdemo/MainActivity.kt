package com.kotlin.mvvmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.mvvmdemo.Database.QuoteDatabase
import com.kotlin.mvvmdemo.Entities.Quote
import com.kotlin.mvvmdemo.Repository.QuoteRepository
import com.kotlin.mvvmdemo.ViewModel.MainViewModel
import com.kotlin.mvvmdemo.ViewModel.MainViewModelFactory
import com.kotlin.mvvmdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Getting Database Access
        val dao = QuoteDatabase.getDatabase(applicationContext).quoteDao()
        val repository = QuoteRepository(dao)

        //Initializing View Model
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        //Reading All Quotes and Setting to Textview From XML File.
        mainViewModel.getQuotes().observe(this, Observer {
            binding.quotesModel = it.toString()
            val adapter = RecyclerViewAdapter(it)
            binding.recylerView.adapter = adapter
        })

        //Adding new Quote with Button Click
        binding.bntAddQuote.setOnClickListener {
            val quote = Quote(0, "Testing new", "Rohit")
            mainViewModel.insertQuote(quote)
        }

    }
}