package com.rodrigolmti.anipix.model.controller

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by rodrigolmti on 08/12/17.
 */
class PreferencesController(context: Context) {

    private val ANIPIX = "anipix_preference"
    private val FAVORITE = "favorite"
    private val ORDERS = "orders"
    private val preferences: SharedPreferences = context.getSharedPreferences(ANIPIX, 0)

    var favorites: String
        get() = preferences.getString(FAVORITE, "favorite")
        set(string) = preferences.edit().putString(FAVORITE, string).apply()

    var orders: String
        get() = preferences.getString(ORDERS, "order")
        set(string) = preferences.edit().putString(ORDERS, string).apply()
}