package com.rodrigolmti.anipix.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import com.google.android.gms.ads.AdRequest
import com.rodrigolmti.anipix.R
import com.rodrigolmti.anipix.R.string
import com.rodrigolmti.anipix.model.callback.CallBackAnime
import com.rodrigolmti.anipix.model.dto.AnimeDTO
import com.rodrigolmti.anipix.model.service.AnipixService
import com.rodrigolmti.anipix.model.utils.gone
import com.rodrigolmti.anipix.model.utils.visible
import com.rodrigolmti.anipix.view.adapter.AnimeSearchAdapter
import com.rodrigolmti.library.controller.view.BaseActivity
import kotlinx.android.synthetic.main.activity_anime_search.*
import java.util.Timer
import java.util.TimerTask
import kotlin.collections.ArrayList
import kotlin.collections.List
import kotlin.collections.MutableList


/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class AnimeSearchActivity : BaseActivity(), CallBackAnime {

    private var animesList: MutableList<AnimeDTO> = ArrayList()
    private var timer: Timer = Timer()
    private val delay: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_search)
        setSupportActionBar(toolbar)
        loadData()
        loadView()
        initAd()
    }

    override fun onSuccessGetAnimes(animes: List<AnimeDTO>) {
        contentLoading.gone()
        progressBar.gone()
        if (animes.isEmpty()) {
            Snackbar.make(content, getString(string.no_search_found), Snackbar.LENGTH_LONG).show()
            textViewNotFound.visible()
            recyclerView.gone()
        } else {
            recyclerView.visible()
            textViewNotFound.gone()
            animesList.addAll(animes)
            recyclerView.adapter.notifyDataSetChanged()
        }
    }

    override fun onErrorGetAnimes() {
        textViewNotFound.visible()
        progressBar.gone()
    }

    private fun searchAnimeByNAme(query: String) {
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                AnipixService().getAnimeByName(this@AnimeSearchActivity, query)
            }
        }, delay)
    }

    private fun loadData() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.hasFixedSize()
        recyclerView.adapter = AnimeSearchAdapter(this, animesList, object : AnimeSearchAdapter.OnItemClick {
            override fun onItemClick(anime: AnimeDTO) {
                val intent = Intent(this@AnimeSearchActivity, AnimeDetailActivity::class.java)
                intent.putExtra("action.item", anime)
                startActivity(intent)
            }
        })
    }

    private fun loadView() {
        if (intent.hasExtra("action.order.id")) {
            contentLoading.visible()
            kotlin.run {
                AnipixService().getAnimeByOrderId(this, intent.getStringExtra("action.order.id"))
            }
        }
        imageViewBack.setOnClickListener({ _ ->
            finish()
        })

        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                timer.cancel()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.length > 3) {
                    progressBar.visible()
                    searchAnimeByNAme(s.toString())
                } else {
                    recyclerView.adapter.notifyDataSetChanged()
                    animesList.clear()
                    progressBar.gone()
                }
            }
        })
    }

    private fun initAd() {
        val adRequest = AdRequest.Builder()
                .addTestDevice(getString(string.ad_device_one_plus))
                .build()
        adView.loadAd(adRequest)
    }
}