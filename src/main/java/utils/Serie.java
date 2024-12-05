package utils;

import javax.swing.*;

public class Serie extends JMenuItem {
    public String title;
    public String pageID;
    public String snippet;
    public int score;

    public Serie(String title, String pageID, String snippet) {
        String itemText = "<html><font face=\"arial\">" + title + ": " + snippet;
        itemText =itemText.replace("<span class=\"searchmatch\">", "")
                .replace("</span>", "");
        this.setText(itemText);
        this.title = title;
        this.pageID = pageID;
        this.snippet = snippet;
    }

    public String getTitle() {
        return title;
    }

    public String getPageID() {
        return pageID;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }


}
