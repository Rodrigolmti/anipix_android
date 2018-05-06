package com.rodrigolmti.anipix.view.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.anipix.R
import com.rodrigolmti.anipix.model.callback.CallBackOrder
import com.rodrigolmti.anipix.model.dao.OrderDAO
import com.rodrigolmti.anipix.model.dto.OrderDTO
import com.rodrigolmti.anipix.model.service.AnipixService
import com.rodrigolmti.anipix.model.utils.gone
import com.rodrigolmti.anipix.model.utils.visible
import com.rodrigolmti.anipix.view.activity.AnimeSearchActivity
import com.rodrigolmti.anipix.view.adapter.OrderAnimeAdapter
import com.rodrigolmti.anipix.view.adapter.OrderAnimeAdapter.OnItemClick
import com.rodrigolmti.library.controller.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_animes.view.*

/**
 * Created by rodrigo on 12/3/17.
 * At Framework System
 */
class AnimesFragment : BaseFragment(), CallBackOrder {

    private lateinit var viewFragment: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewFragment = inflater!!.inflate(R.layout.fragment_animes, container, false)
        return viewFragment
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewFragment.contentLoading.visible()
        if (OrderDAO.getOrders().isEmpty()) {
            AnipixService().getOrderList(this)
        } else {
            loadData(OrderDAO.getOrders())
        }
    }

    override fun onSuccessGetOrders(orders: List<OrderDTO>) {
        OrderDAO.saveOrders(orders)
        loadData(orders)
    }

    override fun onErrorGetOrders() {
        viewFragment.contentLoading.gone()
    }

    private fun loadData(orders: List<OrderDTO>) {
        viewFragment.recyclerView.layoutManager = LinearLayoutManager(activity)
        viewFragment.recyclerView.hasFixedSize()
        viewFragment.recyclerView.adapter = OrderAnimeAdapter(activity, orders, object : OnItemClick {
            override fun onItemClick(item: OrderDTO) {
                if (orders.isNotEmpty()) {
                    val intent = Intent(activity, AnimeSearchActivity::class.java)
                    intent.putExtra("action.order.id", item.id)
                    startActivity(intent)
                }
            }
        })
        viewFragment.contentLoading.gone()
    }

    companion object {
        fun newInstance(): AnimesFragment {
            return AnimesFragment()
        }
    }
}