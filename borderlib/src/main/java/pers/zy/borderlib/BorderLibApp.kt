package pers.zy.borderlib

import android.app.Application

/**
 * date: 2020/8/27   time: 7:10 PM
 * author zy
 * Have a nice day :)
 */
internal class BorderLibApp : Application() {

    companion object {
        @JvmStatic
        lateinit var instance: BorderLibApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}