package com.ameliariely.callswiper

import android.app.Application
import android.arch.persistence.room.Room
import com.ameliariely.callswiper.data.AppDatabase
import com.ameliariely.callswiper.data.AppDbHelper

class CallSwiperApp : Application() {

    override fun onCreate() {
        super.onCreate()
        val appDatabase = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "callMomDatabase").build()
        val dbHelper = AppDbHelper(appDatabase)
    }
}