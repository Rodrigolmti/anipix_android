package com.rodrigolmti.anipix.view.activity

import android.os.Bundle
import com.rodrigolmti.anipix.R
import com.rodrigolmti.anipix.view.fragment.AnimesFragment
import com.rodrigolmti.anipix.view.fragment.FavoriteFragment
import com.rodrigolmti.anipix.view.fragment.InfoFragment
import com.rodrigolmti.anipix.view.fragment.SearchFragment
import com.rodrigolmti.library.controller.view.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomBar.setOnTabSelectListener { tabId ->
            when (tabId) {
                R.id.tab_search -> {
                    changeFragment(SearchFragment.newInstance())
                    title = "Pesquise pelo nome"
                }
                R.id.tab_animes -> {
                    changeFragment(AnimesFragment.newInstance())
                    title = "Animes de A-Z"
                }
                R.id.tab_favorite -> {
                    changeFragment(FavoriteFragment.newInstance())
                    title = "Animes favoritos"
                }
                R.id.tab_info -> {
                    changeFragment(InfoFragment.newInstance())
                    title = "Informações Anipix"
                }
            }
        }
    }
}
