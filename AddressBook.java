import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

import static java.awt.GridBagConstraints.FIRST_LINE_START;
import static java.awt.GridBagConstraints.HORIZONTAL;

public class AddressBook extends JPanel {

    JTable addrTable;
    Object[][] z;
    JTextField moreInfo1 = new JTextField();
    JTextField phoneNum = new JTextField();
    JTextField age = new JTextField();
//    JPanel moreInfo = new JPanel();
    public AddressBook() {
        super();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = FIRST_LINE_START;
        c.fill = HORIZONTAL;
        String[] columnNames = {"Name"};

        Object[][] data = {
                {"Bob", "Jones", "123-456-7890", 12},
                {"Harry", "Fam", "098-765-4321", 21},
                {"Larry", "Man", "111-222-3333", 33},
                {"Bob", "Cake", "333-444-5555", 44},
                {"Andrew", "Johnson", "222-555-6666", 55},
                {"Larry", "Guy", "333-111-3333", 66},
                {"Bob", "Jones", "123-456-7890", 77},
                {"Harry", "Fam", "098-765-4321", 88},
                {"Larry", "Man", "111-222-3333", 11},
                {"Bob", "Cake", "333-444-5555", 12},
                {"Andrew", "Johnson", "222-555-6666", 15},
                {"Larry", "Guy", "333-111-3333", 16},
                {"Bob", "Jones", "123-456-7890", 17},
                {"Harry", "Fam", "098-765-4321", 18},
                {"Larry", "Man", "111-222-3333", 24},
                {"Bob", "Cake", "333-444-5555", 28},
                {"Andrew", "Johnson", "222-555-6666", 77},
                {"Larry", "Guy", "333-111-3333", 42},
                {"Bob", "Jones", "123-456-7890", 41},
                {"Harry", "Fam", "098-765-4321", 53},
                {"Larry", "Man", "111-222-3333", 51},
                {"Bob", "Cake", "333-444-5555", 61},
                {"Andrew", "Johnson", "222-555-6666", 31},
                {"Larry", "Guy", "333-111-3333", 21},
        };

        z = new Object[data.length][1];

        for (int i = 0; i < data.length; i++) {
            z[i][0] = data[i][0];
        }

        addrTable = new JTable(z, columnNames);
        addrTable.setSize(new Dimension(200,300));
        addrTable.setPreferredScrollableViewportSize(new Dimension(200,300));
        addrTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(addrTable);
        scrollPane.setSize(new Dimension(200,300));
        add(scrollPane, c);

        addrTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int i = addrTable.getSelectedRow();
                String name = data[i][0].toString() + " " + data[i][1].toString();
                String phone = data[i][2].toString();
                String aged = data[i][3].toString();
                moreInfo1.setText("Full Name: " + name);
                phoneNum.setText("Phone Number: " + phone);
                age.setText("Age: " + aged);
            }
        });

        moreInfo1.setColumns(1);
        moreInfo1.setEditable(false);
        moreInfo1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        phoneNum.setColumns(1);
        phoneNum.setEditable(false);
        phoneNum.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        age.setColumns(1);
        age.setEditable(false);
        age.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        //add the JTextField to panel
        c.gridx = 0;
        c.gridy = 1;
        add(moreInfo1, c);
        c.gridy = 2;
        add(phoneNum, c);
        c.gridy = 3;
        add(age, c);

    }
}
