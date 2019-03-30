import java.awt.*;

public class ovals extends Shape{
    //draws ovals
    public ovals(int x, int y, int width, int height, boolean select) {
        super(x, y, width, height, select);
    }
    public ovals() {
        super();
    }
    @Override
    public void drawShape(Graphics g) {
        g.setColor(Color.black);
        g.drawOval(getX(), getY(), getWidth(), getHeight());
    }
    @Override
    public void recolor(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.green);
        g2.drawOval(getX(), getY(), getWidth(), getHeight());
        g2.setColor(Color.black);
    }
}
