package view;

import javax.swing.*;

public class SerieMenuItem extends JMenuItem {

    public SerieMenuItem(String title, String snippet) {
        setTextToDisplay(title, snippet);
    }

    public void setTextToDisplay(String title, String snippet) {
        String itemText = "<html><font face=\"arial\">" + title + ": " + snippet;
        itemText = itemText.replace("<span class=\"searchmatch\">", "")
                .replace("</span>", "");
        this.setText(itemText);
    }

}
