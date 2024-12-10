package model;

import presenter.SeriesPresenter;
import utils.Serie;
import presenter.Presenter;

import java.util.LinkedList;
import java.util.List;

public interface Model {

    LinkedList<Serie> searchSeries(String seriesName);

    String searchPageExtract(Serie searchResult);

    void deleteSavedInfo(String title);

    Object[] getSavedTitles();

    void saveStoredInfo(String title, String text);

    void saveLocally();

    void setScore();

    boolean hasScore(Serie serie);

    int getScore();

    List<Serie> getScoredSeries();

    String getExtract(String selectedTitle);

    void setPresenter(SeriesPresenter seriesPresenter);

}
