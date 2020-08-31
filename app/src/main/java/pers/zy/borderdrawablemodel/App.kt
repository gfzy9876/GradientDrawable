package pers.zy.borderdrawablemodel

import android.app.Application
import pers.zy.borderlib.utils.GradientBorderInit

/**
 * date: 2020/8/31   time: 6:04 PM
 * author zy
 * Have a nice day :)
 **/
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        GradientBorderInit.init(this)
    }

}