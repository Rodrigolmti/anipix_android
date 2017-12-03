package com.rodrigolmti.anipix.view.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.anipix.R
import com.rodrigolmti.anipix.model.dto.OrderDTO
import com.rodrigolmti.anipix.model.service.AnipixService
import com.rodrigolmti.anipix.model.utils.CallBackOrders
import com.rodrigolmti.anipix.model.utils.gone
import com.rodrigolmti.anipix.model.utils.visible
import com.rodrigolmti.anipix.view.activity.AnimeSearchResultActivity
import com.rodrigolmti.anipix.view.adapter.OrderAnimeAdapter
import com.rodrigolmti.anipix.view.adapter.OrderAnimeAdapter.OnItemClick
import com.rodrigolmti.library.controller.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_animes.view.*

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class AnimesFragment : BaseFragment(), CallBackOrders {

    private lateinit var viewFragment: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewFragment = inflater!!.inflate(R.layout.fragment_animes, container, false)

        viewFragment.contentLoading.visible()
        kotlin.run {
            AnipixService(activity).getOrderList(this)
        }

        return viewFragment
    }

    override fun onSuccessGetOrders(orders: List<OrderDTO>) {
        viewFragment.contentLoading.gone()
        viewFragment.recyclerView.layoutManager = LinearLayoutManager(activity)
        viewFragment.recyclerView.hasFixedSize()
        val ordersString = resources.getStringArray(R.array.anime_order)
        viewFragment.recyclerView.adapter = OrderAnimeAdapter(activity, ordersString, object : OnItemClick {
            override fun onItemClick(position: Int) {
                if (orders.isNotEmpty()) {
                    val intent = Intent(activity, AnimeSearchResultActivity::class.java)
                    intent.putExtra("action.order.id", orders[position].id)
                    startActivity(intent)
                }
            }
        })
    }

    override fun onErrorGetOrders() {
        viewFragment.contentLoading.gone()
    }

    companion object {
        fun newInstance(): AnimesFragment {
            return AnimesFragment()
        }
    }
}