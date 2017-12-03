package com.rodrigolmti.anipix.model.utils

import com.rodrigolmti.anipix.model.dto.OrderDTO

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
interface CallBackOrders {

    fun onSuccessGetOrders(orders: List<OrderDTO>)
    fun onErrorGetOrders()
}