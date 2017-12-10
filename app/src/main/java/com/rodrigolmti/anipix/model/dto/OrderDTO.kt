package com.rodrigolmti.anipix.model.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class OrderDTO(
        @Expose
        @SerializedName("_id")
        val id: String,
        @Expose
        val word: String
)