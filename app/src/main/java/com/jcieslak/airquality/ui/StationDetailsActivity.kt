package com.jcieslak.airquality.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.jcieslak.airquality.R
import com.jcieslak.airquality.base.DataBindingActivity
import com.jcieslak.airquality.data.model.Marker
import com.jcieslak.airquality.data.model.Station
import com.jcieslak.airquality.databinding.ActivityStationDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class StationDetailsActivity : DataBindingActivity() {

    private val binding: ActivityStationDetailsBinding by binding(R.layout.activity_station_details)
    private val viewModel by viewModels<StationDetailsViewModel>()
    private val simpleDateFormat = SimpleDateFormat(DATE_FORMAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            vm = viewModel
            viewModel.station = intent.getParcelableExtra(EXTRA_STATION)
            viewModel.getSensorsData()
            tvTitle.text = viewModel.station.stationName
        }

        registerObservables()
    }

    private fun registerObservables() {
        // Zip all sensors call
        // Return all sensors data as a list
        viewModel.sensorsDataListLiveData.observe(this, Observer { sensorDataList ->
            sensorDataList.forEach { sensorData ->
                val markerList = arrayListOf<Marker>()
                val dayList = arrayListOf<String>()
                val maxResult =
                    if (sensorData.values.size > MAX_CHART_RESULT) MAX_CHART_RESULT else sensorData.values.size
                for (index in 0..maxResult) {
                    sensorData.values[index]
                    markerList.add(Marker(value = sensorData.values[index].value))
                    dayList.add(
                        sensorData.values[index].date
                    )
                    val valueDate = Calendar.getInstance()
                    valueDate.time = simpleDateFormat.parse(sensorData.values[index].date)
                }

                //TODO move to separate fun fix problems with show graphs
                when (sensorData.key) {
                    "PM10" -> {
                        binding.containerPm10.visibility = View.VISIBLE
                        val maxSensorValue = sensorData.values.maxBy { it.value }?.value
                        binding.graphPm10.setMarkersAnDays(markerList, dayList,
                            (maxSensorValue?.toInt()?.let { it } ?: 0), 10)
                    }
                    "CO" -> {
                        binding.containerCo2.visibility = View.VISIBLE
                        val maxSensorValue = sensorData.values.maxBy { it.value }?.value
                        binding.graphCo2.setMarkersAnDays(markerList, dayList,
                            (maxSensorValue?.toInt()?.let { it } ?: 0), 10)
                    }
                    "O3" -> {
                        binding.containerO3.visibility = View.VISIBLE
                        val maxSensorValue = sensorData.values.maxBy { it.value }?.value
                        binding.graphO3.setMarkersAnDays(markerList, dayList,
                            (maxSensorValue?.toInt()?.let { it } ?: 0), 10)
                    }
                    "NO2" -> {
                        binding.containerNo2.visibility = View.VISIBLE
                        val maxSensorValue = sensorData.values.maxBy { it.value }?.value
                        binding.graphNo2.setMarkersAnDays(markerList, dayList,
                            (maxSensorValue?.toInt()?.let { it } ?: 0), 10)
                    }

                    "C6H6" -> {
                        binding.containerC6h6.visibility = View.VISIBLE
                        val maxSensorValue = sensorData.values.maxBy { it.value }?.value
                        binding.graphC6h6.setMarkersAnDays(markerList, dayList,
                            (maxSensorValue?.toInt()?.let { it } ?: 0), 10)
                    }


                }

            }
        })


        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT)
        })
    }

    private fun isTodayValue(day: Calendar): Boolean {
        val today = Calendar.getInstance()
        return today.get(Calendar.DAY_OF_MONTH) - day.get(Calendar.DAY_OF_MONTH) < 3

    }

    companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
        private const val MAX_CHART_RESULT = 10
        private const val EXTRA_STATION = "EXTRA_STATION"

        fun createIntent(context: Context, station: Station): Intent {
            val intent = Intent(context, StationDetailsActivity::class.java)
            return intent.putExtra(EXTRA_STATION, station)
        }
    }
}