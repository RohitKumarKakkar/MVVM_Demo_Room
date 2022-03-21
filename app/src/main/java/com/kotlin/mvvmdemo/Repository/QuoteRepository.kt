package com.kotlin.mvvmdemo.Repository

import androidx.lifecycle.LiveData
import com.kotlin.mvvmdemo.DAO.QuoteDao
import com.kotlin.mvvmdemo.Entities.Quote

class QuoteRepository(private val quoteDao: QuoteDao) {

    /*
        If user is online - input data to db and show , or if user is offline , put data
        to room db and send data to Viewmodel will be done here
    */

    fun getQuotes(): LiveData<List<Quote>> {
        return quoteDao.getQuotes()
    }

    suspend fun insertQuote(quote: Quote) {
        return quoteDao.insertQuote(quote)
    }

}