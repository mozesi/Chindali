package com.kauzganga.chindali

import android.app.Application
import android.content.Context

class ChindaliApplication : Application(){
    init{ app=this}
    companion object{
        private lateinit var app: ChindaliApplication
        fun getAppContext(): Context = app.applicationContext
    }
}