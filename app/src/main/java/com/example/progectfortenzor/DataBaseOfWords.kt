package com.example.progectfortenzor

import android.provider.BaseColumns

object DataBaseOfWords {
    const val TABLE_NAME = "WordsWithTranslate"
    const val COLUMN_NAME_EN_WORD = "WordInEnglish"
    const val COLUMN_NAME_RU_WORD = "WordInRussian"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "ProjectForTenzor.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXIST $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_EN_WORD TEXT,$COLUMN_NAME_RU_WORD TEXT)"
    const val DElETE_TABLE ="DROP TABLE IF EXIST $TABLE_NAME"
}