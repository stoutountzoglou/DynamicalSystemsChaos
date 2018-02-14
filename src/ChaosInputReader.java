import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Stelios Toutountzoglou 987
 */

public class ChaosInputReader extends JDialog implements ActionListener {

    private JTextField xField, yField, AreaSizeField, RealField, ImaginaryField, ImageSizeField, IterationsField;
    private String prevButton;
    private int ImageSizeVal, IterationsVal;
    private double xVal, yVal, AreaSizeVal, RealVal, ImaginaryVal;
    private int ReaderLocation;

    // DHMIOURGIA TOU PARATHIROU ME TA TEXT FIELDS
    public ChaosInputReader (String prevButton, Frame parentFrame, DynamicalSystems DSparentFrame) {
            super( parentFrame ,"Specify complex area position & size" , true);
            setResizable(false);
            ReaderLocation = DSparentFrame.getFrameLocation()+40;
            setLocation( ReaderLocation ,30);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());

            this.prevButton = prevButton;

            JLabel xLabel, yLabel, AreaSizeLabel, RealLabel, ImaginaryLabel, ImageSizeLabel, IterationsLabel;
            xLabel = new JLabel(" x-Coord of center");
            yLabel = new JLabel(" y-Coord of center");
            AreaSizeLabel = new JLabel(" Area size");
            RealLabel = new JLabel(" Real part of const");
            ImaginaryLabel = new JLabel(" Imaginary part of const");
            ImageSizeLabel = new JLabel(" Image size");
            IterationsLabel = new JLabel (" Number of Iterations");

            AreaSizeField = new JTextField("1",10);
            ImageSizeField = new JTextField("768",10);
            xField = new JTextField(10);
            yField = new JTextField(10);
            IterationsField = new JTextField(10);

            RealField = new JTextField("0.3",10);
            ImaginaryField = new JTextField("0.5",10);

            if (prevButton.equals("Create Mandelbrot set...")) {
                setSize(280, 300);

                xField.setText("0.5");
                yField.setText("0.5");
                IterationsField.setText("100");
            }
            else {
                setSize(280,350);

                xField.setText("0.0");
                yField.setText("0.0");
                IterationsField.setText("500");
            }


            JPanel InputPanel = new JPanel();

            if (prevButton.equals("Create Mandelbrot set...")) {

                InputPanel.setLayout(new GridLayout(5, 2));
                InputPanel.add(xLabel);
                InputPanel.add(xField);
                InputPanel.add(yLabel);
                InputPanel.add(yField);
                InputPanel.add(AreaSizeLabel);
                InputPanel.add(AreaSizeField);
                InputPanel.add(ImageSizeLabel);
                InputPanel.add(ImageSizeField);
                InputPanel.add(IterationsLabel);
                InputPanel.add(IterationsField);
            }
            else {
                InputPanel.setLayout(new GridLayout(7,2));
                InputPanel.add(xLabel);
                InputPanel.add(xField);
                InputPanel.add(yLabel);
                InputPanel.add(yField);
                InputPanel.add(AreaSizeLabel);
                InputPanel.add(AreaSizeField);
                InputPanel.add(RealLabel);
                InputPanel.add(RealField);
                InputPanel.add(ImaginaryLabel);
                InputPanel.add(ImaginaryField);
                InputPanel.add(ImageSizeLabel);
                InputPanel.add(ImageSizeField);
                InputPanel.add(IterationsLabel);
                InputPanel.add(IterationsField);

            }


            JPanel ButtonsPanel = new JPanel();
            ButtonsPanel.setLayout(new FlowLayout());

            InputPanel.setBackground(Color.yellow);

            JButton AcceptButton = new JButton("Accept");
            AcceptButton.setPreferredSize(new Dimension(125,50));
            ImageIcon dukeIcon = new ImageIcon("duke.png");
            AcceptButton.setIcon(dukeIcon);
            AcceptButton.addActionListener(this);
            JButton CancelButton = new JButton("Cancel");
            CancelButton.setPreferredSize(new Dimension(125,50));
            CancelButton.addActionListener(this);
            ButtonsPanel.add(AcceptButton);
            ButtonsPanel.add(CancelButton);
            add(InputPanel, BorderLayout.CENTER);
            add(ButtonsPanel, BorderLayout.SOUTH);

            //setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
            String f = e.getActionCommand();
            if (f.equals("Accept")) {
                try {
                    xVal = Double.parseDouble(xField.getText());
                    yVal = Double.parseDouble(yField.getText());
                    AreaSizeVal = Double.parseDouble(AreaSizeField.getText());
                    // if (prevButton.equals("Create Julia set...")) {
                    RealVal = Double.parseDouble(RealField.getText());
                    ImaginaryVal = Double.parseDouble(ImaginaryField.getText());
                    // }
                    ImageSizeVal = Integer.parseInt(ImageSizeField.getText());
                    IterationsVal = Integer.parseInt(IterationsField.getText());

                    this.setVisible(false);

                    Thread thread = new ChaosThread(xVal, yVal, AreaSizeVal, RealVal, ImaginaryVal, ImageSizeVal, IterationsVal, prevButton, ReaderLocation);
                    thread.start();
                }
                catch(Exception wrongInput) {
                    JOptionPane.showMessageDialog(this, "Input type mismatch in one (or more) input fields", "Invalid input", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (f.equals("Cancel")) {
                    this.setVisible(false);

            }
    }
}