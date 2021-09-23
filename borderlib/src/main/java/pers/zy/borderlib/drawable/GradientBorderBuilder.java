package pers.zy.borderlib.drawable;

/**
 * date: 2020/8/31   time: 2:57 PM
 * author zy
 * Have a nice day :)
 **/
public class GradientBorderBuilder {

    private int[] borderColors = GradientBorderDrawable.DEFAULT_COLORS;
    private int[] bgColors = GradientBorderDrawable.DEFAULT_COLORS;
    private float borderWidth = GradientBorderDrawable.DEFAULT_BORDER_WIDTH;
    private float[] radii = new float[] {GradientBorderDrawable.DEFAULT_RADIUS,
            GradientBorderDrawable.DEFAULT_RADIUS,
            GradientBorderDrawable.DEFAULT_RADIUS,
            GradientBorderDrawable.DEFAULT_RADIUS,
            GradientBorderDrawable.DEFAULT_RADIUS,
            GradientBorderDrawable.DEFAULT_RADIUS,
            GradientBorderDrawable.DEFAULT_RADIUS,
            GradientBorderDrawable.DEFAULT_RADIUS};
    private int borderAngle = GradientBorderDrawable.ANGLE_LEFT_RIGHT;
    private int bgAngle = GradientBorderDrawable.ANGLE_LEFT_RIGHT;

    public GradientBorderBuilder() { }

    public GradientBorderBuilder setBorderColors(int[] borderColors) {
        this.borderColors = borderColors;
        return this;
    }

    public GradientBorderBuilder setBorderColor(int borderColor) {
        this.borderColors = new int[]{borderColor,
                borderColor};
        return this;
    }

    public GradientBorderBuilder setBgColors(int[] bgColors) {
        this.bgColors = bgColors;
        return this;
    }

    public GradientBorderBuilder setBgColor(int bgColor) {
        this.bgColors = new int[]{bgColor, bgColor};
        return this;
    }

    public GradientBorderBuilder setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public GradientBorderBuilder setRadius(float radius) {
        this.radii = new float[] {radius, radius, radius, radius, radius, radius, radius, radius};
        return this;
    }

    public GradientBorderBuilder setRadii(float[] radii) {
        this.radii = radii;
        return this;
    }

    public GradientBorderBuilder setRadiusWithRadiusType(float radius, GradientBorderDrawable.RadiusType radiusType) {
        this.radii = GradientBorderDrawable.createRadiiByType(radius, radiusType);
        return this;
    }

    public GradientBorderBuilder setBorderAngle(int borderAngle) {
        this.borderAngle = borderAngle;
        return this;
    }

    public GradientBorderBuilder setBgAngle(int bgAngle) {
        this.bgAngle = bgAngle;
        return this;
    }

    public GradientBorderDrawable build() {
        return new GradientBorderDrawable(
                borderColors,
                bgColors,
                borderWidth,
                radii,
                borderAngle,
                bgAngle
        );
    }
}
