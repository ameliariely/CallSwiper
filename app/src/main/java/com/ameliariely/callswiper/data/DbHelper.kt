package com.ameliariely.callswiper.data

import android.util.Log
import com.ameliariely.callswiper.data.model.Mom
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

interface DbHelper {

    fun getMoms(): Flowable<List<Mom>>
    fun addMom(mom: Mom)
}

class AppDbHelper(private val appDatabase: AppDatabase) : DbHelper {

    override fun getMoms(): Flowable<List<Mom>> {
        return appDatabase.momsDao().getMoms().filter { it.isNotEmpty() }
                .doOnNext {
                    Log.d("DbHelper", "Dispatching ${it.size} contacts from DB...")
                }
    }

    override fun addMom(mom: Mom) {
        Flowable.fromCallable { appDatabase.momsDao().insert(mom) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Log.d("DbHelper", "Inserted a mom into DB...")
                }
    }
}