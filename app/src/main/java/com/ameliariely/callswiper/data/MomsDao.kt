package com.ameliariely.callswiper.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.ameliariely.callswiper.data.model.Mom
import io.reactivex.Flowable

@Dao
interface MomsDao {

    @Query("SELECT * FROM moms")
    fun getMoms(): Flowable<List<Mom>>
    //TODO what does single do and which single import should I pick...?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mom: Mom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(mom: ArrayList<Mom>)
}