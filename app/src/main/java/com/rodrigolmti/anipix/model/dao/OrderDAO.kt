package com.rodrigolmti.anipix.model.dao

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rodrigolmti.anipix.model.controller.PreferencesController
import com.rodrigolmti.anipix.model.dto.OrderDTO

/**
 * Created by rodrigolmti on 09/12/17.
 */
class OrderDAO {

    companion object {

        private lateinit var context: Context

        fun saveOrders(orders: List<OrderDTO>) {
            val stringOrders: String = Gson().toJson(orders)
            if (stringOrders.isEmpty()) return
            PreferencesController(context).orders = stringOrders
        }

        fun getOrders(): List<OrderDTO> {
            try {
                val stringOrders: String = PreferencesController(context).orders
                if (stringOrders.isEmpty()) return ArrayList()
                val listType = object : TypeToken<ArrayList<OrderDTO>>() {}.type
                return Gson().fromJson(stringOrders, listType)
            } catch (error: Exception) {
                error.printStackTrace()
                return ArrayList()
            }
        }

        fun init(context: Context) {
            this.context = context
        }
    }
}