
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MainScreen extends JFrame{

    TabPanel left = new TabPanel();
    JTextField statusBar = new JTextField();
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    UserGeneratedSide rightPanel = new UserGeneratedSide();

    MouseAdapter mMouse = new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

            if (rightPanel.pages.get(rightPanel.pNum).getGesture() == true) {
                if (rightPanel.pages.get(rightPanel.pNum).getWGest() == 1) {
                    rightPanel.nextPage();
                    statusBar.setText("right arrow recognized");
                } else if (rightPanel.pages.get(rightPanel.pNum).getWGest() == 2) {
                    rightPanel.backPage();
                    statusBar.setText("left arrow recognized");
                } else if (rightPanel.pages.get(rightPanel.pNum).getWGest() == 3) {
                    statusBar.setText("pigtail recognized");
                } else if (rightPanel.pages.get(rightPanel.pNum).getWGest() == 4) {
                    statusBar.setText("loop recognized");
                } else if (rightPanel.pages.get(rightPanel.pNum).getWGest() == 0) {
                    statusBar.setText("Gesture Unrecognized");
                }
            }
        }

    };
    /*
    Constructor for implementing the split screen and status bar.
     */
    public MainScreen() {

        splitPane.setLeftComponent(left);
        left.setMinimumSize(new Dimension(250, 300));
        splitPane.setRightComponent(rightPanel);


        rightPanel.setMinimumSize(new Dimension(400, 300));
        add(splitPane);

        //textfield
        statusBar.setColumns(10);
        statusBar.setEditable(false);
        statusBar.setLayout(new BorderLayout());

        add(statusBar, BorderLayout.SOUTH);

        /*action listeners to see if button is pressed and then changes the
        status bar to reflect the button being pressed.
        */
        rightPanel.getNewPage().addActionListener(x -> {
            rightPanel.addPage();
            rightPanel.pages.get(rightPanel.pNum).addMouseListener(mMouse);
            statusBar.setText("New Page Selected");
        });
        rightPanel.getDeletePage().addActionListener(x -> {
            rightPanel.removePage();
            statusBar.setText("Delete Page Selected");
        });

        rightPanel.getPageForward().addActionListener(x -> {
            rightPanel.nextPage();
            statusBar.setText("Page Forward Selected");
        });

        rightPanel.getPageBackward().addActionListener(x -> {
            rightPanel.backPage();
            statusBar.setText("Page Back Selected");
        });

        rightPanel.getffinkTool().addActionListener(x -> {
            rightPanel.pages.get(rightPanel.pNum).ffinkSelect();
            statusBar.setText("Free-Form Ink Selected");
        });
        rightPanel.getRectangleTool().addActionListener(x -> {
            rightPanel.pages.get(rightPanel.pNum).rectSelect();
            statusBar.setText("Rectangle Selected");
        });
        rightPanel.getOvalTool().addActionListener(x -> {
            rightPanel.pages.get(rightPanel.pNum).ovalSelect();
            statusBar.setText("Oval Selected");
        });
        rightPanel.getTextTool().addActionListener(x -> {
            rightPanel.pages.get(rightPanel.pNum).wordsSelect();
            statusBar.setText("Text Selected");
        });

        rightPanel.pages.get(rightPanel.pNum).addMouseListener(mMouse);



    }

    public static void main(String[] args) {

        MainScreen sp = new MainScreen();
        sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sp.setSize(800,600);
        sp.setMinimumSize(new Dimension(700, 500));
        sp.setVisible(true);

    }
}
