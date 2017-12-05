package com.rodrigolmti.anipix.model.api

import com.rodrigolmti.anipix.model.dto.*
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

    @GET("animeByOrderId")
    fun getAnimeByOrderId(@Header("token") token: String, @Query("orderId") orderId: String):
            Observable<BaseResponseDTO<AnimeDTO>>

    @GET("animeByName")
    fun getAnimeByName(@Header("token") token: String, @Query("animeName") animeName: String):
            Observable<BaseResponseDTO<AnimeDTO>>

    @GET("episodeByAnimeId")
    fun getEpisodeByAnimeId(@Header("token") token: String, @Query("animeId") animeId: String):
            Observable<BaseResponseDTO<EpisodeDTO>>

    @GET("episodeLinkByEpisodeId")
    fun getEpisodeLinkByEpisodeId(@Header("token") token: String, @Query("episodeId") episodeId: String):
            Observable<ResponseLinkDTO<EpisodeLinkDTO>>
}