package com.rodrigolmti.anipix.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.anipix.R
import com.rodrigolmti.anipix.model.dto.EpisodeDTO
import kotlinx.android.synthetic.main.row_episode.view.*

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class EpisodeAdapter(private val context: Context, private val episodes: List<EpisodeDTO>,
                     private val listener: OnItemClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as ItemViewHolder).bindData(episodes[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.row_episode, parent, false))
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(episode: EpisodeDTO, listener: OnItemClick) {
            itemView.setOnClickListener { listener.onItemClick(episode) }
            itemView.textViewEpisodeNumber.text = episode.numero.toString()
        }
    }

    interface OnItemClick {
        fun onItemClick(episode: EpisodeDTO)
    }
}