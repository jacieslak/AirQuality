package com.jcieslak.airquality.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jcieslak.airquality.data.model.Station
import com.jcieslak.airquality.data.repository.GiosRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainViewModel @ViewModelInject constructor(
    private val giosRepository: GiosRepository
) : ViewModel() {
    val stationListLiveData: MutableLiveData<List<Station>> by lazy {
        MutableLiveData<List<Station>>()
    }
    val filteredStationListLiveData: MutableLiveData<List<Station>> by lazy {
        MutableLiveData<List<Station>>()
    }
    private var compositeDisposable = CompositeDisposable()
    var searchDisposable: Disposable? = null

    init {
        compositeDisposable.add(giosRepository.getAllStations()
            .flatMapIterable { it }
            .flatMap { station ->
                giosRepository.getStationSensors(station.id).toObservable()
                    .flatMap {
                        station.sensors = it
                        return@flatMap Observable.just(station)
                    }
            }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ stations ->
                stationListLiveData.value = stations.sortedBy { it.city.name }
            }, {
                //TODO show error message
                Timber.e(it)
            })


        )
    }

    public fun getFilteredStationList(query: String?) {
        //TODO create better search logic
        query?.let {
            val queryToLowerCase = query.toLowerCase()
            searchDisposable?.dispose()
            searchDisposable = Observable.fromCallable {
                stationListLiveData.value?.let { stations ->
                    stations.filter { station ->
                        (station.city.name.toLowerCase().contains(queryToLowerCase)
                                || station.addressStreet?.let {
                            it.toLowerCase().contains(queryToLowerCase)
                        } ?: false //return false when addressStreet is null
                                )
                    }
                } ?: listOf()
            }
                .delay(200, TimeUnit.MILLISECONDS) //TODO move delay to const
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    filteredStationListLiveData.value = it
                }, {
                    filteredStationListLiveData.value = stationListLiveData.value
                })
        }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}