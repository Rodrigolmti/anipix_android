package com.rodrigolmti.anipix.model.callback

import com.rodrigolmti.anipix.model.dto.EpisodeDTO

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
interface CallBackEpisode {

    fun onSuccessGetEpisodes(episodes: List<EpisodeDTO>)
    fun onErrorGetEpisodes()
}