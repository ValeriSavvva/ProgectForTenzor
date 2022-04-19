package com.example.progectfortenzor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAddWords: Button?= findViewById(R.id.addWords)
        buttonAddWords?.setOnClickListener {
            val intent = Intent(this@MainActivity, AddWords::class.java)
            startActivity(intent)
        }
        val buttonRepeatWords: Button? = findViewById(R.id.repeatWords)
        buttonRepeatWords?.setOnClickListener {
            val intent = Intent(this@MainActivity, LearnWords::class.java)
            startActivity(intent)
        }
//        val buttonShowList: Button = findViewById(R.id.showList)
//        buttonAddWords.setOnClickListener {
//            val intent = Intent(this@MainActivity, LearnWords::class.java)
//            startActivity(intent)
//        }
    }
}