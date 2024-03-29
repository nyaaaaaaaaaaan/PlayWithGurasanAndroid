package com.aochdjp.playwithgura_san.viewmodel

import android.arch.lifecycle.*
import android.databinding.ObservableField
import com.aochdjp.playwithgura_san.model.Stream
import com.aochdjp.playwithgura_san.model.repositories.streamApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class StreamViewModel : ViewModel(), LifecycleObserver {
    val streams = MutableLiveData<Stream>()
    val isLoading = ObservableField<Boolean>(false)
    private val disposeBug = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getStreamList() {
        streamApi.stream()
            .doOnSubscribe { isLoading.set(true) }
            .doFinally { isLoading.set(false) }
            .subscribeBy(
                onNext = {
                    streams.postValue(it)
                }
            ).addTo(disposeBug)
    }

    override fun onCleared() {
        super.onCleared()
        disposeBug.clear()
    }
}