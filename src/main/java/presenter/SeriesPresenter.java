package presenter;

import model.SeriesModel;
import utils.Serie;
import view.MainView;
import view.SearchPanel;
import view.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SeriesPresenter implements Presenter {
    private final MainView view;
    private final SeriesModel model;

    private final ScoredPresenter scoredPresenter;
    private final StoredPresenter storedPresenter;
    private final SearchPresenter searchPresenter;

    private Serie lastSearchedSeries;

    public SeriesPresenter(SeriesModel model) {
        this.model = model;
        model.setPresenter(this);
        this.view = new MainView(this);
        this.scoredPresenter = new ScoredPresenter(view, model);
        this.storedPresenter = new StoredPresenter(view, model);
        this.searchPresenter = new SearchPresenter(view, model);
    }

    @Override
    public void start() {
        try {
            view.showView();
        } catch (Exception e) {
            view.showErrorMessage(e.getMessage());
        }
    }

    @Override
    public Serie getLastSearchedSeries() {
        return lastSearchedSeries;
    }

    @Override
    public void initializeSavedPanel() {
        storedPresenter.initializeSavedPanel();
    }

    @Override
    public int getScore() {
        return view.getScore();
    }

    @Override
    public void getStoredInfo() {
        storedPresenter.getStoredInfo();
    }

    @Override
    public void deleteStoredInfo() {
        storedPresenter.deleteStoredInfo();
    }

    @Override
    public void saveStoredInfo() {
        storedPresenter.saveStoredInfo();
    }


    @Override
    public boolean hasScore(String title) {
        return model.hasScore(title);
    }

    @Override
    public void showError(String messageError) {
        view.showErrorMessage(messageError);
    }

    @Override
    public void handleShowResults(LinkedList<Serie> results, JTextPane searchResultsTextPane, SearchPanel searchPanel) {
        JPopupMenu searchOptionsMenu = new JPopupMenu("Search Results");

        for (Serie searchResult : results) {
            boolean hasScore = hasScore(searchResult.getTitle());
            String displayTitle = hasScore ? "★ " + searchResult.getTitle() : searchResult.getTitle();
            JMenuItem menuItem = new JMenuItem(displayTitle);

            menuItem.addActionListener(actionEvent -> {
                lastSearchedSeries = searchResult;
                try {
                    getSelectedExtract(searchResult);
                } catch (SQLException e) {
                    showError(e.getMessage());
                }
            });

            searchOptionsMenu.add(menuItem);
        }
        searchOptionsMenu.show(searchResultsTextPane, searchResultsTextPane.getX(), searchResultsTextPane.getY());
    }

    @Override
    public void updateScoredSeriesTable() {
        scoredPresenter.updateScoredSeriesTable(getScoredSeries());
    }

    @Override
    public void showSuccess(String scoreSetSuccessfully) {
        view.showSuccessMessage(scoreSetSuccessfully);
    }


    @Override
    public List<Serie> getScoredSeries() {
        return model.getScoredSeries();
    }

    @Override
    public void searchSeries() {
        searchPresenter.searchSeries();
    }

    @Override
    public void saveLocally() {
        storedPresenter.saveLocally();

    }

    @Override
    public void getSelectedExtract(Serie searchResult) throws SQLException {
        searchPresenter.getSelectedExtract(searchResult);
    }


    @Override
    public String getScoreSerie(String title) throws SQLException {
        if (model.hasScore(view.getLastSearchedSeries().getTitle())) {
            return String.valueOf(model.getScore());
        }else{
            return "Not found";
        }
    }

    @Override
    public void recordScore() {
        try {
            scoredPresenter.recordScore();
        } catch (SQLException e) {
            view.showErrorMessage(e.getMessage());
        }
    }

    public View getView() {
        return view;
    }
}
