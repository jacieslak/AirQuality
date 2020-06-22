package com.jcieslak.airquality.ui

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jcieslak.airquality.data.model.SensorData
import com.jcieslak.airquality.data.model.Station
import com.jcieslak.airquality.data.repository.GiosRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class StationDetailsViewModel @ViewModelInject constructor(
    private val giosRepository: GiosRepository
) : ViewModel() {
    lateinit var station: Station //TODO intent extras might be null
    private var compositeDisposable = CompositeDisposable()
    val sensorsDataListLiveData: MutableLiveData<List<SensorData>> by lazy {
        MutableLiveData<List<SensorData>>()
    }
    val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val progressBarVisibility = ObservableBoolean(false)
    val noDataTextVisibility = ObservableBoolean(false)
    fun getSensorsData() {
        compositeDisposable.add(Observable.zip(getStateObservableZip()) { t: Array<Any> ->
            return@zip t.filterIsInstance<SensorData>()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressBarVisibility.set(true) }
            .doOnError {
                noDataTextVisibility.set(true)
            }  //TODO show error message
            .doFinally {
                progressBarVisibility.set(false)
            }
            .subscribe {
                sensorsDataListLiveData.value = it
            })
    }

    private fun getStateObservableZip(): List<Observable<SensorData>> {
        var zipList = arrayListOf<Observable<SensorData>>()
        station.sensors.forEach { sensor ->
            zipList.add(giosRepository.getSensorData(sensor.id))
        }
        return zipList;
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}