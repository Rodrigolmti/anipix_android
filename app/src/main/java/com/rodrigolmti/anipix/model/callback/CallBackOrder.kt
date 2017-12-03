package com.rodrigolmti.anipix.model.callback

import com.rodrigolmti.anipix.model.dto.OrderDTO

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
interface CallBackOrder {

    fun onSuccessGetOrders(orders: List<OrderDTO>)
    fun onErrorGetOrders()
}