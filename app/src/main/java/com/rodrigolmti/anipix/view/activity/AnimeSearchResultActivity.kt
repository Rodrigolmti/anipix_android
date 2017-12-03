package com.rodrigolmti.anipix.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.rodrigolmti.anipix.R
import com.rodrigolmti.anipix.model.dto.AnimeDTO
import com.rodrigolmti.anipix.model.service.AnipixService
import com.rodrigolmti.anipix.model.utils.CallBackAnime
import com.rodrigolmti.anipix.model.utils.gone
import com.rodrigolmti.anipix.model.utils.visible
import com.rodrigolmti.anipix.view.adapter.AnimeSearchAdapter
import com.rodrigolmti.anipix.view.adapter.OrderAnimeAdapter
import com.rodrigolmti.library.controller.view.BaseActivity
import kotlinx.android.synthetic.main.activity_anime_search_result.*

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class AnimeSearchResultActivity : BaseActivity(), CallBackAnime {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_search_result)
        enableBackButton()
        title = "Animes"
        if (intent.hasExtra("action.order.id")) {
            contentLoading.visible()
            kotlin.run {
                AnipixService(this).getAnimeByOrderId(this,
                        intent.getStringExtra("action.order.id"))
            }
        }
    }

    override fun onSuccessGetAnimes(animes: List<AnimeDTO>) {
        contentLoading.gone()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.hasFixedSize()
        recyclerView.adapter = AnimeSearchAdapter(this, animes, object : AnimeSearchAdapter.OnItemClick {
            override fun onItemClick(position: Int) {

            }
        })
    }

    override fun onErrorGetAnimes() {
    }
}