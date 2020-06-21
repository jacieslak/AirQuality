package com.jcieslak.airquality.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.jcieslak.airquality.DataBindingActivity
import com.jcieslak.airquality.R
import com.jcieslak.airquality.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : DataBindingActivity() {

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)
    val viewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply { vm = viewModel
            rvStations.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            adapter = StationAdapter()
        }
        registerObservables()
    }

    private fun registerObservables() {
        viewModel.stationListLiveData.observe(this, Observer { stations ->
            binding.adapter?.setStationList(stations)
        } )
    }
}