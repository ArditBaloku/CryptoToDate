package com.arditb.cryptotodate.converter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arditb.cryptotodate.R
import com.arditb.cryptotodate.databinding.FragmentConverterBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ConverterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConverterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentConverterBinding.inflate(inflater)

        return binding.root
    }
}
