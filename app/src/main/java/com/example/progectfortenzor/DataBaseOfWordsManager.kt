package com.example.progectfortenzor

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DataBaseOfWordsManager(context: Context) {
    val dbHelper = DataBaseOfWordsHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB(){
        db = dbHelper.writableDatabase
    }
    fun insertToDb(enWord: String,ruWord: String){
        val values = ContentValues().apply {
            put(DataBaseOfWords.COLUMN_NAME_EN_WORD,enWord)
            put(DataBaseOfWords.COLUMN_NAME_RU_WORD,ruWord)
        }
        db?.insert(DataBaseOfWords.TABLE_NAME,null,values)
    }

    fun readDb():ArrayList<String>{
        val dataList = ArrayList<String>()
        val cursor = db?.query(DataBaseOfWords.TABLE_NAME,null,null,
                null,null,null,null)
        while(cursor?.moveToNext()!!){
            val dataText = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseOfWords.COLUMN_NAME_EN_WORD))
            dataList.add(dataText.toString())
        }
        cursor.close()
        return dataList
    }
    fun closeDB(){
        dbHelper.close()
    }
}
