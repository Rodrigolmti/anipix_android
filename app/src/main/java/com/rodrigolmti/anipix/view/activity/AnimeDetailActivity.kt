package com.rodrigolmti.anipix.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.rodrigolmti.anipix.R
import com.rodrigolmti.anipix.model.callback.CallBackEpisode
import com.rodrigolmti.anipix.model.dto.AnimeDTO
import com.rodrigolmti.anipix.model.dto.EpisodeDTO
import com.rodrigolmti.anipix.model.service.AnipixService
import com.rodrigolmti.anipix.model.utils.gone
import com.rodrigolmti.anipix.model.utils.visible
import com.rodrigolmti.anipix.view.adapter.EpisodeAdapter
import com.rodrigolmti.library.controller.view.BaseActivity
import kotlinx.android.synthetic.main.activity_anime_detail.*

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class AnimeDetailActivity : BaseActivity(), CallBackEpisode {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_detail)
        setSupportActionBar(toolbar)
        enableBackButton()
        if (intent.hasExtra("action.item")) {
            val anime = intent.getSerializableExtra("action.item") as AnimeDTO
            imageViewAnime.setImageURI(Uri.parse(anime.imagem))
            title = anime.nome
            textViewSinopse.text = anime.sinopse
            textViewAno.text = anime.ano
            textViewCategorias.text = anime.categorias
            contentLoading.visible()
            kotlin.run {
                AnipixService(this).getEpisodeByAnimeId(this, anime.id)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_anime_detail, menu)
        return true
    }

    override fun onSuccessGetEpisodes(episodes: List<EpisodeDTO>) {
        contentLoading.gone()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.hasFixedSize()
        recyclerView.adapter = EpisodeAdapter(this, episodes, object : EpisodeAdapter.OnItemClick {
            override fun onItemClick(episode: EpisodeDTO) {
                val intent = Intent(this@AnimeDetailActivity, EpisodeActivity::class.java)
                intent.putExtra("action.item.id", episode.id)
                intent.putExtra("action.item.number", episode.numero)
                startActivity(intent)
            }
        })
    }

    override fun onErrorGetEpisodes() {
    }
}