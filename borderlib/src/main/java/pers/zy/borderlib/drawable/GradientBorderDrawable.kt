package pers.zy.borderlib.drawable

import android.graphics.*
import android.graphics.drawable.Drawable
import pers.zy.borderlib.utils.dpF

/**
 * date: 2020/8/27   time: 4:07 PM
 * author zy
 * Have a nice day :)
 **/
class GradientBorderDrawable(private var borderColors: IntArray = DEFAULT_COLORS,
    private var bgColors: IntArray = DEFAULT_COLORS,
    private var borderWidth: Float = DEFAULT_BORDER_WIDTH,
    private var radii: FloatArray = floatArrayOf(DEFAULT_RADIUS, DEFAULT_RADIUS, DEFAULT_RADIUS, DEFAULT_RADIUS, DEFAULT_RADIUS, DEFAULT_RADIUS, DEFAULT_RADIUS, DEFAULT_RADIUS),
    private val borderAngle: Int = ANGLE_LEFT_RIGHT,
    private var bgAngle: Int = ANGLE_LEFT_RIGHT) : Drawable() {

    companion object {
        @JvmField val DEFAULT_COLORS = intArrayOf(Color.TRANSPARENT, Color.TRANSPARENT)
        @JvmField val DEFAULT_BORDER_WIDTH = 5f.dpF
        @JvmField val DEFAULT_RADIUS = 10f.dpF

        const val ANGLE_LEFT_RIGHT = 1
        const val ANGLE_TOP_BOTTOM = 2
        const val ANGLE_LEFT_TOP_BOTTOM_RIGHT = 3
        const val ANGLE_LEFT_BOTTOM_RIGHT_TOP = 4

        @JvmStatic fun createRadiiByType(radius: Float, radiusType: RadiusType): FloatArray {
            return when (radiusType) {
                RadiusType.LT -> floatArrayOf(radius, radius, 0f, 0f, 0f, 0f, 0f, 0f)
                RadiusType.LB -> floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, radius, radius)
                RadiusType.RT -> floatArrayOf(0f, 0f, radius, radius, 0f, 0f, 0f, 0f)
                RadiusType.RB -> floatArrayOf(0f, 0f, 0f, 0f, radius, radius, 0f, 0f)
                RadiusType.L -> floatArrayOf(radius, radius, 0f, 0f, 0f, 0f, radius, radius)
                RadiusType.R -> floatArrayOf(0f, 0f, radius, radius, radius, radius, 0f, 0f)
                RadiusType.T -> floatArrayOf(radius, radius, radius, radius, 0f, 0f, 0f, 0f)
                RadiusType.B -> floatArrayOf(0f, 0f, 0f, 0f, radius, radius, radius, radius)
                RadiusType.LT_RB -> floatArrayOf(radius, radius, 0f, 0f, radius, radius, 0f, 0f)
                RadiusType.LB_RT -> floatArrayOf(0f, 0f, radius, radius, 0f, 0f, radius, radius)
                RadiusType.EXCEPT_LT -> floatArrayOf(0f, 0f, radius, radius, radius, radius, radius, radius)
                RadiusType.EXCEPT_LB -> floatArrayOf(radius, radius, radius, radius, radius, radius, 0f, 0f)
                RadiusType.EXCEPT_RT -> floatArrayOf(radius, radius, 0f, 0f, radius, radius, radius, radius)
                RadiusType.EXCEPT_RB -> floatArrayOf(radius, radius, radius, radius, 0f, 0f, radius, radius)
                else -> floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)
            }
        }
    }

    enum class RadiusType {
        // 四个角
        ALL,
        // 左上角、左下角、右上角、右下角
        LT, LB, RT, RB,
        // 左上角和左下角、右上角和右下角、左上角和右上角、左下角和右下角、左上角和右下角、左下角和右上角
        L, R, T, B, LT_RB, LB_RT,
        // 除了左上角、除了左下角、除了右上角、除了右下角
        EXCEPT_LT, EXCEPT_LB, EXCEPT_RT, EXCEPT_RB
    }

    constructor(borderColors: IntArray, bgColors: IntArray, borderWidth: Float, radius: Float, borderAngle: Int, bgAngle: Int) : this(
        borderColors,
        bgColors,
        borderWidth,
        createRadiiByType(radius, RadiusType.ALL),
        borderAngle,
        bgAngle
    )

    constructor(borderColors: IntArray, bgColors: IntArray, borderWidth: Float, radius: Float, radiusType: RadiusType, borderAngle: Int, bgAngle: Int) : this(
        borderColors,
        bgColors,
        borderWidth,
        createRadiiByType(radius, radiusType),
        borderAngle,
        bgAngle
    )

    private val roundRectF = RectF()
    private val drawRectF = RectF()
    private val drawPath = Path()
    private val bgPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
        style = Paint.Style.FILL
    }
    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
        style = Paint.Style.STROKE
    }

    init {
        setStrokeWidth(borderWidth)
        updateShader()
    }

    fun updateRadius(radius: Float) {
        this.radii = floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)
        invalidateSelf()
    }

    fun updateRadii(radii: FloatArray) {
        this.radii = radii
        invalidateSelf()
    }

    fun updateRadiusWithRadiusType(radius: Float, radiusType: RadiusType) {
        this.radii = createRadiiByType(radius, radiusType)
        invalidateSelf()
    }

    fun updateBorderWidth(borderWidth: Float) {
        this.borderWidth = borderWidth
        setStrokeWidth(borderWidth)
        invalidateSelf()
    }

    private fun setStrokeWidth(borderWidth: Float) {
        borderPaint.strokeWidth = borderWidth
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
        drawBorder(canvas)
        drawInner(canvas)
    }

    private fun drawBorder(canvas: Canvas) {
        val space = borderWidth / 2f
        drawRectF.set(
            space,
            space,
            roundRectF.width() - space,
            roundRectF.height() - space
        )
        drawPath.reset()
        drawPath.addRoundRect(drawRectF, radii, Path.Direction.CCW)
        canvas.drawPath(drawPath, borderPaint)
    }

    private fun drawInner(canvas: Canvas) {
        val space = borderWidth / 2f
        drawRectF.set(
            borderWidth,
            borderWidth,
            roundRectF.width() - borderWidth,
            roundRectF.height() - borderWidth
        )
        drawPath.reset()
        val bgRadii = FloatArray(radii.size) {
            radii[it] - space
        }
        drawPath.addRoundRect(drawRectF, bgRadii, Path.Direction.CCW)
        canvas.drawPath(drawPath, bgPaint)
    }

    override fun setAlpha(alpha: Int) {
        bgPaint.alpha = alpha
        borderPaint.alpha = alpha
    }

    override fun getOpacity(): Int = PixelFormat.TRANSPARENT

    override fun setColorFilter(colorFilter: ColorFilter?) { }
}