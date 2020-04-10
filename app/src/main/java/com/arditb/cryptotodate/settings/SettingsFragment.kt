package com.arditb.cryptotodate.settings

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.arditb.cryptotodate.R
import kotlinx.android.synthetic.main.fragment_settings.view.*

class SettingsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val currenciesSpinner = view.currenciesSpinner
        ArrayAdapter.createFromResource(context!!
            ,R.array.currencies,android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                currenciesSpinner.adapter = adapter
            }
        val languagesSpinner = view.languagesSpinner
        ArrayAdapter.createFromResource(context!!
            ,R.array.languages,android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                languagesSpinner.adapter = adapter
            }
        return view
    }
}
