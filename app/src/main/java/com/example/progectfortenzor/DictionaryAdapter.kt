package com.example.progectfortenzor

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class DictionaryAdapter(context: Context) : RecyclerView.Adapter<DictionaryAdapter.WordHolder>() {

    val dbManager = DataBaseOfWordsManager(context)
    class WordHolder(item: View): RecyclerView.ViewHolder(item) {
        val engWordText: TextView = itemView.findViewById(R.id.engWord)
        val rusWordText: TextView = itemView.findViewById(R.id.rusWord)
        fun bind(){
            engWordText.setText("")
            rusWordText.setText("")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}