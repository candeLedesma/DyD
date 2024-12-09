package presenter;

import utils.Serie;

import java.util.List;

public interface SearchPresenter {

    void searchSeries();

    void getSelectedExtract(Serie searchResult);

    void deleteStoredInfo();

    void saveStoredInfo();


    void saveLocally();

    Serie getLastSearchedSeries();

    void recordScore();

    boolean hasScore();

    int getScore();

    int getScoreSerie(String title);

    List<Serie> getScoredSeries();
}
