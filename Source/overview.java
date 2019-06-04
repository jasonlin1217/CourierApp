
import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class overview extends JPanel {

    private Graphics2D g2;
    private int cX, cY;
    private boolean mRelease = false;
    LinkedList<drawArea> pages = new LinkedList<>();
    drawArea newDraw = new drawArea();
    int pNum = 0;
    int pageSize = 1;
    OverviewModeView overMode = new OverviewModeView();




    MouseAdapter myMouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            cX = e.getX();
            cY = e.getY();

        }
    };

    public overview() {
        super();
        setLayout(new BorderLayout());
        pages.add(newDraw);
        addMouseListener(myMouseAdapter);
        setFocusable(true);
        repaint();

    };


    public void OVMode(Rectangle r) {
        overMode.setValues(pages, r);
    }

}
