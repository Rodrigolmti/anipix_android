package com.rodrigolmti.anipix.model.dto

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class ResponseLinkDTO<out Any>(
        val success: Boolean,
        val data: Any
)