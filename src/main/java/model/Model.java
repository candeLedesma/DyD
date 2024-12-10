package model;

import presenter.SeriesPresenter;
import utils.Serie;
import presenter.Presenter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public interface Model {

    LinkedList<Serie> searchSeries(String seriesName) throws IOException;

    String searchPageExtract(Serie searchResult) throws IOException;

    void deleteSavedInfo(String title);

    Object[] getSavedTitles();

    void saveStoredInfo(String title, String text);

    void saveLocally();

    void setScore();

    boolean hasScore(String title);

    int getScore();

    List<Serie> getScoredSeries();

    String getExtract(String selectedTitle);

    void setPresenter(SeriesPresenter seriesPresenter);

}
