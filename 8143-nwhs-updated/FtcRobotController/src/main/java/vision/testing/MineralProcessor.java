package vision.testing;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

import vision.ImUtil;
import vision.ImageProcessor;
import vision.Result;

public class MineralProcessor implements ImageProcessor {
    private static final String TAG = "MineralProcessor";

    @Override
    public Result process(Mat rgbaFrame) {

        //create new mat in hsv
        Mat hsvFrame = new Mat();
        Imgproc.cvtColor(rgbaFrame, hsvFrame, Imgproc.COLOR_RGB2HSV);

        //list of the color values
        List<Scalar> hsvMin = new ArrayList<>();
        List<Scalar> hsvMax = new ArrayList<>();

//        //hsvMin.add(new Scalar(  H,   S,   V  ));
//        hsvMin.add(new Scalar(330/2,  100, 150)); //red min
//        hsvMax.add(new Scalar( 40/2, 255, 255)); //red max
//
//        hsvMin.add(new Scalar( 60/2,  100, 150)); //green min
//        hsvMax.add(new Scalar(180/2, 255, 255)); //green max

        hsvMin.add(new Scalar(30 / 2, 100, 150)); //yellow min
        hsvMax.add(new Scalar(70 / 2, 255, 255)); //yellow max

        hsvMin.add(new Scalar(60 / 2, 100, 150)); //green min
        hsvMax.add(new Scalar(180 / 2, 255, 255)); //green max

        hsvMin.add(new Scalar(195 / 2, 100, 150)); //blue min
        hsvMax.add(new Scalar(285 / 2, 255, 255)); //blue max

        //after masking out a color, put the mask image in this array
        List<Mat> rgbaChannels = new ArrayList<>();

        //temporary holding for masked image
        Mat maskedFrame;

        // index of the max mass
        //0 = yellow, 1 = blue, 2 = green, 3 = unknown
        int[] colorOfMaxMass = {3, 3, 3};


        Mat colSum = new Mat();
        double[] maxMass = {Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE};
        double mass;
        int[] data = new int[3]; //used to read the colSum

        for (int i = 0; i < 3; i++) {

            maskedFrame = new Mat();
            ImUtil.hsvInRange(hsvFrame, hsvMin.get(i), hsvMax.get(i), maskedFrame);
            rgbaChannels.add(maskedFrame);


            Core.reduce(maskedFrame, colSum, 0, Core.REDUCE_SUM, 4);
            int start = 0;
            int end = hsvFrame.width() / 3;
            int twoThirds = (hsvFrame.width() * 2) / 3;

            for (int s = 0; s < 3; s++) {//right and left side
                mass = 0;
                for (int x = start; x < end; x++) {
                    colSum.get(0, x, data);
                    mass += data[0];
                }
                mass /= hsvFrame.size().area(); //scale the mass by the image size
                if (mass > maxMass[s]) {
                    maxMass[s] = mass;
                    colorOfMaxMass[s] = i;
                }

                if (s == 0) {
                    start = end;
                    end = twoThirds;
                } else {
                    start = end;
                    end = hsvFrame.width();
                }
            }
        }

        //add empty alpha channel
        rgbaChannels.add(Mat.zeros(hsvFrame.size(), CvType.CV_8UC1));
        //merge the 3 binary images and 1 alpha channel into one image
        Core.merge(rgbaChannels, rgbaFrame);


        MineralColorResult.MineralColor[] mineralColors = MineralColorResult.MineralColor.values();
        MineralColorResult.MineralColor left = mineralColors[colorOfMaxMass[0]];
        MineralColorResult.MineralColor middle = mineralColors[colorOfMaxMass[1]];
        MineralColorResult.MineralColor right = mineralColors[colorOfMaxMass[2]];


        //draw the color result bars
        int barHeight = 352 / 30;
        int frameWidth = 352;

        Imgproc.rectangle(rgbaFrame, new Point(0, 0), new Point(110, barHeight), left.color, barHeight);
        Imgproc.rectangle(rgbaFrame, new Point(110, 0), new Point(220, barHeight), middle.color, barHeight);
        Imgproc.rectangle(rgbaFrame, new Point(220, 0), new Point(330, barHeight), right.color, barHeight);
//        Imgproc.rectangle(rgbaFrame, new Point(0, 0), new Point(frameWidth * (1/3), barHeight), left.color, barHeight);
//        Imgproc.rectangle(rgbaFrame, new Point(frameWidth * (1/3), 0), new Point(frameWidth * (2/3), barHeight), middle.color, barHeight);
//        Imgproc.rectangle(rgbaFrame, new Point(frameWidth * (2/3), 0), new Point(frameWidth, barHeight), right.color, barHeight);

        return new Result(rgbaFrame, new MineralColorResult(left, middle, right));
    }
}
