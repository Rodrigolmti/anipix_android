package com.rodrigolmti.anipix.model.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class EpisodeDTO(
        @SerializedName("_id")
        @Expose
        val id: String,
        @Expose
        val animeId: String,
        @Expose
        val numero: Int
)