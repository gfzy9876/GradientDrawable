package pers.zy.borderlib.utils

import android.util.TypedValue

/**
 * date: 2020/7/1   time: 7:32 PM
 * author zy
 * Have a nice day :)
 **/

val Float.dpF: Float
    get() {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, GradientBorderInit.context.resources.displayMetrics)
    }