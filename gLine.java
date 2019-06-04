import java.awt.*;

public class gLine extends Shape {

    public gLine(int x, int y, int x2, int y2, boolean select) {
        super(x, y, x2, y2, select);
    }
    public gLine() {
        super();
    }
    @Override
    public void drawShape(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        g2.drawLine(getX(), getY(), getWidth(), getHeight());
        g2.setColor(Color.black);
    }
    @Override
    public void recolor(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.green);
        g2.drawLine(getX(), getY(), getWidth(), getHeight());
        g2.setColor(Color.black);
    }

}
