package com.rodrigolmti.anipix.model.dto.base

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class BaseResponseDTO<out Any>(
        val success: Boolean,
        val data: Any
)