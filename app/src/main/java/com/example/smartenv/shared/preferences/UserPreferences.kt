package com.example.smartenv.shared.preferences

import android.content.Context
import android.util.Log
import com.example.smartenv.login.data.model.UserData
import com.google.gson.Gson

class UserPreferences(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveUserData(user: UserData) {
        Log.d("UserPreferences", "Saving user data: $user")
        val json = gson.toJson(user)
        sharedPreferences.edit().putString("user_data", json).apply()
    }

    fun getUserData(): UserData? {
        val json = sharedPreferences.getString("user_data", null)
        return json?.let { gson.fromJson(it, UserData::class.java) }
    }

    fun clearUserData() {
        sharedPreferences.edit().remove("user_data").apply()
    }
}
