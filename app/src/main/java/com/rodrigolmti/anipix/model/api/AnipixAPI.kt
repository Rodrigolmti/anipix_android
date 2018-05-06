package com.rodrigolmti.anipix.model.api

import com.rodrigolmti.anipix.model.dto.AnimeDTO
import com.rodrigolmti.anipix.model.dto.EpisodeDTO
import com.rodrigolmti.anipix.model.dto.EpisodeLinkDTO
import com.rodrigolmti.anipix.model.dto.OrderDTO
import com.rodrigolmti.anipix.model.dto.base.BaseListResponseDTO
import com.rodrigolmti.anipix.model.dto.base.BaseResponseDTO
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
    fun getOrderList(@Header("token") token: String): Observable<BaseListResponseDTO<OrderDTO>>

    @GET("animeByOrderId")
    fun getAnimeByOrderId(@Header("token") token: String, @Query("orderId") orderId: String):
            Observable<BaseListResponseDTO<AnimeDTO>>

    @GET("animeByName")
    fun getAnimeByName(@Header("token") token: String, @Query("animeName") animeName: String):
            Observable<BaseListResponseDTO<AnimeDTO>>

    @GET("episodeByAnimeId")
    fun getEpisodeByAnimeId(@Header("token") token: String, @Query("animeId") animeId: String):
            Observable<BaseListResponseDTO<EpisodeDTO>>

    @GET("episodeLinkByEpisodeId")
    fun getEpisodeLinkByEpisodeId(@Header("token") token: String, @Query("episodeId") episodeId: String):
            Observable<BaseResponseDTO<EpisodeLinkDTO>>
}