package com.jcieslak.airquality.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.jcieslak.airquality.R
import com.jcieslak.airquality.base.DataBindingActivity
import com.jcieslak.airquality.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : DataBindingActivity(), SearchView.OnQueryTextListener {

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)
    private val viewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            vm = viewModel
            rvStations.addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = StationAdapter().apply {
                itemClick = { station ->
                    startActivity(StationDetailsActivity.createIntent(this@MainActivity, station))
                }
            }
        }
        registerObservables()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        searchItem?.expandActionView()

        val searchView = searchItem?.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(this)
        return true
    }

    @SuppressLint("ShowToast")
    private fun registerObservables() {
        viewModel.stationListLiveData.observe(this, Observer { stations ->
            binding.adapter?.setStationList(stations)
        })

        viewModel.filteredStationListLiveData.observe(this, Observer { stations ->
            binding.adapter?.setStationList(stations)
        })

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT)
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        viewModel.getFilteredStationList(query)
        return true;
    }


}