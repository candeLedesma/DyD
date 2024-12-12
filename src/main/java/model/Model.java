package model;

import presenter.SeriesPresenter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public interface Model {

    LinkedList<Serie> searchSeries(String seriesName) throws IOException;

    String searchPageExtract(Serie searchResult) throws IOException;

    void deleteSavedInfo(String title);

    Object[] getSavedTitles();

    void saveStoredInfo(String title, String text);

    void saveLocally();

    void setScore() throws SQLException;

    boolean hasScore(String title) throws SQLException;

    int getScore() throws SQLException;

    List<Serie> getScoredSeries() throws SQLException;

    String getExtract(String selectedTitle);

    void setPresenter(SeriesPresenter seriesPresenter);

}
