package com.ameliariely.callswiper

import android.app.Application
import android.arch.persistence.room.Room
import com.ameliariely.callswiper.data.AppDatabase
import com.ameliariely.callswiper.data.DbHelper
import com.ameliariely.callswiper.data.model.Mom

class CallSwiperApp : Application() {

    lateinit var dbHelper: DbHelper

    override fun onCreate() {
        super.onCreate()
        val appDatabase = Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "callMomDatabase").build()
        dbHelper = DbHelper(appDatabase)
        dbHelper.addMom(Mom("5126669312", "Fake Mom 1"))
        dbHelper.addMom(Mom("83274", "Fake Mom 2"))
        dbHelper.addMom(Mom("23948", "Fake Mom 3"))
    }

}