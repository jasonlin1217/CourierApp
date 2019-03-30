import javax.swing.*;
import java.awt.*;

public class TabPanel extends JTabbedPane {

    AddressBook addrbook = new AddressBook();
    webbrowser browser = new webbrowser();

    //Creates an address book tab and Web Browser tab that when selected
    //bring up the address book and the web browser.
    public TabPanel() {
        super();
        addTab("Address Book", addrbook);
        addTab("Web Browser", browser);


    }

}
