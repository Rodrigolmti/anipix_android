package com.rodrigolmti.anipix.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodrigolmti.anipix.R
import com.rodrigolmti.library.controller.view.BaseFragment

/**
 * Created by rodrigolmti on 10/12/17.
 */
class UpdatesFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_updates, container, false)
    }

    companion object {
        fun newInstance(): UpdatesFragment {
            return UpdatesFragment()
        }
    }
}