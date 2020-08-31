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
    private float corner = GradientBorderDrawable.DEFAULT_COLOR;
    private int borderAngle = GradientBorderDrawable.ANGLE_LEFT_RIGHT;
    private int bgAngle = GradientBorderDrawable.ANGLE_LEFT_RIGHT;

    public GradientBorderBuilder() { }

    public GradientBorderBuilder setBorderColors(int[] borderColors) {
        this.borderColors = borderColors;
        return this;
    }

    public GradientBorderBuilder setBorderColor(int borderColor) {
        this.borderColors = new int[]{borderColor, borderColor};
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

    public GradientBorderBuilder setCorner(float corner) {
        this.corner = corner;
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
                corner,
                borderAngle,
                bgAngle
        );
    }
}
