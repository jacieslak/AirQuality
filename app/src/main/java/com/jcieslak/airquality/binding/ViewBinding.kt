package com.jcieslak.airquality.binding

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jcieslak.airquality.data.model.Sensor
import com.jcieslak.airquality.data.model.Station

@BindingAdapter("sensorsList")
fun bindSensors(view: TextView, sensors: List<Sensor>) {
    var sensorListText = ""
    sensors?.let { sensors ->
        sensorListText = sensors.joinToString { it.param.paramFormula }
    }
    view.text = sensorListText
}

@SuppressLint("SetTextI18n")
@BindingAdapter("stationAddress")
fun bindStationAddress(view: TextView, station: Station) {
    if (station.addressStreet != null) {
        view.text = "${station.city.name} - ${station.addressStreet}"
    } else {
        view.text = station.city.name
    }
}

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}