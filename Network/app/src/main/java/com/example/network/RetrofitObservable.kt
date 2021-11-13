package com.example.network

import java.util.*

class RetrofitObservable : Observable() {
    fun notifyObserverWithResponse(response: Any?) {
        setChanged()
        notifyObservers(response)
    }

    companion object {
        private var instance: RetrofitObservable? = null
        fun getInstance(): RetrofitObservable? {
            if (instance == null) {
                instance = RetrofitObservable()
            }
            return instance
        }
    }
}