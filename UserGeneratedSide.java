import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import static java.awt.GridBagConstraints.PAGE_END;

public class UserGeneratedSide extends JPanel{

    JButton newPage = new JButton("New Page");
    JButton deletePage = new JButton("Delete Page");
    JButton pageForward = new JButton("Page Forward");
    JButton pageBackward = new JButton("Page Back");
    JRadioButton ffinkTool = new JRadioButton("Free-form ink");
    JRadioButton rectangleTool = new JRadioButton("Rectangle");
    JRadioButton ovalTool = new JRadioButton("Oval");
    JRadioButton textTool = new JRadioButton("Text");
    ButtonGroup drawTools = new ButtonGroup();
    JPanel buttonBar = new JPanel();
    drawArea newDraw = new drawArea();
    LinkedList<drawArea> pages = new LinkedList<>();
    JScrollPane sp = new JScrollPane();
    int pNum = 0;
    int pageSize = 1;

    ComponentListener myComponentListener = new ComponentListener() {
        @Override
        public void componentResized(ComponentEvent e) {
            Dimension initialS = pages.get(pNum).getPreferredSize();
            double initialW = initialS.getWidth();
            double initialH = initialS.getHeight();
            Dimension newD = new Dimension((int) initialW,(int) initialH);
            if (initialW < pages.get(pNum).getWidth()) {
                newD.setSize(pages.get(pNum).getWidth(), newD.getHeight());
            }
            if (initialH < pages.get(pNum).getHeight()) {
                newD.setSize(newD.getWidth(), pages.get(pNum).getHeight());
            }
            for (drawArea d : pages) {
                d.setPreferredSize(newD);
                e.getComponent().revalidate();
            }

        }

        @Override
        public void componentMoved(ComponentEvent e) {

        }

        @Override
        public void componentShown(ComponentEvent e) {

        }

        @Override
        public void componentHidden(ComponentEvent e) {

        }
    };

    public UserGeneratedSide() {
        super();
        pages.add(newDraw);
        setLayout(new BorderLayout());
        sp.setViewportView(pages.get(pNum));
        sp.setPreferredSize(new Dimension(300, 400));
        sp.addComponentListener(myComponentListener);
        add(sp, BorderLayout.CENTER);

        repaint();
        buttonBar.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
//        add(placeholder, c);
        //initial location
        c.ipady = 0;
        c.ipadx = 0;
        c.weighty = 0.5;
        c.anchor =  GridBagConstraints.PAGE_END;
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0,0,25,0);
        c.gridwidth = 1;
        buttonBar.add(newPage, c);


        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        buttonBar.add(deletePage, c);

        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 1;
        buttonBar.add(pageForward, c);

        c.gridx = 3;
        c.gridy = 3;
        c.gridwidth = 1;
        buttonBar.add(pageBackward, c);

        c.insets = new Insets(0,0,0,0);

        //drawTools radio button
        ffinkTool.setSelected(pages.get(pNum).gFfink());
        rectangleTool.setSelected(pages.get(pNum).gRect());
        ovalTool.setSelected(pages.get(pNum).gOval());
        textTool.setSelected(pages.get(pNum).gWords());
        c.gridx = 0;
        c.gridy = 3;
        buttonBar.add(ffinkTool, c);
        c.gridx = 1;
        c.gridy = 3;
        buttonBar.add(rectangleTool, c);
        c.gridx = 2;
        c.gridy = 3;
        buttonBar.add(ovalTool, c);
        c.gridx = 3;
        c.gridy = 3;
        buttonBar.add(textTool, c);

        drawTools.add(ffinkTool);
        drawTools.add(rectangleTool);
        drawTools.add(ovalTool);
        drawTools.add(textTool);


        add(buttonBar, BorderLayout.SOUTH);


        if (newDraw.getGesture() == true) {
            if (newDraw.getWGest() == 1) {
                nextPage();
            } else if (newDraw.getWGest() == 2) {
                backPage();
            }
        }
    }

    //page functions


    //function that adds pages
    public void addPage() {
        pageSize++;
        pNum++;
        drawArea newPage = new drawArea();
        pages.add(pNum, newPage);
        sp.setViewportView(pages.get(pNum));
        ffinkTool.setSelected(true);
        revalidate();
        repaint();
    }

    //function that removes pages
    public void removePage() {
        pages.remove(pNum);
        pageSize--;
        pNum--;
        if (pageSize == 0) {
            pageSize++;
            pNum++;
            drawArea newPage = new drawArea();
            pages.add(newPage);
            sp.setViewportView(pages.get(pNum));
            ffinkTool.setSelected(true);
        } else if (pNum < 0){
            pNum++;
            sp.setViewportView(pages.get(pNum));
            ffinkTool.setSelected(true);
        } else {
            sp.setViewportView(pages.get(pNum));
            ffinkTool.setSelected(true);
        }
        revalidate();
        repaint();
    }

    //function that goes to the next page
    public void nextPage(){
        if (pNum < pageSize - 1) {
            pNum++;
            sp.setViewportView(pages.get(pNum));
            ffinkTool.setSelected(true);
        }
        revalidate();
        repaint();
    }

    //function that goes to the previous page
    public void backPage(){
        if (pNum > 0) {
            pNum--;
            sp.setViewportView(pages.get(pNum));;
            ffinkTool.setSelected(true);
        }
        revalidate();
        repaint();
    }

    //getters for the buttons
    public JButton getNewPage() {
        return newPage;
    }
    public JButton getDeletePage() {
        return deletePage;
    }
    public JButton getPageForward() {
        return pageForward;
    }
    public JButton getPageBackward() {
        return pageBackward;
    }
    public JRadioButton getffinkTool() {
        return ffinkTool;
    }
    public JRadioButton getOvalTool() {
        return ovalTool;
    }
    public JRadioButton getRectangleTool() {
        return rectangleTool;
    }
    public JRadioButton getTextTool() {
        return textTool;
    }
}
