package presenter;

import utils.Serie;

import java.util.List;

public interface Presenter {
    List<Serie> getScoredSeries();

    void searchSeries();

    void saveLocally();

    void getSelectedExtract(Serie searchResult);


    String getScoreSerie(String title);

    void recordScore();

    void start();

    Serie getLastSearchedSeries();

    void initializeSavedPanel();

    int getScore();

    void getStoredInfo();

    void deleteStoredInfo();

    void saveStoredInfo();


    Object getLastUpdatedScore();
}
