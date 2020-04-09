package com.arditb.cryptotodate.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders


import com.arditb.cryptotodate.R
import com.arditb.cryptotodate.databinding.FragmentOverviewBinding
import com.arditb.cryptotodate.network.CryptoItem


/**
 * A simple [Fragment] subclass.
 * Use the [OverviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val adapter = CryptoItemAdapter()
        binding.cryptoItemList.adapter = adapter

        viewModel.properties.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        return binding.root
    }
}
