package vision;
import org.opencv.core.Mat;

public interface ImageProcessor<ResultType> {
    Result<ResultType> process (Mat rgbaFrame);
}
