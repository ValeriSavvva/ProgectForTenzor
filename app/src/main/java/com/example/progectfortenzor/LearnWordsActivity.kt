package com.example.progectfortenzor

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView

class LearnWordsActivity : Activity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_learnwords)
        val cardOfWord: CardView? = findViewById(R.id.cardOfWord)
        val textInfo: TextView? = findViewById(R.id.info_text)
        cardOfWord?.setOnClickListener {
            cardOfWord.rotationX = 0f
            cardOfWord.animate().apply {
                rotationX(90f)
                start()
            }
        }
    }

}