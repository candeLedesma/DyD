package utils;

import model.API.WikipediaSearchAPI;

import javax.swing.*;
import java.util.Date;

public class Serie extends JMenuItem {

    public String title;

    public String pageID;

    public String snippet;

    public int score;

    private Date lastUpdated;

    public String extract;

    public boolean hasScore = false;


    public Serie(String title, String pageID, String snippet) {
        this.title = title;
        this.pageID = pageID;
        this.snippet = snippet;

        setTextToDisplay();
    }

    public Serie(String title, int score) {
        this.title = title;
        this.score = score;
        hasScore = true;

        setTextToDisplay();
    }


    private void setTextToDisplay() {
        String itemText = "<html><font face=\"arial\">" + title + ": " + snippet;
        itemText = itemText.replace("<span class=\"searchmatch\">", "")
                .replace("</span>", "");
        this.setText(itemText);
    }

    public String getTitle() {
        return title;
    }

    public void setScore(int score) {
        this.score = score;
        hasScore = true;
    }


    public int getScore() {
        return score;
    }

    public String getExtract() {
        return extract;
    }

    public void setExtract(String extract) {
        this.extract = extract;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setUpdatedAt(java.sql.Date updatedAt) {
        this.lastUpdated = updatedAt;
    }
}
