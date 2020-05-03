package com.arditb.cryptotodate.converter

import android.os.Bundle
import android.util.Log
import androidx.fragment.  app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.arditb.cryptotodate.MainActivity

import com.arditb.cryptotodate.R
import com.arditb.cryptotodate.database.getDatabase
import com.arditb.cryptotodate.databinding.FragmentConverterBinding
import com.arditb.cryptotodate.utils.getIndex
import kotlinx.android.synthetic.main.fragment_converter.*
import kotlinx.android.synthetic.main.fragment_settings.view.*
import kotlinx.coroutines.*


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

            val temp = first_currency.text.toString()
            first_currency.setText(second_currency.text.toString())
            second_currency.setText(temp)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        second_currency.isEnabled = false

        val database = getDatabase((activity as MainActivity).application)

        val job = Job()
        val scope = CoroutineScope(Dispatchers.IO + job)

        convertBtn.setOnClickListener {
            if (first_currency.text.toString().isNotEmpty()) {
                val firstCurrValue = first_currency.text.toString().toFloat()

                val firstCurrId = convert_from_spinner.selectedItem.toString()
                Log.i("First currency ID", convert_from_spinner.selectedItem.toString())
                val secondCurrId = convert_to_spinner.selectedItem.toString()

                scope.launch {
                    val firstCurr = database.cryptoDao.getCryptoById(firstCurrId)
                    val secondCurr = database.cryptoDao.getCryptoById(secondCurrId)

                    Log.i("First currency", firstCurr.toString())
                    Log.i("Second currency", secondCurr.toString())

                    val secondCurrValue = firstCurr.price / secondCurr.price

                    val ratio = secondCurrValue * firstCurrValue

                    withContext(Dispatchers.Main) {
                        second_currency.setText(ratio.toString())
                    }
                }
            }
        }
    }

}
