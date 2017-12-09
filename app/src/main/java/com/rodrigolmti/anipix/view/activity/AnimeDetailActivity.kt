package com.rodrigolmti.anipix.view.activity

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
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
        if (intent.hasExtra("action.item")) {
            anime = intent.getSerializableExtra("action.item") as AnimeDTO
            imageViewAnime.setImageURI(Uri.parse(anime.imagem))
            anime.favorite = FavoriteController.onCheckIsFavorite(anime)
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
        this.menu = menu
        if (anime.favorite) {
            menuInflater.inflate(R.menu.menu_activity_anime_detail_unfavorite, menu)
        } else {
            menuInflater.inflate(R.menu.menu_activity_anime_detail_favorite, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_favorite -> {
                menu!!.clear()
                menuInflater.inflate(R.menu.menu_activity_anime_detail_unfavorite, menu)
                FavoriteController.onAddFavorite(anime)
            }
            R.id.menu_un_favorite -> {
                menu!!.clear()
                menuInflater.inflate(R.menu.menu_activity_anime_detail_favorite, menu)
                FavoriteController.onRemoveFavorite(anime)
            }
            android.R.id.home -> finish()
        }
        return true
    }

    override fun onSuccessGetEpisodes(episodes: List<EpisodeDTO>) {
        contentLoading.gone()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.hasFixedSize()
        recyclerView.adapter = EpisodeAdapter(this, episodes, object : EpisodeAdapter.OnItemClick {
            override fun onItemClick(episode: EpisodeDTO) {
                val options = arrayOf<CharSequence>("Link 1", "Link2")
                val builder = AlertDialog.Builder(this@AnimeDetailActivity)
                builder.setTitle(getString(string.selecione_um_player))
                builder.setItems(options, { _, index ->
                    val intent = Intent(this@AnimeDetailActivity, EpisodeActivity::class.java)
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
}