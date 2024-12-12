package model;

import java.util.Date;

public class Serie {

    private String title;
    private String pageID;
    private String snippet;
    private int score;
    private Date lastUpdated;
    private String extract;
    private boolean hasScore;

    public Serie(String title, String pageID, String snippet) {
        this.title = title;
        this.pageID = pageID;
        this.snippet = snippet;
    }

    public Serie(String title, int score) {
        this.title = title;
        this.score = score;
        this.hasScore = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPageID() {
        return pageID;
    }

    public void setPageID(String pageID) {
        this.pageID = pageID;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        this.hasScore = true;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getExtract() {
        return extract;
    }

    public void setExtract(String extract) {
        this.extract = extract;
    }

    public boolean hasScore() {
        return hasScore;
    }
}
