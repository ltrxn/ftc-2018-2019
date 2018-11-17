package ftc.vision.ruckus;

import org.opencv.core.Scalar;

import ftc.vision.ImUtil;

public class BallColorResult {
    public enum BallColor {
        RED (ImUtil.RED),
        GREEN (ImUtil.GREEN),
        BLUE (ImUtil.BLUE),
        UNKNOWN (ImUtil.BLACK);

        public final Scalar color;

        BallColor(Scalar scalar) {
            this.color = scalar;
        }
    }

    private final BallColor leftColor, rightColor;

    public BallColorResult() {
        this.leftColor = BallColor.UNKNOWN;
        this.rightColor = BallColor.UNKNOWN;
    }

    public BallColorResult(BallColor leftColor, BallColor rightColor) {
        this.leftColor = leftColor;
        this.rightColor = rightColor;
    }

    public String toString(){
        return leftColor + ", " + rightColor;
    }

    public BallColor getLeftColor() {
        return leftColor;
    }

    public BallColor getRightColor() {
        return rightColor;
    }
}
