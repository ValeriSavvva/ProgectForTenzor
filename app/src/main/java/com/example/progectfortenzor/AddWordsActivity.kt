package com.example.progectfortenzor

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import java.io.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import java.net.URL


class AddWordsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addwords)

        var langSource = "ru"
        var langTarget = "en"
        val searchWord: SearchView = findViewById(R.id.searchWord)
        val switchLanguage: Switch = findViewById(R.id.switchLanguage)
        val descriptionWords: TextView = findViewById(R.id.translateWord)
        val addWord: Button = findViewById(R.id.addWord)
        val dbManager = DataBaseOfWordsManager(this)

        searchWord.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                descriptionWords.visibility = View.VISIBLE
                addWord.visibility = View.VISIBLE
                showTranslateWord(p0)
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

        switchLanguage.setOnCheckedChangeListener { compoundButton, b ->
            if(b) {
                switchLanguage.setText(R.string.tranRu)
                langSource = "en"
                langTarget = "ru"
            } else{
                switchLanguage.setText(R.string.tranEn)
                langSource = "ru"
                langTarget = "en"
            }
        }

        addWord?.setOnClickListener{
            dbManager.insertToDb(descriptionWords.text.toString(),descriptionWords.text.toString())
        }
    }
    fun showTranslateWord(word: String?){
        val descriptionWords: TextView = findViewById(R.id.translateWord)
        try {
            val client = OkHttpClient()

            val mediaType = "application/json".toMediaTypeOrNull()
            val body = RequestBody.create(mediaType, "{\r\"q\": \"$word\",\r\"source\": \"ru\",\r\"target\": \"en\"\r }")
            val request = Request.Builder()
                .url("https://deep-translate1.p.rapidapi.com/language/translate/v2")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("X-RapidAPI-Host", "deep-translate1.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "afb4f1a598mshcb3b1b298dc69a3p199e4fjsnda81d5c95bee")
                .build()
            print(request)
            val response = client.newCall(request).execute()
            print(response)
            var str = response.body!!.string()
            print(str)
            val jsonObject: JSONObject = JSONObject(str)
            print(jsonObject)
            var result = jsonObject.getJSONObject("data").getJSONObject("translations").getString("translatedText")
            print(result)
            descriptionWords.setText("$result")

        } catch (e:Exception){
            descriptionWords.setText(word)
        }
    }

}






