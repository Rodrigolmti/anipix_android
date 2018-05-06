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
import com.rodrigolmti.anipix.model.utils.gone
import com.rodrigolmti.anipix.model.utils.visible
import com.rodrigolmti.anipix.view.activity.AnimeDetailActivity
import com.rodrigolmti.anipix.view.adapter.AnimeSearchAdapter
import com.rodrigolmti.anipix.view.adapter.AnimeSearchAdapter.OnItemClick
import com.rodrigolmti.library.controller.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteFragment : BaseFragment() {

    private var favorites: MutableList<AnimeDTO> = ArrayList()
    private var fragmentView: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater!!.inflate(R.layout.fragment_favorite, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (view != null) {
            favorites.addAll(FavoriteController.onSearchFavorites())
            if (favorites.isNotEmpty()) {
                view.recyclerView.layoutManager = LinearLayoutManager(activity)
                view.recyclerView.hasFixedSize()
                view.recyclerView.adapter = AnimeSearchAdapter(activity, favorites, object : OnItemClick {
                    override fun onItemClick(anime: AnimeDTO) {
                        val intent = Intent(activity, AnimeDetailActivity::class.java)
                        intent.putExtra("action.item", anime)
                        startActivity(intent)
                    }
                })
                view.recyclerView.visible()
                view.textViewEmpty.gone()
            } else {
                view.textViewEmpty.visible()
                view.recyclerView.gone()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        favorites.clear()
        favorites.addAll(FavoriteController.onSearchFavorites())
        if (fragmentView != null && favorites.isNotEmpty()) {
            fragmentView!!.recyclerView.adapter.notifyDataSetChanged()
        }
    }

    companion object {
        fun newInstance(): FavoriteFragment {
            return FavoriteFragment()
        }
    }
}