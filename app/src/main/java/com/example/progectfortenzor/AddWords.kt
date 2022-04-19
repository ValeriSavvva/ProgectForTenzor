package com.example.progectfortenzor

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.io.*
import java.net.URL


class AddWords : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addwords)


        val descriptionWords: TextView = findViewById(R.id.translateWord)
        val searchWord: SearchView = findViewById(R.id.searchWord)
        val addWord: Button = findViewById(R.id.addWord)
        searchWord.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                descriptionWords.visibility = View.VISIBLE
                addWord.visibility = View.VISIBLE
                descriptionWords.setText("$p0")
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })

        searchWord.setOnCloseListener(object :SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                descriptionWords.visibility = View.INVISIBLE
                addWord.visibility = View.INVISIBLE
                descriptionWords.setText("")
                searchWord.focusable = View.NOT_FOCUSABLE
                return true
            }
        })

    }
    fun tryToConnection(): String{

        try {
            var str  = ""
            var url: URL = URL(str)
            var connection =  url.openConnection()
            connection.connect()

            var stream: InputStream = connection.getInputStream()
            var reader = BufferedReader(InputStreamReader(stream))

            var buffer: StringBuffer = StringBuffer("")
            var line: String? = ""
            while ((line == reader.readLine())!=null)
            {
                buffer.append(line).append("\n")
            }
            return buffer.toString();

        }
        finally {
        return ""
        }
    }

}






