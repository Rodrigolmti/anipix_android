package com.rodrigolmti.anipix.view.activity

import android.net.Uri
import android.os.Bundle
import com.rodrigolmti.anipix.R
import com.rodrigolmti.anipix.model.callback.CallBackEpisodeLink
import com.rodrigolmti.anipix.model.dto.EpisodeLinkDTO
import com.rodrigolmti.anipix.model.service.AnipixService
import com.rodrigolmti.anipix.model.utils.gone
import com.rodrigolmti.anipix.model.utils.visible
import com.rodrigolmti.library.controller.view.BaseActivity
import kotlinx.android.synthetic.main.activity_episode.*

class EpisodeActivity : BaseActivity(), CallBackEpisodeLink {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode)
        enableBackButton()
        if (intent.hasExtra("action.item.id")) {
            title = "Episodio: " + intent.getIntExtra("action.item.number", 1)
            contentLoading.visible()
            kotlin.run {
                AnipixService(this).getEpisodeLinkByEpisodeId(this,
                        intent.getStringExtra("action.item.id"))
            }
        }
    }

    override fun onSuccessGetLinks(episode: EpisodeLinkDTO) {
        contentLoading.gone()
        viewView.visible()
        viewView.setVideoURI(Uri.parse(episode.link1))
        viewView.start()
    }

    override fun onErrorGetLinks() {
        contentLoading.gone()
    }
}
