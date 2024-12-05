package model;

public class RatedSeries {

    private String title;

    private int rating;

    private String lastUpdated;

    public RatedSeries(String title, int rating, String lastUpdated) {
        this.title = title;
        this.rating = rating;
        this.lastUpdated = lastUpdated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}