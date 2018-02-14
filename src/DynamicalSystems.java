import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.*;

/**
 *
 * @author Stelios Toutountzoglou 987
 */
public class DynamicalSystems extends JFrame implements ActionListener {

        public static void main(String[] args) {
            DynamicalSystems DSdemo = new DynamicalSystems();
            DSdemo.setVisible(true);
        }

        private static final int SIZE = 500;
        private static final double SIZEtoDOUBLE = 500.0;

        private File BufferedFile;
        private JFileChooser FileSelection;
        private JPanel redPanel=null;
        private JLabel redLabel=null;

        public DynamicalSystems() {
            super(" Dynamical Systems: The beauty of Chaos ");
            setSize(SIZE, SIZE);
            setResizable(false);
            setLocation(30,30);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(1,1));

            JMenu menuProgram = new JMenu("Program");
            menuProgram.setMnemonic('P');

            JMenuItem LoadImageChoice = new JMenuItem("Load Image...");
            LoadImageChoice.setMnemonic('L');
            LoadImageChoice.addActionListener(this);
            menuProgram.add(LoadImageChoice);

            JMenuItem SetBackgroundChoice = new JMenuItem("Set Background...");
            SetBackgroundChoice.setMnemonic('B');
            SetBackgroundChoice.addActionListener(this);
            menuProgram.add(SetBackgroundChoice);

            JMenuItem ExitChoice = new JMenuItem("Exit");
            ExitChoice.setMnemonic('E');
            ExitChoice.addActionListener(this);
            menuProgram.add(ExitChoice);

            JMenu menuFractals = new JMenu("Fractals");
            menuFractals.setMnemonic('F');

            JMenuItem CreateMandelbrotChoice = new JMenuItem("Create Mandelbrot set...");
            CreateMandelbrotChoice.setMnemonic('M');
            CreateMandelbrotChoice.addActionListener(this );
            menuFractals.add(CreateMandelbrotChoice);

            JMenuItem CreateJuliaChoice = new JMenuItem("Create Julia set...");
            CreateJuliaChoice.setMnemonic('J');
            CreateJuliaChoice.addActionListener(this );
            menuFractals.add(CreateJuliaChoice);

            JMenu menuHelp = new JMenu("Help");
            menuHelp.setMnemonic('H');

            JMenuItem AboutChoice = new JMenuItem("About...");
            AboutChoice.setMnemonic('A');
            AboutChoice.addActionListener(this );
            menuHelp.add(AboutChoice);

            JMenuBar bar = new JMenuBar();
            bar.add(menuProgram);
            bar.add(menuFractals);
            bar.add(menuHelp);
            setJMenuBar(bar);

            redPanel = new JPanel();
            add(redPanel);
            redPanel.setBackground(Color.red);

            FileSelection = new JFileChooser();
            FileSelection.setFileSelectionMode(0); //to 0 einai gia loader FILES ONLY;
            FileSelection.addChoosableFileFilter(new JPGFilter());
        }

        public int getFrameLocation() {
            return SIZE;

        }

        public void actionPerformed(ActionEvent e) {
            String buttonString = e.getActionCommand();

            if (buttonString.equals("Load Image...")) {                 // LOAD IMAGE
                int ChooserIsOpen = FileSelection.showOpenDialog(this);

                if(ChooserIsOpen == 0) {
                    BufferedFile = FileSelection.getSelectedFile();
                    String PicViewTitle = BufferedFile.getPath().toString();
                    try {
                        PictureViewer PicView = new PictureViewer(this, PicViewTitle, new ImageIcon(ImageIO.read(BufferedFile))); //pernaw kai to this gia na exw to location tou
                        PicView.setVisible(true);
                    }
                    catch (Exception error) {
                        JOptionPane.showMessageDialog(null, "Error while reading file", "I/O Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            else if (buttonString.equals("Set Background...")) {        //SET BACKGROUND

                JOptionPane.showMessageDialog(this, "under construction . . .");
                int ChooserIsOpen = FileSelection.showOpenDialog(this);

                if(ChooserIsOpen == 0) {
                    BufferedFile = FileSelection.getSelectedFile();
                    try {
                        JLabel tempLabel = new JLabel(new ImageIcon(ImageIO.read(BufferedFile)));
                        if(redLabel != null){ // se periptwsh pou hdh yparxei background
                            redPanel.remove(redLabel);
                            redLabel = null;
                        }
                        redLabel = tempLabel;
                        redPanel.add(redLabel);

                        this.add(redPanel);
                        this.pack();//Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
                    }
                    catch (Exception error) {
                        JOptionPane.showMessageDialog(null, "Error while reading file", "I/O Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            else if (buttonString.equals("Exit")){                      //EXIT
                int n = JOptionPane.showConfirmDialog( this, "Are you sure?", "Don't Exit ! ! !", JOptionPane.YES_NO_OPTION);
                if (n==0) {
                    System.exit(0);
                }
            }

            else if (buttonString.equals("Create Mandelbrot set...") || buttonString.equals("Create Julia set...")){

                ChaosInputReader Inputwin = new ChaosInputReader(buttonString, (Frame) this, this);
                Inputwin.setVisible(true);     // pernaw to this mia fora me case casting se Frame epeidh mou to zhtaei to JDialog kai mia fora me
                                                // aplo this giati thelw to location tou
            }

            else if (buttonString.equals("About...")){      // CREDITS

                JOptionPane.showMessageDialog(null, "Credits to Mr. Toutountzoglou Stylianos 987");
            }
        }
}