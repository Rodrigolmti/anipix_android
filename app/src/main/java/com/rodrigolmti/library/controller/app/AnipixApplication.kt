package com.rodrigolmti.library.controller.app

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class AnipixApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)
    }
}