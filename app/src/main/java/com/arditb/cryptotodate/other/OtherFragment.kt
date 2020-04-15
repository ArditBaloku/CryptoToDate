package com.arditb.cryptotodate.other

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi

import com.arditb.cryptotodate.R
import kotlinx.android.synthetic.main.fragment_other.view.*


class OtherFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_other, container, false)
        val vibrator:Vibrator = activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        var effect: VibrationEffect = VibrationEffect.createOneShot(350, VibrationEffect.DEFAULT_AMPLITUDE  )
        view.btnVibrate.setOnClickListener{

            vibrator.vibrate(effect)

        }
        return view
    }

}
