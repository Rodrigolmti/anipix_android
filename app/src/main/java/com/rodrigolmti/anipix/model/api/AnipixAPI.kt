package com.rodrigolmti.anipix.model.api

import com.rodrigolmti.anipix.model.dto.AnimeDTO
import com.rodrigolmti.anipix.model.dto.BaseResponseDTO
import com.rodrigolmti.anipix.model.dto.OrderDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import rx.Observable

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
interface AnipixAPI {

    @GET("orderList")
    fun getOrderList(@Header("token") token: String): Observable<BaseResponseDTO<OrderDTO>>

    @GET("animeByOrder")
    fun getAnimeByOrderId(@Header("token") token: String, @Query("orderId") orderId: String):
            Observable<BaseResponseDTO<AnimeDTO>>

    @GET("orderList")
    fun getEpisodeByAnimeId(@Header("token") token: String): Observable<BaseResponseDTO<AnimeDTO>>
}