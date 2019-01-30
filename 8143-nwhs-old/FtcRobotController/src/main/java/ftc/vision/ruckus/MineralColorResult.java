package ftc.vision.ruckus;

import org.opencv.core.Scalar;

import ftc.vision.ImUtil;

public class MineralColorResult {

    public enum MineralColor {
//        WHITE(ImUtil.WHITE),
        YELLOW(ImUtil.YELLOW),
//        UNKOWN(ImUtil.BLACK);
//        RED (ImUtil.RED),
        GREEN (ImUtil.GREEN),
        BLUE (ImUtil.BLUE),
        UNKNOWN (ImUtil.BLACK);

        public final Scalar color;

        MineralColor(Scalar scalar) {
            this.color = scalar;
        }
    }

    private final MineralColor leftColor, middleColor, rightColor;


    public MineralColorResult() {
        this.leftColor = MineralColor.UNKNOWN;
        this.middleColor = MineralColor.UNKNOWN;
        this.rightColor = MineralColor.UNKNOWN;
    }

    public MineralColorResult(MineralColor leftColor, MineralColor middleColor, MineralColor rightColor) {
        this.leftColor = leftColor;
        this.middleColor = middleColor;
        this.rightColor = rightColor;
    }

    public String toString() {
        return leftColor + ", " + middleColor + ", " + rightColor;
    }

    public MineralColor getLeftColor() {
        return leftColor;
    }

    public MineralColor getMiddleColor() {
        return middleColor;
    }

    public MineralColor getRightColor() {
        return rightColor;
    }
}
