package presenter;

import utils.Serie;
import view.SearchPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.LinkedList;
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

    void handleShowResults(LinkedList<Serie> results, JTextPane searchResultsTextPane, SearchPanel searchPanel);

    void updateScoredSeriesTable();
}
