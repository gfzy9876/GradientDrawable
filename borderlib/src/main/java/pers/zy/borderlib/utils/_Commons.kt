package pers.zy.borderlib.utils

import android.util.TypedValue
import pers.zy.borderlib.BorderLibApp as App

/**
 * date: 2020/7/1   time: 7:32 PM
 * author zy
 * Have a nice day :)
 **/

val Float.dp: Int
    get() {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, App.instance.resources.displayMetrics).toInt()
    }

val Float.dpF: Float
    get() {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, App.instance.resources.displayMetrics)
    }