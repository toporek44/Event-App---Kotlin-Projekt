package com.example.eventapp.ui.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


fun saveCheckedItems(context: Context, checkedItems: ArrayList<String>, key: String) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val gson = Gson()
    val json = gson.toJson(checkedItems)
    editor.putString(key, json)
    editor.apply()
}


fun getCheckedItems(context: Context, key: String): ArrayList<String> {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val gson = Gson()
    val json = sharedPreferences.getString(key, null)
    val type = object : TypeToken<List<String>>() {}.type
    return gson.fromJson(json, type) ?: ArrayList()
}