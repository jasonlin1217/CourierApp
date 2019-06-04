import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Point;
import static com.sun.deploy.net.UpdateTracker.clear;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;



public class drawArea extends JComponent {


    // Graphics2d object -- drawn on
    private Graphics2D g2;
    public ArrayList<Shape> shapeList = new ArrayList<>();
    public ArrayList<Shape> gestureList = new ArrayList<>();
    public ArrayList<Integer> removeList = new ArrayList<>();
    public ArrayList<Integer> selectList = new ArrayList<>();
    private int currentX, currentY, oldX, oldY;
    private boolean mouseDragged;
    private boolean ffink = true;
    private boolean rect = false;
    private boolean oval = false;
    private boolean words = false;
    private boolean mouseRelease = false;
    private boolean nTB = false;
    private int cooldown = 0;
    private String text = "";
    private String last = "";
    int height = 1;
    private boolean lClick = false;
    private boolean rClick = false;
    private int x1, y1, x2, y2;
    private int xDist, yDist;
    private double angle;
    private double pi = Math.PI;
    public String directions = "";
    private int gFunction = 0;
    private boolean doGesture = false;
    private int wX1 = 11111111, eX1 = 0, nY1 = 111111110, sY1 = 0;
    public gestureChecker checker = new gestureChecker();



    public drawArea() {
        super();
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseAdapter);
        addKeyListener(myKeyAdapter);
        setFocusable(true);
        setPreferredSize(new Dimension(300, 400));

    }


    KeyAdapter myKeyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            char x = e.getKeyChar();
            text = text + Character.toString(x);
            last = Character.toString(x);
            if (nTB == true && words == true) {
                drawString();
            }
        }
    };

    MouseAdapter myMouseAdapter = new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                rClick = false;
                lClick = true;
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                doGesture = true;
                rClick = true;
                lClick = false;
            }
            oldX = e.getX();
            oldY = e.getY();
            if (oldX < wX1 || oldX > eX1 || oldY < nY1 || oldY > sY1) {
                for (int i = selectList.size() - 1; i > 0; i--) {
                    if (selectList.get(i) < (shapeList.size() - 1)) {
                        shapeList.get(selectList.get(i).intValue()).setSelect(false);
                    }
                }
                wX1 = 11111111;
                eX1 = 0;
                nY1 = 111111110;
                sY1 = 0;
                selectList.clear();
                System.out.println(selectList);
            }

            mouseDragged = false;
            mouseRelease = false;
            requestFocusInWindow();
            text = "";
            height = 1;
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            currentX = e.getX();
            currentY = e.getY();
            if (rClick == true) {
                gestureList.add(new gLine(currentX,currentY, oldX, oldY, false));
                oldX = currentX;
                oldY = currentY;
                doGesture = true;
            }
            mouseDragged = true;
            mouseRelease = false;

            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (rClick == true) {
                if (!gestureList.isEmpty()) {
                    directions = directionCalc(gestureList);
                }
                if(gFunction == 3) {
                    deleteDraw(gestureList, shapeList);
                }
                if(gFunction == 4) {
                    selectDraw(gestureList, shapeList);
                }
                gestureList.clear();
                directions = "";
                doGesture = true;
            }
            currentX = e.getX();
            currentY = e.getY();
            mouseDragged = false;
            mouseRelease = true;
            repaint();
        }

    };


    public void drawfLine() {
        if (lClick == true) {
            shapeList.add(new ffinkline(oldX, oldY, currentX, currentY, false));
            // refresh draw area to repaint
            repaint();
            oldX = currentX;
            oldY = currentY;
            // store current coords x,y as olds x,y
        }
    }

    public void drawRect() {
        int x, y;
        int w, h;
        w = Math.abs(oldX - currentX);
        if (oldX > currentX) {
            x = currentX;
        } else {
            x = oldX;
        }
        h = Math.abs(oldY - currentY);
        if (oldY > currentY) {
            y = currentY;
        } else {
            y = oldY;
        }

        if (!shapeList.isEmpty() && mouseDragged == true) {
            shapeList.remove(shapeList.size() - 1);
        }
        shapeList.add(new rectangles(x, y, w, h, false));

    }

    public void drawOval() {
        int x, y;
        int w, h;
        w = Math.abs(oldX - currentX);
        if (oldX > currentX) {
            x = currentX;
        } else {
            x = oldX;
        }
        h = Math.abs(oldY - currentY);
        if (oldY > currentY) {
            y = currentY;
        } else {
            y = oldY;
        }
        if (!shapeList.isEmpty() && mouseDragged == true) {
            shapeList.remove(shapeList.size() - 1);
        }
        shapeList.add(new ovals(x, y, w, h, false));
    }

    public void drawString() {
        if (nTB == false) {
            text = "";
            height = 1;
        }
        int x, y;
        int w, h;
        w = Math.abs(oldX - currentX);
        if (oldX > currentX) {
            x = currentX;
        } else {
            x = oldX;
        }
        h = Math.abs(oldY - currentY);
        if (oldY > currentY) {
            y = currentY;
        } else {
            y = oldY;
        }
        int length = g2.getFontMetrics().stringWidth(text);
        if (length < w - 15) {
            shapeList.add(new textboxwords(text, x + 10, y + (15 * height), false));
        } else {
            height++;
            text = last;
            if ((height * 15) + 1 > h) {
                shapeList.add(new textBoxes(x, y + (height * 15) - 10, w, 15, false));
            }

        }


    }

    public void drawText() {
        int x, y;
        int w, h;
        nTB = false;
        w = Math.abs(oldX - currentX);
        if (oldX > currentX) {
            x = currentX;
        } else {
            x = oldX;
        }
        h = Math.abs(oldY - currentY);
        if (oldY > currentY) {
            y = currentY;
        } else {
            y = oldY;
        }
        if (!shapeList.isEmpty() && mouseDragged == true) {
            shapeList.remove(shapeList.size() - 1);
        }
        shapeList.add(new textBoxes(x, y, w, h, false));
        nTB = true;


    }

    public String directionCalc(ArrayList<Shape> gestures) {
        for (int i = 0; i < (gestures.size() - 6); i++ ) {
            x1 = gestures.get(i).getX();
            x2 = gestures.get(i + 5).getX();
            y1 = gestures.get(i).getY();
            y2 = gestures.get(i + 5).getY();
            xDist = x2 - x1;
            yDist = y1 - y2;
            angle = 12345;



            //calculate angle of line
            if (xDist == 0 && yDist == 0) {

            } else if (xDist < 0 && yDist < 0) {
                angle = (Math.atan((double)(-1*yDist)/ (-1* xDist)) + pi);
            } else if (xDist < 0 && yDist > 0) {
                angle = (Math.atan((double)yDist / (xDist)) + pi);
            } else if (xDist > 0 && yDist > 0) {
                angle = (Math.atan((double)yDist/xDist));
            } else if (xDist > 0 && yDist < 0) {
                angle = ((2 * pi) + (Math.atan((double)yDist/xDist)));
            } else if (xDist == 0) {
                if (yDist < 0) {
                    angle = ((3*pi)/2);
                } else if (yDist > 0) {
                    angle = (pi/2);
                }
            } else if (yDist == 0 ) {
                if(xDist < 0) {
                    angle = pi;
                } else if (xDist > 0) {
                    angle = 0;
                }
            }

            //determine letter for angles.
            if ((angle >= 0 && angle < (pi/8)) || (angle < (2*pi) && angle > (15*pi/8))) {

                directions = directions.concat("E");
            } else if ((angle >= (pi/8) && angle < (3*pi/8))) {

                directions = directions.concat("A");
            } else if ((angle >= (3*pi/8) && angle < (5*pi/8))) {

                directions = directions.concat("N");
            }  else if ((angle >= (5*pi/8) && angle < (7*pi/8))) {

                directions = directions.concat("B");
            }  else if ((angle >= (7*pi/8) && angle < (9*pi/8))) {

                directions =  directions.concat("W");
            }  else if ((angle >= (9*pi/8) && angle < (11*pi/8))) {

                directions =  directions.concat("C");
            }  else if ((angle >= (11*pi/8) && angle < (13*pi/8))) {

                directions = directions.concat("S");
            }  else if ((angle >= (13*pi/8) && angle < (15*pi/8))) {

                directions =  directions.concat("D");
            }

        }

        gFunction = checker.gChecker(directions, gFunction);
        return directions;
    }

    public void deleteDraw(ArrayList<Shape> gestures, ArrayList<Shape> drawings) {
        int nX = 0, wX = 11111111, sX = 100000000, eX = 0;
        int nY = 111111110, wY = 0, sY = 0, eY = 100000000;
        for (Shape q : gestures) {
            if (q.getX() < wX) {
                wX = q.getX();
            }
            if (q.getY() > sY) {
                sY = q.getY();
            }
            if (q.getY() < nY) {
                nY = q.getY();
                nX = q.getX();
            }
            if (q.getX() > eX) {
                eX = q.getX();
                eY = q.getY();
            }
        }

        for (Shape t : drawings) {
            if ((t.getX() >= wX) && (t.getX() <= eX) && (t.getY() <= sY) && (t.getY() >= nY)) {
                removeList.add(drawings.indexOf(t));
            }
            if ((t.getX() + t.getWidth()) >= wX && (t.getX() + t.getWidth()) <= eX
                    && (t.getY() + t.getHeight()) <= sY && (t.getY() + t.getHeight()) >= nY) {
                removeList.add(drawings.indexOf(t));
            }
        }
        for (int i = removeList.size() - 1; i > 0; i--) {
            if (removeList.get(i) < (drawings.size() - 1)) {
                drawings.remove(removeList.get(i).intValue());
            }
        }
        removeList.clear();

    }


    public void selectDraw(ArrayList<Shape> gestures, ArrayList<Shape> drawings) {

        for (Shape q : gestures) {
            if (q.getX() < wX1) {
                wX1 = q.getX();
            }
            if (q.getY() > sY1) {
                sY1 = q.getY();
            }
            if (q.getY() < nY1) {
                nY1 = q.getY();
            }
            if (q.getX() > eX1) {
                eX1 = q.getX();
            }
        }
        for (Shape z : drawings) {
            if ((z.getX() >= wX1) && (z.getX() <= eX1) && (z.getY() <= sY1) && (z.getY() >= nY1)) {
                selectList.add(drawings.indexOf(z));
            }

            if ((z.getX() + z.getWidth()) >= wX1 && (z.getX() + z.getWidth()) <= eX1
                    && (z.getY() + z.getHeight()) <= sY1 && (z.getY() + z.getHeight()) >= nY1) {
                selectList.add(drawings.indexOf(z));
            }
        }

        for (int i = selectList.size() - 1; i >= 0; i--) {
            if (selectList.get(i) < (drawings.size() - 1)) {
                drawings.get(selectList.get(i).intValue()).setSelect(true);
            }
        }
    }



    public void drawBackground(Graphics g) {
        g.setColor(Color.BLUE);
        for (int i = 0; i < (getSize().height / 10); i++) {
            g.drawLine(0, i * 25, getSize().width, i * 25);
            g.drawLine(i * 25, 0, i * 25, getSize().height);
            repaint();
        }
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        drawBackground(g);
        g2.setColor(Color.black);
        if (rClick == true && mouseRelease == true){
//
            if (gFunction == 1 || gFunction == 2 || gFunction == 3 || gFunction == 4)  {
                gFunction = 0;
                doGesture = false;
            }
            mouseRelease = false;
        }
        if (mouseDragged == true && ffink == true) {
            //draw line if g2 context not null
            drawfLine();
        }
        if (rect == true) {
            if (mouseDragged == true) {
                drawRect();
            } else if (mouseRelease == true) {
                drawRect();
                mouseRelease = false;
            }
        }

        if (oval == true && (mouseDragged == true || mouseRelease == true)) {
            drawOval();
            mouseRelease = false;
        }
        if (words == true && (mouseDragged == true || mouseRelease == true)) {
            drawText();
            mouseRelease = false;
        }
        if (words == true && (mouseRelease == true)) {
            drawString();
        }

        for (Shape s : shapeList) {
            if (s.getSelect() == true) {
                s.recolor(g);
            } else {
                s.drawShape(g);
            }

        }
//
        for (Shape a : gestureList) {
            a.drawShape(g);
        }



    }



    public void ffinkSelect() {
        ffink = true;
        rect = false;
        oval = false;
        words = false;
    }

    public void rectSelect() {
        ffink = false;
        rect = true;
        oval = false;
        words = false;
    }

    public void ovalSelect() {
        ffink = false;
        rect = false;
        oval = true;
        words = false;
    }

    public void wordsSelect() {
        ffink = false;
        rect = false;
        oval = false;
        words = true;
    }

    public boolean gFfink() { return ffink; }
    public boolean gRect() { return rect; }
    public boolean gOval() { return oval; }
    public boolean gWords() { return words; }
    public boolean getGesture() { return doGesture; }
    public int getWGest() { return gFunction; }
}
