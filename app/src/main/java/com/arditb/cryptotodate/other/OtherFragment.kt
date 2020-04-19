package com.arditb.cryptotodate.other

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
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
import kotlinx.android.synthetic.main.fragment_other.*
import kotlinx.android.synthetic.main.fragment_other.view.*
import java.io.IOException


class OtherFragment : Fragment(), SensorEventListener {

    var sensor: Sensor ? = null
    var sensorManager: SensorManager? = null

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        try
        {
            if(event!!.values[0] < 30 )
            {
                display_img.visibility = View.VISIBLE
            }
            else
            {
                display_img.visibility = View.INVISIBLE
            }
        }catch (e: IOException)
        {

        }
    }

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
        view.display_img.visibility = View.INVISIBLE

        sensorManager = activity!!.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
        val vibrator:Vibrator = activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        var effect: VibrationEffect = VibrationEffect.createOneShot(350, VibrationEffect.DEFAULT_AMPLITUDE  )
        view.btnVibrate.setOnClickListener{

            vibrator.vibrate(effect)

        }
        return view
    }


    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

}
