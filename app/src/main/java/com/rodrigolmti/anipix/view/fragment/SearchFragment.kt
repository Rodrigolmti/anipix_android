package com.rodrigolmti.anipix.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.anipix.R
import com.rodrigolmti.library.controller.view.BaseFragment

class SearchFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_search, container, false)
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}