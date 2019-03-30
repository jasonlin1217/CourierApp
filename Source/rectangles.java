import java.awt.*;

public class rectangles extends Shape{
    //draws rectangles
   public rectangles(int x, int y, int width, int height, boolean select) {
       super(x, y, width, height, select);
   }
   public rectangles() {
       super();
   }
    @Override
    public void drawShape(Graphics g) {
       g.setColor(Color.black);
       g.drawRect(getX(), getY(), getWidth(), getHeight());
    }
    @Override
    public void recolor(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.green);
        g2.drawRect(getX(), getY(), getWidth(), getHeight());
        g2.setColor(Color.black);
    }
}
