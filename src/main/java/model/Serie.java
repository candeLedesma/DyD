package model;

import java.util.Date;

public class Serie {
    private String title;
    private String pageID;
    private String snippet;
    private int score;
    private Date lastUpdated;
    private String extract;
    private boolean hasScore = false;

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

    public String getPageID() {
        return pageID;
    }

    public String getSnippet() {
        return snippet;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        this.hasScore = true;
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

    public boolean hasScore() {
        return hasScore;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.lastUpdated = updatedAt;
    }
}