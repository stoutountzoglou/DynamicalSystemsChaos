/**
 *
 * @author Stelios Toutountzoglou 987
 */

public class ChaosThread extends Thread  {
    private String str;
    private double xVal;
    private double yVal;
    private double AreaSizeVal;
    private double RealVal;
    private double ImaginaryVal;
    private int ImageSizeVal;
    private int IterationsVal;
    private int ReaderLocation;

    public ChaosThread(String str) {
        super(str);
    }

    public ChaosThread (double xVal, double yVal, double AreaSizeVal, double RealVal, double ImaginaryVal, int ImageSizeVal, int IterationsVal, String FractalButton, int ReaderLocation) {
        this.str = FractalButton;
        this.xVal = xVal;
        this.yVal = yVal;
        this.AreaSizeVal = AreaSizeVal;
        this.RealVal = RealVal;
        this.ImaginaryVal = ImaginaryVal;
        this.ImageSizeVal = ImageSizeVal;
        this.IterationsVal = IterationsVal;
        this.ReaderLocation = ReaderLocation;
    }

    public void run(){
        new ChaosCreator(xVal, yVal, AreaSizeVal, RealVal, ImaginaryVal, ImageSizeVal, IterationsVal, str, ReaderLocation);
    }
}