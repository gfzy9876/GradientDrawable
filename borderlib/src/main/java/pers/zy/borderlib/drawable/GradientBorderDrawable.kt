package pers.zy.borderlib.drawable

import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import pers.zy.borderlib.utils.dpF

/**
 * date: 2020/8/27   time: 4:07 PM
 * author zy
 * Have a nice day :)
 **/
class GradientBorderDrawable(private var borderColors: IntArray = intArrayOf(DEFAULT_COLOR, DEFAULT_COLOR),
                             private var bgColors: IntArray = intArrayOf(DEFAULT_COLOR, DEFAULT_COLOR),
                             private var borderWidth: Float = DEFAULT_BORDER_WIDTH,
                             private var corner: Float = DEFAULT_CORNER,
                             private val borderAngle: Int = ANGLE_LEFT_RIGHT,
                             private var bgAngle: Int = ANGLE_LEFT_RIGHT) : Drawable() {

    companion object {
        val DEFAULT_BORDER_WIDTH = 5f.dpF
        val DEFAULT_CORNER = 10f.dpF
        const val DEFAULT_COLOR = Color.TRANSPARENT

        const val ANGLE_LEFT_RIGHT = 1
        const val ANGLE_TOP_BOTTOM = 2
        const val ANGLE_LEFT_TOP_BOTTOM_RIGHT = 3
        const val ANGLE_LEFT_BOTTOM_RIGHT_TOP = 4
    }

    constructor(borderStartColor: Int, borderEndColor: Int, bgStartColor: Int, bgEndColor: Int, borderWidth: Float, corner: Float, borderAngle: Int, bgAngle: Int)
            : this(intArrayOf(borderStartColor, borderEndColor), intArrayOf(bgStartColor, bgEndColor), borderWidth, corner, borderAngle, bgAngle)

    constructor(borderStartColor: Int, borderEndColor: Int, bgStartColor: Int, bgEndColor: Int, borderWidth: Float, corner: Float)
            : this(intArrayOf(borderStartColor, borderEndColor), intArrayOf(bgStartColor, bgEndColor), borderWidth, corner)

    constructor(borderColor: Int, bgColor: Int, borderWidth: Float, corner: Float, borderAngle: Int, bgAngle: Int)
            : this(intArrayOf(borderColor, borderColor), intArrayOf(bgColor, bgColor), borderWidth, corner, borderAngle, bgAngle)

    constructor(borderColor: Int, bgColor: Int, borderWidth: Float, corner: Float)
            : this(intArrayOf(borderColor, borderColor), intArrayOf(bgColor, bgColor), borderWidth, corner = corner)

    private val roundRectF = RectF()
    private val drawRectF = RectF()
    private val bgPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
        style = Paint.Style.FILL_AND_STROKE
    }
    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
        style = Paint.Style.STROKE
    }
    private val clearPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
        style= Paint.Style.STROKE
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    init {
        setStrokeWidth(borderWidth)
        updateShader()
    }

    fun updateCorner(corner: Float) {
        this.corner = corner
        invalidateSelf()
    }

    fun updateBorderWidth(borderWidth: Float) {
        this.borderWidth = borderWidth
        setStrokeWidth(borderWidth)
        invalidateSelf()
    }

    private fun setStrokeWidth(borderWidth: Float) {
        borderPaint.strokeWidth = borderWidth
        clearPaint.strokeWidth = borderWidth
        bgPaint.strokeWidth = borderWidth
    }

    private fun updateShader() {
        bgPaint.shader = getGradient(bgColors, bgAngle)
        borderPaint.shader = getGradient(borderColors, borderAngle)
    }

    private fun getGradient(colors: IntArray, angle: Int): LinearGradient {
        var startX = 0f
        var startY = 0f
        var endX = roundRectF.width()
        var endY = 0f
        when (angle) {
            ANGLE_TOP_BOTTOM -> {
                endX = 0f
                endY = roundRectF.height()
            }
            ANGLE_LEFT_TOP_BOTTOM_RIGHT -> {
                endY = roundRectF.height()
            }
            ANGLE_LEFT_BOTTOM_RIGHT_TOP -> {
                startY = roundRectF.height()
            }
        }
        return LinearGradient(startX, startY, endX, endY, colors, null, Shader.TileMode.CLAMP)
    }

    override fun getIntrinsicWidth(): Int = roundRectF.width().toInt()

    override fun getIntrinsicHeight(): Int = roundRectF.height().toInt()

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        roundRectF.set(bounds)
        updateShader()
    }

    override fun draw(canvas: Canvas) {
        val space = borderWidth / 2f
        drawRectF.set(space, space, roundRectF.width() - space, roundRectF.height() - space)
        val savedCount = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.saveLayer(roundRectF, null)
        } else {
            canvas.saveLayer(roundRectF, null, Canvas.ALL_SAVE_FLAG)
        }
        canvas.drawRoundRect(drawRectF, corner, corner, bgPaint)
        if (borderWidth > 0) {
            canvas.drawRoundRect(drawRectF, corner, corner, clearPaint)
            canvas.drawRoundRect(drawRectF, corner, corner, borderPaint)
        }
        canvas.restoreToCount(savedCount)
    }

    override fun setAlpha(alpha: Int) {
        bgPaint.alpha = alpha
        borderPaint.alpha = alpha
        clearPaint.alpha = alpha
    }

    override fun getOpacity(): Int = PixelFormat.TRANSPARENT

    override fun setColorFilter(colorFilter: ColorFilter?) { }
}