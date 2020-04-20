package com.arditb.cryptotodate.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.arditb.cryptotodate.R
import com.arditb.cryptotodate.databinding.FragmentDetailBinding


/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application

        val binding = FragmentDetailBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val cryptoItem = DetailFragmentArgs.fromBundle(arguments!!).cryptoItem

        val viewModelFactory = DetailViewModelFactory(cryptoItem, application)

        binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)

        return binding.root
    }

}
