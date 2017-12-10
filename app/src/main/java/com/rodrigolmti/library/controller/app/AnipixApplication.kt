package com.rodrigolmti.library.controller.app

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.android.gms.ads.MobileAds
import com.rodrigolmti.anipix.R.string
import com.rodrigolmti.anipix.model.controller.FavoriteController
import com.rodrigolmti.anipix.model.dao.OrderDAO

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class AnipixApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)
        MobileAds.initialize(this, getString(string.ad_mob_app_id))
        FavoriteController.init(applicationContext)
        OrderDAO.init(applicationContext)
    }
}