package pers.zy.borderdrawablemodel

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import pers.zy.borderlib.drawable.GradientBorderDrawable
import pers.zy.borderlib.utils.dp
import pers.zy.borderlib.utils.dpF

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_test.background = GradientBorderDrawable(
            Color.BLACK, Color.GREEN,
            Color.TRANSPARENT, Color.TRANSPARENT,
            borderWidth = 5f.dpF,
            corner = 0.5f.dpF,
            borderAngle = GradientBorderDrawable.ANGLE_LEFT_BOTTOM_RIGHT_TOP,
            bgAngle = GradientBorderDrawable.ANGLE_LEFT_BOTTOM_RIGHT_TOP
        )
        initView()

    }

    private fun initView() {
        corner_seek.setOnSeekBarChangeListener(object : AbsSeekBarChangeAdapter() {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateCorner(100f * progress / 100f)
            }
        })
        tv_corner.text = "corner: ${0.5f.dpF} dp"


        border_width.setOnSeekBarChangeListener(object : AbsSeekBarChangeAdapter() {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateBorderWidth(25f * progress / 100f)
            }
        })
        tv_border_width.text = "border width: ${5f.dpF} dp"
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