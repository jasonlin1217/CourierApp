import javax.swing.*;
import javax.swing.border.Border;
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
    JButton overButton = new JButton("Overview");
    JRadioButton ffinkTool = new JRadioButton("Free-form ink");
    JRadioButton rectangleTool = new JRadioButton("Rectangle");
    JRadioButton ovalTool = new JRadioButton("Oval");
    JRadioButton textTool = new JRadioButton("Text");
    ButtonGroup drawTools = new ButtonGroup();
    JPanel buttonBar = new JPanel();
    JScrollPane sp = new JScrollPane();
    overview newOverview = new overview();

    ComponentListener myComponentListener = new ComponentListener() {
        @Override
        public void componentResized(ComponentEvent e) {
            Dimension initialS = newOverview.pages.get(newOverview.pNum).getPreferredSize();
            double initialW = initialS.getWidth();
            double initialH = initialS.getHeight();
            Dimension newD = new Dimension((int) initialW,(int) initialH);
            if (initialW < newOverview.pages.get(newOverview.pNum).getWidth()) {
                newD.setSize(newOverview.pages.get(newOverview.pNum).getWidth(), newD.getHeight());
            }
            if (initialH < newOverview.pages.get(newOverview.pNum).getHeight()) {
                newD.setSize(newD.getWidth(), newOverview.pages.get(newOverview.pNum).getHeight());
            }
            for (drawArea d : newOverview.pages) {
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
        setLayout(new BorderLayout());
        sp.setViewportView(newOverview.pages.get(newOverview.pNum));
        sp.setPreferredSize(new Dimension(300, 400));
        sp.addComponentListener(myComponentListener);
        add(sp, BorderLayout.CENTER);
        setFocusable(true);
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
        ffinkTool.setSelected(newOverview.pages.get(newOverview.pNum).gFfink());
        rectangleTool.setSelected(newOverview.pages.get(newOverview.pNum).gRect());
        ovalTool.setSelected(newOverview.pages.get(newOverview.pNum).gOval());
        textTool.setSelected(newOverview.pages.get(newOverview.pNum).gWords());
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

        c.gridy = 4;
        buttonBar.add(overButton, c);

        drawTools.add(ffinkTool);
        drawTools.add(rectangleTool);
        drawTools.add(ovalTool);
        drawTools.add(textTool);


        add(buttonBar, BorderLayout.SOUTH);


        if (newOverview.pages.get(newOverview.pNum).getGesture() == true) {
            if (newOverview.pages.get(newOverview.pNum).getWGest() == 1) {
                nextPage();
            } else if (newOverview.pages.get(newOverview.pNum).getWGest() == 2) {
                backPage();
            }
        }
    }

    //page functions


    public void overviewMode(Rectangle r) {
        newOverview.OVMode(r);
        sp.setViewportView(newOverview.overMode);
    }


    //function that adds pages
    public void addPage() {
        newOverview.pageSize++;
        newOverview.pNum++;
        drawArea newPage = new drawArea();
        newOverview.pages.add(newOverview.pNum, newPage);
        sp.setViewportView(newOverview.pages.get(newOverview.pNum));
        ffinkTool.setSelected(true);
        revalidate();
        repaint();
    }

    //function that removes pages
    public void removePage() {
        newOverview.pages.remove(newOverview.pNum);
        newOverview.pageSize--;
        newOverview.pNum--;
        if (newOverview.pageSize == 0) {
            newOverview.pageSize++;
            newOverview.pNum++;
            drawArea newPage = new drawArea();
            newOverview.pages.add(newPage);
            sp.setViewportView(newOverview.pages.get(newOverview.pNum));
            ffinkTool.setSelected(true);
        } else if (newOverview.pNum < 0){
            newOverview.pNum++;
            sp.setViewportView(newOverview.pages.get(newOverview.pNum));
            ffinkTool.setSelected(true);
        } else {
            sp.setViewportView(newOverview.pages.get(newOverview.pNum));
            ffinkTool.setSelected(true);
        }
        revalidate();
        repaint();
    }

    //function that goes to the next page
    public void nextPage(){
        if (newOverview.pNum < newOverview.pageSize - 1) {
            newOverview.pNum++;
            sp.setViewportView(newOverview.pages.get(newOverview.pNum));
            ffinkTool.setSelected(true);
        }
        revalidate();
        repaint();
    }

    //function that goes to the previous page
    public void backPage(){
        if (newOverview.pNum > 0) {
            newOverview.pNum--;
            sp.setViewportView(newOverview.pages.get(newOverview.pNum));;
            ffinkTool.setSelected(true);
        }
        revalidate();
        repaint();
    }

    //getters for the buttons
    public JButton getOverButton() { return overButton; }
    public JButton getNewPage() { return newPage; }
    public JButton getDeletePage() { return deletePage; }
    public JButton getPageForward() { return pageForward; }
    public JButton getPageBackward() { return pageBackward; }
    public JRadioButton getffinkTool() { return ffinkTool; }
    public JRadioButton getOvalTool() { return ovalTool; }
    public JRadioButton getRectangleTool() { return rectangleTool; }
    public JRadioButton getTextTool() { return textTool; }
}
