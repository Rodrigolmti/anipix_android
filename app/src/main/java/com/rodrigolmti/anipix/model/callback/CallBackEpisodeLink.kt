package com.rodrigolmti.anipix.model.callback

import com.rodrigolmti.anipix.model.dto.EpisodeLinkDTO

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
interface CallBackEpisodeLink {

    fun onSuccessGetLinks(episode: EpisodeLinkDTO)
    fun onErrorGetLinks()
}