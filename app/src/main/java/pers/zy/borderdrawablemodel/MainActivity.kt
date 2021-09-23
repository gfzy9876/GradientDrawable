package pers.zy.borderdrawablemodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import pers.zy.borderlib.drawable.GradientBorderBuilder
import pers.zy.borderlib.drawable.GradientBorderDrawable

class MainActivity : AppCompatActivity() {

    private lateinit var gradientBorderDrawable: GradientBorderDrawable
    private var radiusType = GradientBorderDrawable.RadiusType.ALL
    private var radiusPx: Float = 20f.dpF

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gradientBorderDrawable =
        /*GradientBorderDrawable(
            borderColors = intArrayOf(resources.getColor(R.color.border_left_color), resources.getColor(R.color.border_right_color)), // 边框色，支持多色
            bgColors = intArrayOf(resources.getColor(R.color.inner_left_color), resources.getColor(R.color.inner_right_color)),  // 背景色，支持多色
            borderWidth = 5f.dpF, // 边框宽度 单位px
            radius = radiusPx, // 圆角 单位px
            borderAngle = GradientBorderDrawable.ANGLE_LEFT_RIGHT, // 边框多色值情况下，选择渐变方向
            bgAngle = GradientBorderDrawable.ANGLE_LEFT_RIGHT // 背景色多色值情况下，选择渐变方向
        )*/

        GradientBorderDrawable(
            borderColors = intArrayOf(resources.getColor(R.color.border_left_color), resources.getColor(R.color.border_right_color)), // 边框色，支持多色
            bgColors = intArrayOf(resources.getColor(R.color.inner_left_color), resources.getColor(R.color.inner_right_color)), // 背景色，支持多色
            borderWidth = 5f.dpF, // 边框宽度 单位px
            radius = radiusPx, // 圆角 单位px
            radiusType = GradientBorderDrawable.RadiusType.RT, //指定四个角的哪几个角有圆角（当需要圆角角度一样时才能使用）
            borderAngle = GradientBorderDrawable.ANGLE_LEFT_TOP_BOTTOM_RIGHT, // 边框多色值情况下，选择渐变方向
            bgAngle = GradientBorderDrawable.ANGLE_LEFT_BOTTOM_RIGHT_TOP // 背景色多色值情况下，选择渐变方向
        )

        /*GradientBorderDrawable(
            borderColors = intArrayOf(resources.getColor(R.color.border_left_color), resources.getColor(R.color.border_right_color)), // 边框色，支持多色
            bgColors = intArrayOf(resources.getColor(R.color.inner_left_color), resources.getColor(R.color.inner_right_color)), // 背景色，支持多色
            borderWidth = 5f.dpF, // 边框宽度 单位px
            // 圆角半径 单位px，分别指定4个圆角半径，共8个元素，每个圆角两个数组元素，分别表示x方向圆角半径 和 y方向圆角半径
            // 索引和对应值：0,1 左上角; 2,3 右上角; 4,5 右下角; 6,7 左下角
            radii = floatArrayOf(20f.dpF, 20f.dpF, 50f.dpF, 50f.dpF, 0f, 0f, 100f.dpF, 100f.dpF),
            borderAngle = GradientBorderDrawable.ANGLE_LEFT_TOP_BOTTOM_RIGHT, // 边框多色值情况下，选择渐变方向
            bgAngle = GradientBorderDrawable.ANGLE_LEFT_BOTTOM_RIGHT_TOP // 背景色多色值情况下，选择渐变方向
        )*/

        /*GradientBorderBuilder()
            .setBorderColors(intArrayOf(resources.getColor(R.color.border_left_color), resources.getColor(R.color.border_right_color))) // 边框色，支持多色
            .setBgColors(intArrayOf(resources.getColor(R.color.inner_left_color), resources.getColor(R.color.inner_right_color))) //背景色，支持多色
            .setBorderWidth(5f.dpF) // 边框宽度 单位px
//            .setRadius(radius) // 圆角半径 单位px，直接指定4个圆角半径
            // 圆角半径 单位px，分别指定4个圆角半径，共8个元素，每个圆角两个数组元素，分别表示x方向圆角半径 和 y方向圆角半径，
            // 索引和对应值：0,1 左上角; 2,3 右上角; 4,5 右下角; 6,7 左下角
//            .setRadii(floatArrayOf(radiusPx, radiusPx, radiusPx, radiusPx, radiusPx, radiusPx, radiusPx, radiusPx))
            .setRadiusWithRadiusType(radiusPx, GradientBorderDrawable.RadiusType.L)
            .setBorderAngle(GradientBorderDrawable.ANGLE_LEFT_BOTTOM_RIGHT_TOP) // 边框多色值情况下，选择渐变方向
            .setBgAngle(GradientBorderDrawable.ANGLE_LEFT_BOTTOM_RIGHT_TOP) // 背景色多色值情况下，选择渐变方向
            .build()*/

        tv_test.background = gradientBorderDrawable
        initView()
    }

    private fun initView() {
        radius_seek.setOnSeekBarChangeListener(object : AbsSeekBarChangeAdapter() {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val progressDb = 100f * progress / 100f
                radiusPx = progressDb.dpF
                tv_radius.text = "$progressDb dp"
                gradientBorderDrawable.updateRadiusWithRadiusType(radiusPx, radiusType)
            }
        })


        border_width.setOnSeekBarChangeListener(object : AbsSeekBarChangeAdapter() {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateBorderWidth(25f * progress / 100f)
            }
        })

        rg_radius_type.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                radius_type_all.id -> radiusType = GradientBorderDrawable.RadiusType.ALL
                radius_type_lt.id -> radiusType = GradientBorderDrawable.RadiusType.LT
                radius_type_lb.id -> radiusType = GradientBorderDrawable.RadiusType.LB
                radius_type_rt.id -> radiusType = GradientBorderDrawable.RadiusType.RT
                radius_type_rb.id -> radiusType = GradientBorderDrawable.RadiusType.RB
                radius_type_l.id -> radiusType = GradientBorderDrawable.RadiusType.L
                radius_type_r.id -> radiusType = GradientBorderDrawable.RadiusType.R
                radius_type_t.id -> radiusType = GradientBorderDrawable.RadiusType.T
                radius_type_b.id -> radiusType = GradientBorderDrawable.RadiusType.B
                radius_type_lt_rb.id -> radiusType = GradientBorderDrawable.RadiusType.LT_RB
                radius_type_lb_rt.id -> radiusType = GradientBorderDrawable.RadiusType.LB_RT
                radius_type_e_lt.id -> radiusType = GradientBorderDrawable.RadiusType.EXCEPT_LT
                radius_type_e_lb.id -> radiusType = GradientBorderDrawable.RadiusType.EXCEPT_LB
                radius_type_e_rt.id -> radiusType = GradientBorderDrawable.RadiusType.EXCEPT_RT
                radius_type_e_rb.id -> radiusType = GradientBorderDrawable.RadiusType.EXCEPT_RB
            }
            gradientBorderDrawable.updateRadiusWithRadiusType(radiusPx, radiusType)
        }
    }

    private fun updateBorderWidth(borderWidth: Float) {
        gradientBorderDrawable.updateBorderWidth(borderWidth.dpF)
        tv_border_width.text = "$borderWidth dp"
    }

    open class AbsSeekBarChangeAdapter : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) { }

        override fun onStartTrackingTouch(seekBar: SeekBar?) { }

        override fun onStopTrackingTouch(seekBar: SeekBar?) { }
    }

    private val Float.dpF: Float
        get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, App.INSTANCE.resources.displayMetrics)
}