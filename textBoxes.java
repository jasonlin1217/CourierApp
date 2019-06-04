import java.awt.*;

public class textBoxes extends Shape{
    public textBoxes(int x, int y, int width, int height, boolean select) {
        super(x, y, width, height, select);
    }
    public textBoxes() {
        super();
    }

    @Override
    public void drawShape(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.orange);
        g2.fillRect(getX(), getY(), getWidth(), getHeight());
        Stroke oldS = g2.getStroke();
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.black);
//        g2.drawRect(getX(), getY(), getWidth(), getHeight());
        g2.setStroke(oldS);
    }

    @Override
    public void recolor(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.green);
        g2.fillRect(getX(), getY(), getWidth(), getHeight());
        Stroke oldS = g2.getStroke();
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.black);
        g2.setStroke(oldS);
    }
}
