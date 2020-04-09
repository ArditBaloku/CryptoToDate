package com.arditb.cryptotodate.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arditb.cryptotodate.R
import com.arditb.cryptotodate.databinding.FragmentDetailBinding


/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentDetailBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        return binding.root
    }

}
