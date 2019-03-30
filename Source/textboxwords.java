import java.awt.*;

public class textboxwords extends Shape {
    public textboxwords(String z, int x, int y, boolean select) {
        super(z, x, y, select);
    }
    public textboxwords() {
        super();
    }
    @Override
    public void drawShape(Graphics g) {
        g.drawString(getZ(), getX(), getY());
    }

    @Override
    public void recolor(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.green);
        g2.drawString(getZ(), getX(), getY());
        g2.setColor(Color.black);
    }
}
