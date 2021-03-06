package com.rodrigolmti.anipix.view.activity

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.halilibo.bettervideoplayer.BetterVideoCallback
import com.halilibo.bettervideoplayer.BetterVideoPlayer
import com.rodrigolmti.anipix.R
import com.rodrigolmti.anipix.model.callback.CallBackEpisodeLink
import com.rodrigolmti.anipix.model.dto.EpisodeLinkDTO
import com.rodrigolmti.anipix.model.service.AnipixService
import com.rodrigolmti.anipix.model.utils.gone
import com.rodrigolmti.anipix.model.utils.visible
import com.rodrigolmti.library.controller.view.BaseActivity
import kotlinx.android.synthetic.main.activity_player.*
import java.lang.Exception

class PlayerActivity : BaseActivity(), CallBackEpisodeLink, BetterVideoCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        loadView()
    }

    override fun onSuccessGetLinks(episode: EpisodeLinkDTO) {
        contentLoading.gone()
        player.setCallback(this)
        val link: Uri
        if (intent.hasExtra("action.item.link")) {
            val linkNumber = intent.getIntExtra("action.item.link", 0)
            link = when (linkNumber) {
                0 -> Uri.parse(episode.link1)
                1 -> Uri.parse(episode.link2)
                else -> Uri.parse(episode.link1)
            }
            player.setSource(link)
            player.start()
        }
    }

    override fun onErrorGetLinks() {
        contentLoading.gone()
    }

    override fun onPrepared(player: BetterVideoPlayer?) {
    }

    override fun onStarted(player: BetterVideoPlayer?) {
    }

    override fun onCompletion(player: BetterVideoPlayer?) {
    }

    override fun onBuffering(percent: Int) {
    }

    override fun onPreparing(player: BetterVideoPlayer?) {
    }

    override fun onError(player: BetterVideoPlayer?, e: Exception?) {
    }

    override fun onToggleControls(player: BetterVideoPlayer?, isShowing: Boolean) {
    }

    override fun onPaused(player: BetterVideoPlayer?) {
    }

    private fun loadView() {
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
        decorView.systemUiVisibility = uiOptions
        hideSupportActionBar()


        if (intent.hasExtra("action.item.id")) {
            title = "Episodio: " + intent.getIntExtra("action.item.number", 1)
            contentLoading.visible()
            kotlin.run {
                AnipixService().getEpisodeLinkByEpisodeId(this,
                        intent.getStringExtra("action.item.id"))
            }
        }
    }
}
