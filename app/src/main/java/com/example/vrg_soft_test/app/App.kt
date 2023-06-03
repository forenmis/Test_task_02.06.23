package com.example.vrg_soft_test.app

import android.app.Application
import android.content.Context
import com.example.vrg_soft_test.data.network.NetworkManager

class App : Application() {

    lateinit var networkManager: NetworkManager

    override fun onCreate() {
        super.onCreate()
        networkManager = NetworkManager()
    }

    companion object {
        fun getInstance(context: Context): App = context.applicationContext as App
    }
}