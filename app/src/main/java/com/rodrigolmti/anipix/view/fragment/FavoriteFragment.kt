package com.rodrigolmti.anipix.view.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.anipix.R
import com.rodrigolmti.anipix.model.controller.FavoriteController
import com.rodrigolmti.anipix.model.dto.AnimeDTO
import com.rodrigolmti.anipix.view.activity.AnimeDetailActivity
import com.rodrigolmti.anipix.view.adapter.AnimeSearchAdapter
import com.rodrigolmti.anipix.view.adapter.AnimeSearchAdapter.OnItemClick
import com.rodrigolmti.library.controller.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_favorite.view.recyclerView

class FavoriteFragment : BaseFragment() {

    private var favorites: List<AnimeDTO> = ArrayList()
    private var fragmentView: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater!!.inflate(R.layout.fragment_favorite, container, false)
        if (fragmentView != null) {
            favorites = FavoriteController.onSearchFavorites()
            fragmentView!!.recyclerView.layoutManager = LinearLayoutManager(activity)
            fragmentView!!.recyclerView.hasFixedSize()
            fragmentView!!.recyclerView.adapter = AnimeSearchAdapter(activity, favorites, object : OnItemClick {
                override fun onItemClick(anime: AnimeDTO) {
                    val intent = Intent(activity, AnimeDetailActivity::class.java)
                    intent.putExtra("action.item", anime)
                    startActivity(intent)
                }
            })
        }

        return fragmentView
    }

    override fun onResume() {
        super.onResume()
        if (fragmentView != null) {
            favorites = FavoriteController.onSearchFavorites()
            fragmentView!!.recyclerView.adapter.notifyDataSetChanged()
        }
    }

    companion object {
        fun newInstance(): FavoriteFragment {
            return FavoriteFragment()
        }
    }
}