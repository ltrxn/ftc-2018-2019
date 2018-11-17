package ftc.vision;

import org.opencv.core.Mat;

public class Result<ResultType> {
    private final ResultType result;
    private final Mat frame;

    public Result(Mat frame, ResultType result) {
        this.frame = frame;
        this.result = result;
    }

    public boolean isResultNull() {
        return result == null;
    }

    public ResultType getResult(){
        return result;
    }

    public Mat getFrame() {
        return frame;
    }

    @Override
    public String toString() {
        if (isResultNull()) {
            return  "null";
        } else {
            return result.toString();
        }
    }
}
