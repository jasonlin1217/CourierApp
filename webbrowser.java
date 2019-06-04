import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Stack;

import static java.awt.GridBagConstraints.FIRST_LINE_START;
import static java.awt.GridBagConstraints.HORIZONTAL;

public class webbrowser extends JPanel{
    JScrollPane sp;
    public webbrowser() {
        super();

        String initialURL = "http://www.google.com/";
        Stack<String> oldURLs = new Stack<>();
        oldURLs.push(initialURL);
        final JEditorPane ed;

        JLabel labelURL = new JLabel("URL");
        JTextField textURL = new JTextField(initialURL,20);
        JButton btnBrowse = new JButton("Browse");
        JButton btnBack = new JButton("Back");


        setLayout(new FlowLayout());

        add(labelURL);
        add(textURL);
        add(btnBrowse);
        add(btnBack);

        try {
            ed = new JEditorPane(initialURL);
            ed.setSize(new Dimension(150,250));
            ed.setEditable(false);

            ed.addHyperlinkListener(new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent e) {
                    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        JEditorPane pane = (JEditorPane) e.getSource();
                        if (e instanceof HTMLFrameHyperlinkEvent) {
                            HTMLFrameHyperlinkEvent evt =
                                    (HTMLFrameHyperlinkEvent) e;
                            HTMLDocument doc = (HTMLDocument) pane.getDocument();
                            doc.processHTMLFrameHyperlinkEvent(evt);
                        } else {
                            try {
                                pane.setPage(e.getURL());
                            } catch (Throwable t){
                                t.printStackTrace();
                            }
                        }
                    }
                }
            });

            btnBrowse.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                ed.setPage(textURL.getText().trim());
                                oldURLs.push(textURL.getText().trim());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
            );

            btnBack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (!oldURLs.isEmpty()) {
                            ed.setPage(oldURLs.pop());
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            JScrollPane sp = new JScrollPane(ed);
            sp.setSize(new Dimension(200,300));
            add(sp);


        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
}
