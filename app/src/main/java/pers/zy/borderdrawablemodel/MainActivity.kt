package pers.zy.borderdrawablemodel

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import pers.zy.borderlib.drawable.GradientBorderBuilder
import pers.zy.borderlib.drawable.GradientBorderDrawable
import pers.zy.borderlib.utils.dpF

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_test.background =
//        GradientBorderDrawable(
//            borderColors = intArrayOf(Color.GREEN, Color.RED), // 边框色，支持多色
//            bgColors = intArrayOf(Color.TRANSPARENT, Color.TRANSPARENT), //背景色，支持多色
//            borderWidth = 5f.dpF, //边框宽度 单位px
//            corner = 0.5f.dpF, //圆角 单位px
//            borderAngle = GradientBorderDrawable.ANGLE_LEFT_TOP_BOTTOM_RIGHT, //边框多色值情况下，选择渐变方向
//            bgAngle = GradientBorderDrawable.ANGLE_LEFT_BOTTOM_RIGHT_TOP // 背景色多色值情况下，选择渐变方向
//        )
        GradientBorderBuilder()
            .setBorderColors(intArrayOf(Color.GREEN, Color.RED)) // 边框色，支持多色
            .setBgColors(intArrayOf(Color.TRANSPARENT, Color.TRANSPARENT)) //背景色，支持多色
            .setBorderWidth(5f.dpF) //边框宽度 单位px
            .setCorner(0.5f.dpF) //圆角 单位px
            .setBorderAngle(GradientBorderDrawable.ANGLE_LEFT_BOTTOM_RIGHT_TOP) //边框多色值情况下，选择渐变方向
            .setBgAngle(GradientBorderDrawable.ANGLE_LEFT_BOTTOM_RIGHT_TOP) // 背景色多色值情况下，选择渐变方向
            .build()
        initView()

    }

    private fun initView() {
        corner_seek.setOnSeekBarChangeListener(object : AbsSeekBarChangeAdapter() {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateCorner(100f * progress / 100f)
            }
        })
        tv_corner.text = "corner: ${0.5f} dp"


        border_width.setOnSeekBarChangeListener(object : AbsSeekBarChangeAdapter() {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateBorderWidth(25f * progress / 100f)
            }
        })
        tv_border_width.text = "border width: ${5f} dp"
    }

    fun updateCorner(corner: Float) {
        val drawable = tv_test.background as GradientBorderDrawable
        drawable.updateCorner(corner.dpF)
        tv_corner.text = "corner: $corner dp"
    }

    private fun updateBorderWidth(borderWidth: Float) {
        val drawable = tv_test.background as GradientBorderDrawable
        drawable.updateBorderWidth(borderWidth.dpF)
        tv_border_width.text = "border width: $borderWidth dp"
    }

    open class AbsSeekBarChangeAdapter : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) { }

        override fun onStartTrackingTouch(seekBar: SeekBar?) { }

        override fun onStopTrackingTouch(seekBar: SeekBar?) { }
    }
}