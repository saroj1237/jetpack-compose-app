package com.example.myapplication.utility

import android.content.Context
import android.content.SharedPreferences


class LocalStorage(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    companion object{
        private const val TOKEN_KEY = "token_key"
    }
    fun saveToken(token:String){
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply();
    }
    fun getToken():String?{
      return  sharedPreferences.getString(TOKEN_KEY,null)
    }
    fun deleteToken(){
        sharedPreferences.edit().remove(TOKEN_KEY).apply();
    }
}