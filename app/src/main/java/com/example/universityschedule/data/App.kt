package com.example.universityschedule.data

import android.app.Application

class App: Application() {
    val dataBase by lazy { MainDB.createDataBase(this) }
}