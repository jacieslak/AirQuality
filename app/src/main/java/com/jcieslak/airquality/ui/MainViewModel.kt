package com.jcieslak.airquality.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jcieslak.airquality.data.model.Station
import com.jcieslak.airquality.data.repository.GiosRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class MainViewModel @ViewModelInject constructor(
    private val giosRepository: GiosRepository
) : ViewModel() {
    val stationListLiveData: MutableLiveData<List<Station>> by lazy {
        MutableLiveData<List<Station>>()
    }
    var compositeDisposable = CompositeDisposable()

    init {
        compositeDisposable.add(giosRepository.getAllStations()
            .flatMapIterable { it }
            .flatMap { station ->
                giosRepository.getStationSensors(station.id).toObservable()
                    .flatMap { station.sensors = it
                        return@flatMap Observable.just(station) }
            }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
               stationListLiveData.value = it
            }, {
                Timber.e(it)
            })


        )
    }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}