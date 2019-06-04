import java.awt.*;

public class ffinkline extends Shape {
    public ffinkline(int x, int y, int x2, int y2, boolean select) {
        super(x, y, x2, y2, select);
    }
    public ffinkline() {
        super();
    }
    @Override
    public void drawShape(Graphics g) {
        g.setColor(Color.black);
        g.drawLine(getX(), getY(), getWidth(), getHeight());
    }
    @Override
    public void recolor(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.green);
        g2.drawLine(getX(), getY(), getWidth(), getHeight());
        g2.setColor(Color.black);
    }
}
