package presenter;

import model.Serie;
import view.SearchPanel;

import javax.swing.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public interface SeriesPresenter {

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

    void showError(String messageError);

    void handleShowResults(LinkedList<Serie> results, JTextPane searchResultsTextPane, SearchPanel searchPanel);

    void updateScoredSeriesTable();

    void showSuccess(String message);
}

