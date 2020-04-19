package com.arditb.cryptotodate.settings

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter

import com.arditb.cryptotodate.R
import com.arditb.cryptotodate.utils.getIndex
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.view.*
import kotlinx.android.synthetic.main.fragment_settings.view.currenciesSpinner

class SettingsFragment : Fragment(){

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

        currenciesSpinner.setSelection(getIndex(currenciesSpinner, getSavedCurrency()))


        currenciesSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                resultSpinner.text = getSavedCurrency()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                saveCurrency(currenciesSpinner.getItemAtPosition(position).toString())
                resultSpinner.text = getSavedCurrency()
            }
        }

        val languagesSpinner = view.languagesSpinner
        ArrayAdapter.createFromResource(context!!
            ,R.array.languages,android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                languagesSpinner.adapter = adapter
            }

        languagesSpinner.setSelection(getIndex(languagesSpinner, getSavedLanguage()))

        languagesSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                resultLanguages.text = getSavedLanguage()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                saveLanguage(languagesSpinner.getItemAtPosition(position).toString())
                resultLanguages.text = getSavedLanguage()
            }
        }
        return view
    }

    private fun saveLanguage(newLanguage: String) {
        val sharedPref = activity?.getSharedPreferences("CryptoToDateSettings", Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(getString(R.string.language_key), newLanguage)
            commit()
        }
    }

    private fun saveCurrency(newCurrency: String) {
        val sharedPref = activity?.getSharedPreferences("CryptoToDateSettings", Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(getString(R.string.currency_key), newCurrency)
            commit()
        }
    }

    private fun getSavedLanguage(): String {
        val sharedPref = activity?.getSharedPreferences("CryptoToDateSettings", Context.MODE_PRIVATE) ?: return "EN"
        return sharedPref.getString(getString(R.string.language_key), "EN") ?: "EN"
    }


    private fun getSavedCurrency(): String {
        val sharedPref = activity?.getSharedPreferences("CryptoToDateSettings", Context.MODE_PRIVATE) ?: return "USD"
        return sharedPref.getString(getString(R.string.currency_key), "USD") ?: "USD"
    }

 }
