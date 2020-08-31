package pers.zy.borderlib.utils

import android.content.Context

/**
 * date: 2020/8/31   time: 6:12 PM
 * author zy
 * Have a nice day :)
 **/
class GradientBorderInit {
    companion object {
        lateinit var context: Context

        @JvmStatic
        fun init(context: Context) {
            this.context = context
        }
    }
}