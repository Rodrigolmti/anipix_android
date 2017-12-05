package com.rodrigolmti.anipix.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextWatcher
import android.view.View
import android.view.View.OnClickListener
import com.rodrigolmti.anipix.R
import com.rodrigolmti.anipix.model.dto.AnimeDTO
import com.rodrigolmti.anipix.model.service.AnipixService
import com.rodrigolmti.anipix.model.callback.CallBackAnime
import com.rodrigolmti.anipix.model.utils.gone
import com.rodrigolmti.anipix.model.utils.visible
import com.rodrigolmti.anipix.view.adapter.AnimeSearchAdapter
import com.rodrigolmti.library.controller.view.BaseActivity
import kotlinx.android.synthetic.main.activity_anime_search_result.*
import android.text.Editable


/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class AnimeSearchResultActivity : BaseActivity(), CallBackAnime {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_search_result)
        setSupportActionBar(toolbar)
        if (intent.hasExtra("action.order.id")) {
            contentLoading.visible()
            kotlin.run {
                AnipixService(this).getAnimeByOrderId(this,
                        intent.getStringExtra("action.order.id"))
            }
        }
        imageViewBack.setOnClickListener({ _ ->
            finish()
        })

        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (count > 3) {
                    progressBar.visible()
                    kotlin.run {
                        AnipixService(this@AnimeSearchResultActivity).getAnimeByName(
                                this@AnimeSearchResultActivity, s.toString())
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })

    }

    override fun onSuccessGetAnimes(animes: List<AnimeDTO>) {
        contentLoading.gone()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.hasFixedSize()
        recyclerView.adapter = AnimeSearchAdapter(this, animes, object : AnimeSearchAdapter.OnItemClick {
            override fun onItemClick(anime: AnimeDTO) {
                val intent = Intent(this@AnimeSearchResultActivity, AnimeDetailActivity::class.java)
                intent.putExtra("action.item", anime)
                startActivity(intent)
            }
        })
    }

    override fun onErrorGetAnimes() {
    }
}