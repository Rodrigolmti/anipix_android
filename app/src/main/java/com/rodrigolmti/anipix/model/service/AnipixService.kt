package com.rodrigolmti.anipix.model.service

import android.content.Context
import com.rodrigolmti.anipix.R
import com.rodrigolmti.anipix.model.api.AnipixAPI
import com.rodrigolmti.anipix.model.api.RetrofitService
import com.rodrigolmti.anipix.model.utils.CallBackAnime
import com.rodrigolmti.anipix.model.utils.CallBackOrders
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class AnipixService(private val context: Context) {

    fun getOrderList(callback: CallBackOrders) {
        RetrofitService().retrofitInstance(context.getString(R.string.base_url))
                .create(AnipixAPI::class.java).getOrderList("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    if (data.success)
                        callback.onSuccessGetOrders(data.data)
                    else
                        callback.onErrorGetOrders()
                })
    }

    fun getAnimeByOrderId(callback: CallBackAnime, orderId: String) {
        RetrofitService().retrofitInstance(context.getString(R.string.base_url))
                .create(AnipixAPI::class.java).getAnimeByOrderId("", orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    if (data.success)
                        callback.onSuccessGetAnimes(data.data)
                    else
                        callback.onErrorGetAnimes()
                })
    }
}