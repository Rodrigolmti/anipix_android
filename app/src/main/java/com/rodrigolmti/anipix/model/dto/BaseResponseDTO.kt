package com.rodrigolmti.anipix.model.dto

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class BaseResponseDTO<out Any> (
        val success: Boolean,
        val token: String,
        val data: List<Any>
)