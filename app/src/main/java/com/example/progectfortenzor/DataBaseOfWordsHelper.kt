package com.example.progectfortenzor

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseOfWordsHelper(context: Context) : SQLiteOpenHelper(context, DataBaseOfWords.DATABASE_NAME,
    null, DataBaseOfWords.DATABASE_VERSION)   {
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(DataBaseOfWords.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVer: Int, newVer: Int) {
        db?.execSQL(DataBaseOfWords.DElETE_TABLE)
        onCreate(db)
    }

}