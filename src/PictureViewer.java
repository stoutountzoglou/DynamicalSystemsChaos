import javax.swing.*;

/**
 *
 * @author Stelios Toutountzoglou 987
 */

public class PictureViewer extends JFrame{

    private JPanel BasePanel;
    private JLabel BaseLabel;

    public PictureViewer(DynamicalSystems ItIsChaos, String PicViewTitle, ImageIcon picIcon) {
        super(PicViewTitle);
        setLocation( ItIsChaos.getFrameLocation() + 40 ,30);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        BasePanel = new JPanel();
        BaseLabel = new JLabel(picIcon);
        BasePanel.add(BaseLabel);

        this.add(BasePanel);
        this.pack(); //to fernei sto megethos tis eikonas
    }
}