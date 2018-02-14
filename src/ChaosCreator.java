import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.BorderLayout;
/**
 *
 * @author Stelios Toutountzoglou 987
 */

class ChaosCreator  extends JFrame
{
    private BufferedImage Image;

    private int ImageSizeVal, IterationsVal;
    private double xVal, yVal, AreaSizeVal, RealVal, ImaginaryVal;

    private Color palette[];


    public ChaosCreator(double xVal, double yVal, double AreaSizeVal, double RealVal, double ImaginaryVal,int ImageSizeVal, int IterationsVal, String FractalButton, int ReaderLocation)
    {
        super();

        palette = new Color[46];

        palette[0]= new Color(127, 255, 212);
        palette[1]= new Color(255, 228, 196);
        palette[2]= new Color(0, 0, 255);
        palette[3]= new Color(138, 43, 226);
        palette[4]= new Color(95, 158, 160);
        palette[5]= new Color(127, 255, 0);
        palette[6]= new Color(255, 127, 80);
        palette[7]= new Color(100, 149, 237);
        palette[8]= new Color(0, 255, 255);
        palette[9]= new Color(0, 100, 0);
        palette[10]= new Color(153, 50, 204);
        palette[11]= new Color(233, 150, 122);
        palette[12]= new Color(143, 188, 143);
        palette[13]= new Color(0, 206, 209);
        palette[14]= new Color(148, 0, 211);
        palette[15]= new Color(255, 20, 147);
        palette[16]= new Color(0, 191, 255);
        palette[17]= new Color(255, 215, 0);
        palette[18]= new Color(240, 230, 140);
        palette[19]= new Color(240, 128, 128);
        palette[20]= new Color(32, 178, 170);
        palette[20]= new Color(255, 0, 255);
        palette[21]= new Color(0, 0, 205);
        palette[22]= new Color(147, 112, 219);
        palette[23]= new Color(0, 250, 154);
        palette[24]= new Color(255, 228, 225);
        palette[25]= new Color(107, 142, 35);
        palette[26]= new Color(255, 165, 0);
        palette[27]= new Color(255, 69, 0);
        palette[28]= new Color(152, 251, 152);
        palette[29]= new Color(205, 133, 63);
        palette[30]= new Color(255, 192, 203);
        palette[31]= new Color(221, 160, 221);
        palette[32]= new Color(160, 32, 240);
        palette[33]= new Color(255, 0, 0);
        palette[34]= new Color(65, 105, 225);
        palette[35]= new Color(250, 128, 114);
        palette[36]= new Color(244, 164, 96);
        palette[37]= new Color(135, 206, 235);
        palette[38]= new Color(106, 90, 205);
        palette[39]= new Color(0, 255, 127);
        palette[40]= new Color(216, 191, 216);
        palette[41]= new Color(255, 99, 71);
        palette[42]= new Color(64, 224, 208);
        palette[43]= new Color(238, 130, 238);
        palette[44]= new Color(255, 255, 0);
        palette[45]= new Color(154, 205, 50);

        this.xVal = xVal;
        this.yVal = yVal;
        this.AreaSizeVal = AreaSizeVal;
        this.RealVal = RealVal;
        this.ImaginaryVal = ImaginaryVal;
        this.ImageSizeVal = ImageSizeVal;
        this.IterationsVal = IterationsVal;

        setTitle("" + ImageSizeVal + "X" + ImageSizeVal + ": " + RealVal + " " + ImaginaryVal + " " + " " + AreaSizeVal + " " + this.IterationsVal);
        if(FractalButton.equals("Create Julia set...")){
            setTitle("" + ImageSizeVal + "X" + ImageSizeVal + ": " + RealVal + " " + ImaginaryVal + " " + " " + AreaSizeVal + " " + this.IterationsVal + "  c=" + RealVal + "+" + ImaginaryVal + "i");
        }
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation( ReaderLocation ,30);

        JMenu menuFile = new JMenu("File");
        JMenuItem SaveChoice = new JMenuItem("Save");
        JMenuBar saveBar = new JMenuBar();

        menuFile.add(SaveChoice);
        saveBar.add(menuFile);
        setJMenuBar(saveBar);

        Image = new BufferedImage(ImageSizeVal, ImageSizeVal, 1); // TYPE_INT_RGB
        add(new JLabel(new ImageIcon(Image)));

        this.setVisible(true);
        this.pack();

        ChaosCreator LoadingChaos = new ChaosCreator();
        LoadingChaos.setVisible(true);

        fractals(FractalButton);

        LoadingChaos.setVisible(false);
    }

    public ChaosCreator() {     // einai to minimataki load me th fatsoula pou petaei
        super("Loading Chaos ...");

        setSize(250, 100);
        setLocation( 750 ,300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        JLabel LoadingLabel = new JLabel("  Chaos coming in a while ! ! !");
        ImageIcon anxiousIcon = new ImageIcon("anxious.jpg");
        LoadingLabel.setIcon(anxiousIcon);
        setBackground(Color.white);
        add(LoadingLabel,BorderLayout.CENTER);

    }

    public void fractals(String FractalButton) {

        int mandVal;
        if (FractalButton.equals("Create Mandelbrot set...")){

            for ( int i=0; i < ImageSizeVal; i++) {
                for(int j=0; j < ImageSizeVal; j++) {
                    double x0 = xVal - (AreaSizeVal/2.0) + (AreaSizeVal*i/ImageSizeVal);
                    double y0 = yVal-(AreaSizeVal/2.0) + (AreaSizeVal*j/ImageSizeVal);
                    Complex pixelComplex = new Complex(x0,y0);
                    mandVal = mand( pixelComplex, IterationsVal);

                    int correctColour = PaletteNumber(mandVal, IterationsVal);
                    Image.setRGB( i, ImageSizeVal - 1 - j, palette[correctColour].getRGB()); //ola einai int parametroi. h getRgb einai synarthsh ths Color kai epistrefei se int to xrwma
                }
            }
        }
        else {

            for( int i=0; i < ImageSizeVal; i++) {
                for( int j=0; j < ImageSizeVal; j++) {
                    double x0 = xVal - (AreaSizeVal/2.0) + (AreaSizeVal*i/ImageSizeVal);
                    double y0 = yVal - (AreaSizeVal/2.0) + (AreaSizeVal*j/ImageSizeVal);
                    Complex constComplex = new Complex(RealVal, ImaginaryVal);
                    Complex pixelComplex = new Complex(x0,y0);
                    mandVal = mand( pixelComplex, IterationsVal ,constComplex);

                    int correctColour = PaletteNumber(mandVal, IterationsVal);
                    Image.setRGB(i, ImageSizeVal - 1 - j, palette[correctColour].getRGB());   //ola einai int parametroi. h getRgb einai synarthsh ths Color kai epistrefei se int to xrwma
                }
            }
        }
        repaint();//refresh
    }

    public int PaletteNumber(int mandVal, int IterationsVal) {
        double toPaletteLength;
        int correctColour = 0;
        if(IterationsVal < 46) {
            correctColour = IterationsVal ;
        }
        else {
            toPaletteLength = (double)IterationsVal / 46.0;
            if( toPaletteLength > (double)mandVal){                           //an to palettelength einai megalytero apo to mandVal tote h
                correctColour = 0;                                            // diairesh mandVal/palettelength 8a epistrepsei arithmo
            }                                                                 // mikrotero ths monados kai otan tha afairei to 1 sthn parakatw
            else {                                                            // entolh tha bgainei ektos tou pinaka palette
                correctColour = (int)((double)mandVal / toPaletteLength) - 1;
            }
        }
        return correctColour;
    }


    public int mand(Complex z0, int max) {
        Complex z= z0;
        for (int t=0; t<max; t++) {
            if( z.abs() > 2.0 )
            return t;
            z= z.times(z).plus( z0 );
        }
        return max;
    }

    public int mand(Complex z0, int max, Complex c) {
        Complex z= z0;
        for (int t=0; t<max; t++) {
            if( z.abs() > 2.0 ) {
                return t;
            }
        z= z.times(z).plus( c );
        }
        return max;
    }
}