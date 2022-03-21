package com.kotlin.mvvmdemo.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kotlin.mvvmdemo.Entities.Quote

@Dao
interface QuoteDao {

    @Query("SELECT * FROM QUOTE")
    fun getQuotes(): LiveData<List<Quote>>

    @Insert()
    suspend fun insertQuote(quote: Quote)
}