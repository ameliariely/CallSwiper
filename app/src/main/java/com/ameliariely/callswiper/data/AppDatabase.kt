package com.ameliariely.callswiper.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ameliariely.callswiper.data.model.Mom

@Database(entities = [Mom::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun momsDao(): MomsDao
}