package com.jcieslak.airquality.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jcieslak.airquality.R
import com.jcieslak.airquality.data.model.Station
import com.jcieslak.airquality.databinding.ItemStationBinding

class StationAdapter  : RecyclerView.Adapter<StationAdapter.StationViewHolder>() {

    private var items: List<Station> = listOf()

    class StationViewHolder(val binding: ItemStationBinding) :
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemStationBinding>(inflater, R.layout.item_station, parent, false)
        return StationViewHolder(binding)
    }

    fun setStationList(stationList: List<Station>) {
        items = stationList
        notifyDataSetChanged()

    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            station = item
            executePendingBindings()
        }
    }
}