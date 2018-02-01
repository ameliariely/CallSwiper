package com.ameliariely.callswiper.data.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "moms")
public class Mom {

    public Mom(@NonNull String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    @PrimaryKey
    @NonNull
    private String phone = "";

    @ColumnInfo(name = "name")
    private String name;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
