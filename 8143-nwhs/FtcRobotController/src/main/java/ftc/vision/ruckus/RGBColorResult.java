package ftc.vision.ruckus;

import org.opencv.core.Scalar;

import ftc.vision.ImUtil;

public class RGBColorResult {

    public enum RGBColor {
//        WHITE(ImUtil.WHITE),
//        YELLOW(ImUtil.YELLOW),
//        UNKOWN(ImUtil.BLACK);
        RED (ImUtil.RED),
        GREEN (ImUtil.GREEN),
        BLUE (ImUtil.BLUE),
        UNKNOWN (ImUtil.BLACK);

        public final Scalar color;

        RGBColor(Scalar scalar) {
            this.color = scalar;
        }
    }

    private final RGBColor leftColor, middleColor, rightColor;


    public RGBColorResult() {
        this.leftColor = RGBColor.UNKNOWN;
        this.middleColor = RGBColor.UNKNOWN;
        this.rightColor = RGBColor.UNKNOWN;
    }

    public RGBColorResult(RGBColor leftColor, RGBColor middleColor, RGBColor rightColor) {
        this.leftColor = leftColor;
        this.middleColor = middleColor;
        this.rightColor = rightColor;
    }

    public String toString() {
        return leftColor + ", " + middleColor + ", " + rightColor;
    }

    public RGBColor getLeftColor() {
        return leftColor;
    }

    public RGBColor getMiddleColor() {
        return middleColor;
    }

    public RGBColor getRightColor() {
        return rightColor;
    }
}
