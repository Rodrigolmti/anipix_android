package com.rodrigolmti.anipix.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.rodrigolmti.anipix.R
import com.rodrigolmti.anipix.view.fragment.AnimesFragment
import com.rodrigolmti.anipix.view.fragment.FavoriteFragment
import com.rodrigolmti.anipix.view.fragment.InfoFragment
import com.rodrigolmti.anipix.view.fragment.UpdatesFragment
import com.rodrigolmti.library.controller.view.BaseActivity
import kotlinx.android.synthetic.main.activity_main.bottomBar

class MainActivity : BaseActivity() {

    private var navigation: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomBar.setOnTabSelectListener { tabId ->
            when (tabId) {
                R.id.tab_updates -> {
                    invalidateOptionsMenu()
                    changeFragment(UpdatesFragment.newInstance())
                    title = "Atualizações"
                }
                R.id.tab_animes -> {
                    invalidateOptionsMenu()
                    if (navigation != null) {
                        menuInflater.inflate(R.menu.menu_fragment_anime, navigation)
                    }
                    changeFragment(AnimesFragment.newInstance())
                    title = "Animes de A-Z"
                }
                R.id.tab_favorite -> {
                    changeFragment(FavoriteFragment.newInstance())
                    title = "Animes favoritos"
                    navigation!!.clear()
                }
                R.id.tab_info -> {
                    changeFragment(InfoFragment.newInstance())
                    title = "Informações Anipix"
                    navigation!!.clear()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_fragment_anime, menu)
        navigation = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        startActivity(Intent(this, AnimeSearchResultActivity::class.java))
        return true
    }
}
