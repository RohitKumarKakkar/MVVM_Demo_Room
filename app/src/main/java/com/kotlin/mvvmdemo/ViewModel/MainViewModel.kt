package com.kotlin.mvvmdemo.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.mvvmdemo.Entities.Quote
import com.kotlin.mvvmdemo.Repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository) : ViewModel() {

    fun getQuotes(): LiveData<List<Quote>> {
        return repository.getQuotes()
    }

    fun insertQuote(quote: Quote) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertQuote(quote)
        }
    }

}