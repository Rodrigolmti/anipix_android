package com.rodrigolmti.anipix.model.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class AnimeDTO(
        @SerializedName("_id")
        @Expose
        val id: String,
        @Expose
        val orderId: String,
        @Expose
        val nome: String,
        @Expose
        val imagem: String,
        @Expose
        val ano: String,
        @Expose
        val sinopse: String,
        @Expose
        val categorias: String
) : Serializable