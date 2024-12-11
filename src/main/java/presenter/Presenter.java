package presenter;

import utils.Serie;

import java.sql.SQLException;
import java.util.List;

public interface Presenter {
    List<Serie> getScoredSeries();

    void searchSeries();

    void saveLocally();

    void getSelectedExtract(Serie searchResult) throws SQLException;

    String getScoreSerie(String title) throws SQLException;

    void recordScore();

    void start();

    Serie getLastSearchedSeries();

    void initializeSavedPanel();

    int getScore();

    void getStoredInfo();

    void deleteStoredInfo();

    void saveStoredInfo();

    boolean hasScore(String title);

    void showError(String errorGettingScoredSeries);

}
