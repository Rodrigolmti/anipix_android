package com.rodrigolmti.anipix.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.anipix.R
import com.rodrigolmti.anipix.model.dto.OrderDTO
import kotlinx.android.synthetic.main.row_anime_order.view.*

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class OrderAnimeAdapter(private val context: Context, private val orders: List<OrderDTO>,
                        private val listener: OnItemClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as ItemViewHolder).bindData(orders[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.row_anime_order, parent, false))
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: OrderDTO, listener: OnItemClick) {
            itemView.setOnClickListener { listener.onItemClick(item) }
            itemView.textViewOrder.text = item.word
        }
    }

    interface OnItemClick {
        fun onItemClick(item: OrderDTO)
    }
}