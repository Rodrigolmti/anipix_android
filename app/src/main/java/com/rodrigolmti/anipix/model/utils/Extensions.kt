package com.rodrigolmti.anipix.model.utils

import android.view.View

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}