package com.arditb.cryptotodate.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil


import com.arditb.cryptotodate.R
import com.arditb.cryptotodate.databinding.FragmentOverviewBinding


/**
 * A simple [Fragment] subclass.
 * Use the [OverviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OverviewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentOverviewBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        return binding.root
    }
}
