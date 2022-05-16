package com.example.progectfortenzor

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SearchView
import android.widget.Switch
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception


class AddWordsActivity : Activity() {

    private val TAG = "AddWordsAPI"
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
                showTranslateWord("Переводим..")
                addWord.visibility = View.VISIBLE
                translateWord(p0,langSource,langTarget)
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







    fun translateWord(word: String?,source: String,target: String){
        var result = ""
        GlobalScope.launch(Dispatchers.IO){
            Log.v(TAG, "Зашёл");
            Log.v(TAG, "Зашёл в трай");
            val client = OkHttpClient()
            Log.v(TAG, "создал клиент");
            val mediaType = "application/json".toMediaTypeOrNull()
            Log.v(TAG, "создал медиатайп");
            val body = RequestBody.create(mediaType, "{\r\"q\": \"$word\",\r\"source\": \"$source\",\r\"target\": \"$target\"\r }")
            Log.v(TAG, "создал бади");
            val request = Request.Builder()
                .url("https://deep-translate1.p.rapidapi.com/language/translate/v2")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("X-RapidAPI-Host", "deep-translate1.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "afb4f1a598mshcb3b1b298dc69a3p199e4fjsnda81d5c95bee")
                .build()
            Log.v(TAG, "создал реквест");
            try{
                val response = client.newCall(request).execute()
                Log.v(TAG, "вызвал реквест");
                var str = response.body!!.string()
                Log.v(TAG, "создал строку из реквеста");
                try {
                    val jsonObject: JSONObject = JSONObject(str)
                    Log.v(TAG, "создал джсон объект");
                    result = jsonObject.getJSONObject("data").getJSONObject("translations").getString("translatedText")
                    Log.v(TAG, "получили результат $result");
                    showTranslateWord(result)
                }
                catch (e: JSONException) {
                    Log.v(TAG, "$e");
                }

            }catch (e:Exception){
                Log.v(TAG, "$e");
            }
        }
    }
    fun showTranslateWord(word:String){
        val descriptionWords: TextView = findViewById(R.id.translateWord)
        descriptionWords.setText(word)
    }
}






