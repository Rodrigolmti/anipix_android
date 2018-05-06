package com.rodrigolmti.anipix.view.activity

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.ads.AdRequest
import com.rodrigolmti.anipix.R
import com.rodrigolmti.anipix.R.string
import com.rodrigolmti.anipix.model.callback.CallBackEpisode
import com.rodrigolmti.anipix.model.controller.FavoriteController
import com.rodrigolmti.anipix.model.dto.AnimeDTO
import com.rodrigolmti.anipix.model.dto.EpisodeDTO
import com.rodrigolmti.anipix.model.service.AnipixService
import com.rodrigolmti.anipix.model.utils.gone
import com.rodrigolmti.anipix.model.utils.visible
import com.rodrigolmti.anipix.view.adapter.EpisodeAdapter
import com.rodrigolmti.library.controller.view.BaseActivity
import kotlinx.android.synthetic.main.activity_anime_detail.contentLoading
import kotlinx.android.synthetic.main.activity_anime_detail.imageViewAnime
import kotlinx.android.synthetic.main.activity_anime_detail.recyclerView
import kotlinx.android.synthetic.main.activity_anime_detail.textViewAno
import kotlinx.android.synthetic.main.activity_anime_detail.textViewCategorias
import kotlinx.android.synthetic.main.activity_anime_detail.textViewSinopse
import kotlinx.android.synthetic.main.activity_anime_detail.toolbar
import kotlinx.android.synthetic.main.activity_anime_search.adView

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class AnimeDetailActivity : BaseActivity(), CallBackEpisode {

    private lateinit var anime: AnimeDTO
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_detail)
        setSupportActionBar(toolbar)
        enableBackButton()
        loadView()
        initAd()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_anime_detail_favorite, menu)
        onChangeAnimeFavorite(menu!!.findItem(R.id.menu_favorite))
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_favorite -> {
                if (FavoriteController.onCheckIsFavorite(anime)) {
                    item.icon = ContextCompat.getDrawable(this, R.drawable.ic_action_favorite)
                    FavoriteController.onRemoveFavorite(anime)
                } else {
                    item.icon = ContextCompat.getDrawable(this, R.drawable.ic_action_favorite_ativated)
                    FavoriteController.onAddFavorite(anime)
                }
            }
            android.R.id.home -> finish()
        }
        return true
    }

    private fun onChangeAnimeFavorite(item: MenuItem) {
        if (!FavoriteController.onCheckIsFavorite(anime)) {
            item.icon = ContextCompat.getDrawable(this, R.drawable.ic_action_favorite)
            FavoriteController.onRemoveFavorite(anime)
        } else {
            item.icon = ContextCompat.getDrawable(this, R.drawable.ic_action_favorite_ativated)
            FavoriteController.onAddFavorite(anime)
        }
    }

    override fun onSuccessGetEpisodes(episodes: List<EpisodeDTO>) {
        contentLoading.gone()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.hasFixedSize()
        recyclerView.adapter = EpisodeAdapter(this, episodes, object : EpisodeAdapter.OnItemClick {
            override fun onItemClick(episode: EpisodeDTO) {
                val options = arrayOf<CharSequence>("Player 1", "Player 2")
                val builder = AlertDialog.Builder(this@AnimeDetailActivity)
                builder.setTitle(getString(string.select_one_player))
                builder.setItems(options, { _, index ->
                    val intent = Intent(this@AnimeDetailActivity, PlayerActivity::class.java)
                    intent.putExtra("action.item.number", episode.numero)
                    intent.putExtra("action.item.id", episode.id)
                    intent.putExtra("action.item.link", index)
                    startActivity(intent)
                })
                builder.show()
            }
        })
    }

    override fun onErrorGetEpisodes() {
    }

    private fun loadView() {
        if (intent.hasExtra("action.item")) {
            anime = intent.getSerializableExtra("action.item") as AnimeDTO
            imageViewAnime.setImageURI(Uri.parse(anime.imagem))
            anime.favorite = FavoriteController.onCheckIsFavorite(anime)
            title = anime.nome
            if (anime.sinopse != null && !anime.sinopse.isEmpty()) {
                textViewSinopse.text = anime.sinopse.replace("Sinopse: ", "")
            }
            textViewAno.text = anime.ano
            textViewCategorias.text = anime.categorias
            contentLoading.visible()
            kotlin.run {
                AnipixService().getEpisodeByAnimeId(this, anime.id)
            }
        }
    }

    private fun initAd() {
        val adRequest = AdRequest.Builder()
                .addTestDevice(getString(string.ad_device_one_plus))
                .build()
        adView.loadAd(adRequest)
    }
}