package ftc.vision;

import android.view.SurfaceView;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class FrameGrabber implements CameraBridgeViewBase.CvCameraViewListener2 {

    //VARIABLES
    private final static String TAG = "FrameGrabber";
    private Mat frame;
    private ImageProcessor processor;
    private boolean mask = true;

    //CONSTRUCTOR
    public FrameGrabber (CameraBridgeViewBase cameraView, int frameWidth, int frameHeight) {
        //make camera view visible
        cameraView.setVisibility(SurfaceView.VISIBLE);

        //set dimensions of the frame
        cameraView.setMinimumHeight(frameHeight);
        cameraView.setMinimumWidth(frameWidth);
        cameraView.setMaxFrameSize(frameWidth, frameHeight);

        cameraView.setCvCameraViewListener(this);
    }

    public void setProcessor(ImageProcessor processor) {
        this.processor = processor;
    }

    public boolean isProcessorNull() {
        return processor == null;
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        frame = new Mat(height, width, CvType.CV_8UC4, new Scalar(0, 0, 0));
    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        frame = ImUtil.rotate(inputFrame.rgba(), 90);
        processFrame();
        return frame;
    }

    public Result processFrame() {
        if (isProcessorNull()) {
            return null;
        }
        Result result = processor.process(frame);

        if (mask) {
            frame = result.getFrame();
        }
        return result;
    }
}
