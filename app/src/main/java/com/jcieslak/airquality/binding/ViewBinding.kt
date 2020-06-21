package com.jcieslak.airquality.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jcieslak.airquality.data.model.Sensor
import com.jcieslak.airquality.data.model.Station

@BindingAdapter("sensorsList")
fun bindSensors(view: TextView, sensors: List<Sensor>) {
    var sensorListText = ""
     sensors?.let {sensors ->
         sensorListText = sensors.joinToString { it.param.paramFormula }
     }
    view.text = sensorListText
}

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}