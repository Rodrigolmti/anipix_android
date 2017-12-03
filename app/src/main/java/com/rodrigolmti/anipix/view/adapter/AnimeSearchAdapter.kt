package com.rodrigolmti.anipix.view.adapter

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.anipix.R
import com.rodrigolmti.anipix.model.dto.AnimeDTO
import kotlinx.android.synthetic.main.row_anime.view.*

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class AnimeSearchAdapter(private val context: Context, private val animes: List<AnimeDTO>,
                         private val listener: OnItemClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as ItemViewHolder).bindData(animes[position], position, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.row_anime, parent, false))
    }

    override fun getItemCount(): Int {
        return animes.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(anime: AnimeDTO, position: Int, listener: OnItemClick) {
            itemView.setOnClickListener { listener.onItemClick(position) }
            itemView.textViewAnimeName.text = anime.nome
            itemView.imageViewAnime.setImageURI(Uri.parse(anime.imagem))
        }
    }

    interface OnItemClick {
        fun onItemClick(position: Int)
    }
}