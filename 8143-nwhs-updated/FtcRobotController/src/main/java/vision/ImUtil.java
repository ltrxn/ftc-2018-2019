package vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class ImUtil {
    public static final Scalar WHITE        = new Scalar(255, 255, 255);
    public static final Scalar GRAY         = new Scalar(128, 128, 128);
    public static final Scalar BLACK        = new Scalar(  0,   0,   0);

    public static final Scalar RED          = HSVtoRGB(  0, 255, 255);
    public static final Scalar ORANGE       = HSVtoRGB( 30, 255, 255);
    public static final Scalar YELLOW       = HSVtoRGB( 60, 255, 255);
    public static final Scalar CHARTREUSE   = HSVtoRGB( 90, 255, 255);
    public static final Scalar GREEN        = HSVtoRGB(120, 255, 255);
    public static final Scalar SPRING_GREEN = HSVtoRGB(150, 255, 255);
    public static final Scalar INDIGO       = HSVtoRGB(180, 255, 255);
    public static final Scalar AZURE        = HSVtoRGB(210, 255, 255);
    public static final Scalar BLUE         = HSVtoRGB(240, 255, 255);
    public static final Scalar PURPLE       = HSVtoRGB(270, 255, 255);
    public static final Scalar MAGENTA      = HSVtoRGB(300, 255, 255);
    public static final Scalar PINK         = HSVtoRGB(330, 255, 255);

    public static final Scalar BROWN        = HSVtoRGB( 30, 255, 150);

    /**
     * rotates a Mat by any angle. If the angle is 90n, use transpose and/or flip.
     * Otherwise, use warpAffine
     * @param src Mat to be rotated
     * @param angle angle to rotate by
     * @return Rotated Mat
     */
    public static Mat rotate(Mat src, int angle) {
        angle = angle % 360;
        Mat dst = src.t();

        if (angle == 180 || angle == -180) {
            Core.flip(src.t(), dst, -1);

        } else if (angle == 90 || angle == -270) {
            Core.flip(src.t(), dst, 1);

        } else if (angle == 270 || angle == -90) {
            Core.flip(src.t(), dst, 0);

        } else if (angle == 0 || angle == 360 || angle == -360) {

        } else {
        }

        Imgproc.resize(dst, dst, src.size());
        return dst;
    }

    /**
     * Applys the Core.inRange function to a Mat after accounting for rollover
     * on the hsv hue channel.
     * @param srcHSV source Mat in HSV format
     * @param min Scalar that defines the min h, s, and v values
     * @param max Scalar that defines the max h, s, and v values
     * @param dst the output binary image
     */
    public static void hsvInRange(Mat srcHSV, Scalar min, Scalar max, Mat dst){
        //if the max hue is greater than the min hue
        if(max.val[0] > min.val[0]) {
            //use inRange once
            Core.inRange(srcHSV, min, max, dst);
        } else {
            //otherwise, compute 2 ranges and bitwise or them
            Scalar min1, min2, max1, max2;

            min1 = min;
            double[] vals = max.val.clone();
            vals[0] = 179;
            max1 = new Scalar(vals);

            vals = min.val.clone();
            vals[0] = 0;
            min2 = new Scalar(vals);
            max2 = max;

            Mat tmp1 = new Mat(), tmp2 = new Mat();
            Core.inRange(srcHSV, min1, max1, tmp1);
            Core.inRange(srcHSV, min2, max2, tmp2);
            Core.bitwise_or(tmp1, tmp2, dst);
        }
    }

    /**
     * Converts colorspace from HSV to RGB
     * @param hue HSV hue (0 to 360)
     * @param saturation HSV saturation (0 to 255)
     * @param value HSV value (0 to 255)
     * @return OpenCV RGB Scalar
     */
    public static Scalar HSVtoRGB(double hue, double saturation, double value) {
        double r,g ,b;
        double f, p, q, t;
        int hi;

        // Note that in OpenCV, the HSV values have these valid ranges:
        //the h range is 0 to 179
        //the s range is 0 to 255
        //the v range is 0 to 255

        //hue ∈ [0°, 360°], saturation ∈ [0, 1], and value ∈ [0, 1]
        hue = (hue * 360) / 360;
        saturation = (saturation * 1) / 255;
        value = (value * 1) / 255;

        hue /= 60;
        hi = (int) hue;
        f = hue - hi;
        p = value * (1 - saturation);
        q = value * (1 - saturation * f);
        t = value * (1 - saturation * (1 - f));

        switch (hi) {
            case 0:
                r = value;
                g = t;
                b = p;
                break;
            case 1:
                r = q;
                g = value;
                b = p;
                break;
            case 2:
                r = p;
                g = value;
                b = t;
                break;
            case 3:
                r = p;
                g = q;
                b = value;
                break;
            case 4:
                r = t;
                g = p;
                b = value;
                break;
            default:
                r = value;
                g = p;
                b = q;
                break;
        }

        r *= 255;
        g *= 255;
        b *= 255;

        return new Scalar(r, g, b);
    }
}
