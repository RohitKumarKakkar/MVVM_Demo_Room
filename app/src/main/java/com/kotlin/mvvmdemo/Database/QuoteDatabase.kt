package com.kotlin.mvvmdemo.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kotlin.mvvmdemo.DAO.QuoteDao
import com.kotlin.mvvmdemo.Entities.Quote

@Database(entities = [Quote::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

    companion object {

        @Volatile
        private var INSTANCE: QuoteDatabase? = null

        fun getDatabase(context: Context): QuoteDatabase {
            if (INSTANCE == null) {
                synchronized(this) {  // To Create DB in Background Thread , But Only Once
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext, QuoteDatabase::class.java,
                            "quotes"
                        ).createFromAsset("quotes.db").build()
                }
            }
            return INSTANCE!!
        }
    }

}