package com.rodrigolmti.anipix.model.utils

import com.rodrigolmti.anipix.model.dto.AnimeDTO

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
interface CallBackAnime {
    fun onSuccessGetAnimes(animes: List<AnimeDTO>)
    fun onErrorGetAnimes()
}