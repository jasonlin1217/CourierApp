import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class OverviewModeView extends JComponent {
    private Graphics2D g2;
    private LinkedList<drawArea> pages = new LinkedList();
    private LinkedList<BufferedImage> pics = new LinkedList<>();
    private LinkedList<BufferedImage> miniPics = new LinkedList<>();
    private int nPages, h, w, rH, rW, area;
    Rectangle r;

    public OverviewModeView() {
        super();
        setFocusable(true);
        repaint();
        setPreferredSize(new Dimension(300, 400));
    }



    public void setValues(LinkedList<drawArea> x, Rectangle r) {
        pages = x;
        nPages = pages.size();
        rH = (int) (r.getHeight() - 10);
        rW = (int) (r.getWidth() - 10);
        area = (rH * rW) / nPages;

        h = rH;
        w = rW/nPages;
        for (drawArea d : pages) {
            pics.add(makeBuffImage(d));
        }
        for (BufferedImage i : pics) {
            miniPics.add(iresize(i, w, h));
        }
    }
    public BufferedImage makeBuffImage (JComponent source) {
        // Create our BufferedImage and get a Graphics object for it
        GraphicsConfiguration gfxConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage pic = gfxConfig.createCompatibleImage(source.getWidth(), source.getHeight());
        Graphics2D offscreenGraphics = (Graphics2D) pic.getGraphics();
        System.out.println( h +  "  " + w);
        return pic;
    }
    public BufferedImage iresize(BufferedImage source, int width, int height) {
        Image temp = source.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        return dimg;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        int x = 10;
//        for (int i = 0; i < miniPics.size(); i++) {
//            while (x < rH) {
//
//                x = (x * (i + 1));
//            }
//
//        }
        g.drawImage(miniPics.get(0), 0, 0, null);
        repaint();
    }
}
