package com.rodrigolmti.library.controller.view

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.rodrigolmti.anipix.R

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
open class BaseActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    fun changeFragment(fragment: BaseFragment) {
        try {
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.content, fragment)
            transaction.disallowAddToBackStack()
            transaction.commitAllowingStateLoss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected fun enableBackButton() {
        supportActionBar?.let {
            supportActionBar!!.setHomeButtonEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    protected fun removeElevation() {
        supportActionBar?.let {
            supportActionBar!!.elevation = 0F
        }
    }
}