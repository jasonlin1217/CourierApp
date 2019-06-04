import java.awt.Graphics;


public abstract class Shape {
    private String z;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean select;

    public Shape() {
        this(0, 0, 1, 1, false);
    }

    public Shape(int x, int y, int width, int height, boolean select) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.select = select;
    }
    public Shape(String z, int x, int y, boolean select) {
        this.z = z;
        this.x = x;
        this.y = y;
        this.select = select;
    }

    public abstract void drawShape(Graphics g);

    public abstract void recolor(Graphics g);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getZ() { return z; }

    public void setZ(String z) { this.z = z; }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSelect(boolean select) {this.select = select; }

    public boolean getSelect() {return select; }


}

