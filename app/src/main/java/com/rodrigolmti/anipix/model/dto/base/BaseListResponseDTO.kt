package com.rodrigolmti.anipix.model.dto.base

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class BaseListResponseDTO<out Any> (
        val success: Boolean,
        val token: String,
        val data: List<Any>
)