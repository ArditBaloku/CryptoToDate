package com.arditb.cryptotodate.converter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.arditb.cryptotodate.R
import com.arditb.cryptotodate.databinding.FragmentConverterBinding
import com.arditb.cryptotodate.utils.getIndex


class ConverterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentConverterBinding.inflate(inflater)

        val currencies_from = binding.convertFromSpinner
        ArrayAdapter.createFromResource(context!!
            ,R.array.crypto_currencies,android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                currencies_from.adapter = adapter
            }

        val currencies_to = binding.convertToSpinner
        ArrayAdapter.createFromResource(context!!
            ,R.array.crypto_currencies,android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                currencies_to.adapter = adapter
            }
        binding.swapCurrencies.setOnClickListener{
            val first = currencies_from.selectedItem.toString()
            currencies_from.setSelection(getIndex(currencies_from, currencies_to.selectedItem.toString()))
            currencies_to.setSelection(getIndex(currencies_to, first))
        }
        return binding.root
    }

}
