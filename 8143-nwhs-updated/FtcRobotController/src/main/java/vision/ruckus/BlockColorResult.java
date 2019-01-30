package vision.ruckus;

import org.opencv.core.Scalar;

import vision.ImUtil;

public class BlockColorResult {
    public enum BlockColor {
        YELLOW(ImUtil.YELLOW),
        GREEN(ImUtil.GREEN),
        BLUE(ImUtil.BLUE),
        UNKNOWN(ImUtil.BLACK);

        public final Scalar color;

        BlockColor(Scalar scalar) {
            this.color = scalar;
        }
    }
    private final BlockColor leftColor, rightColor;

    public BlockColorResult() {
        this.leftColor = BlockColor.UNKNOWN;
        this.rightColor = BlockColor.UNKNOWN;
    }

    public BlockColorResult(BlockColor leftColor, BlockColor rightColor) {
        this.leftColor = leftColor;
        this.rightColor = rightColor;
    }

    public String toString() {
        return leftColor + ", " + rightColor;
    }

    public BlockColor getLeftColor() {
        return leftColor;
    }

    public BlockColor getRightColor() {
        return rightColor;
    }

}
