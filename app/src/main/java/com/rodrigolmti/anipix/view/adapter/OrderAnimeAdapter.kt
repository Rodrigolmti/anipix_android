package com.rodrigolmti.anipix.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.anipix.R
import kotlinx.android.synthetic.main.row_anime_order.view.*

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class OrderAnimeAdapter(private val context: Context, private val orders: Array<String>,
                        private val listener: OnItemClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as ItemViewHolder).bindData(orders[position], position, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.row_anime_order, parent, false))
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: String, position: Int, listener: OnItemClick) {
            itemView.setOnClickListener { listener.onItemClick(position) }
            itemView.textViewOrder.text = item
        }
    }

    interface OnItemClick {
        fun onItemClick(position: Int)
    }
}