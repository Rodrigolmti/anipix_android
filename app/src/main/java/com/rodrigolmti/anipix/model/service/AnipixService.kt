package com.rodrigolmti.anipix.model.service

import com.rodrigolmti.anipix.model.api.AnipixAPI
import com.rodrigolmti.anipix.model.api.RetrofitService
import com.rodrigolmti.anipix.model.callback.CallBackAnime
import com.rodrigolmti.anipix.model.callback.CallBackEpisode
import com.rodrigolmti.anipix.model.callback.CallBackEpisodeLink
import com.rodrigolmti.anipix.model.callback.CallBackOrder
import com.rodrigolmti.anipix.model.utils.Constants
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class AnipixService {

    fun getOrderList(callback: CallBackOrder) {
        try {

            Constants.LOCAL_URL_CONNECTION
            RetrofitService().retrofitInstance()
                    .create(AnipixAPI::class.java).getOrderList("")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ data ->
                        if (data.success)
                            callback.onSuccessGetOrders(data.data)
                        else
                            callback.onErrorGetOrders()
                    }, { error ->
                        callback.onErrorGetOrders()
                        error.printStackTrace()
                    })

        } catch (error: Exception) {
            callback.onErrorGetOrders()
        }
    }

    fun getAnimeByOrderId(callback: CallBackAnime, orderId: String) {
        try {

            RetrofitService().retrofitInstance()
                    .create(AnipixAPI::class.java).getAnimeByOrderId("", orderId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ data ->
                        if (data.success)
                            callback.onSuccessGetAnimes(data.data)
                        else
                            callback.onErrorGetAnimes()
                    }, { error ->
                        callback.onErrorGetAnimes()
                        error.printStackTrace()
                    })

        } catch (error: Exception) {
            callback.onErrorGetAnimes()
        }
    }

    fun getAnimeByName(callback: CallBackAnime, name: String) {
        try {

            RetrofitService().retrofitInstance()
                    .create(AnipixAPI::class.java).getAnimeByName("", name)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ data ->
                        if (data.success)
                            callback.onSuccessGetAnimes(data.data)
                        else
                            callback.onErrorGetAnimes()
                    }, { error ->
                        callback.onErrorGetAnimes()
                        error.printStackTrace()
                    })

        } catch (error: Exception) {
            callback.onErrorGetAnimes()
        }
    }

    fun getEpisodeByAnimeId(callback: CallBackEpisode, animeId: String) {
        try {

            RetrofitService().retrofitInstance()
                    .create(AnipixAPI::class.java).getEpisodeByAnimeId("", animeId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ data ->
                        if (data.success)
                            callback.onSuccessGetEpisodes(data.data)
                        else
                            callback.onErrorGetEpisodes()
                    }, { error ->
                        callback.onErrorGetEpisodes()
                        error.printStackTrace()
                    })
        } catch (error: Exception) {
            callback.onErrorGetEpisodes()
        }

    }

    fun getEpisodeLinkByEpisodeId(callback: CallBackEpisodeLink, episodeId: String) {
        try {

            RetrofitService().retrofitInstance()
                    .create(AnipixAPI::class.java).getEpisodeLinkByEpisodeId("", episodeId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ data ->
                        if (data.success)
                            callback.onSuccessGetLinks(data.data)
                        else
                            callback.onErrorGetLinks()
                    }, { error ->
                        callback.onErrorGetLinks()
                        error.printStackTrace()
                    }
                    )
        } catch (error: Exception) {
            callback.onErrorGetLinks()
        }
    }
}