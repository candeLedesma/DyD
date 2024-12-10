package presenter;

import model.SeriesModel;
import utils.Serie;
import view.MainView;

import javax.swing.*;
import java.io.IOException;
import java.util.LinkedList;

public class SearchPresenter {
    private final MainView view;
    private final SeriesModel model;

    public SearchPresenter(MainView view, SeriesModel model) {
        this.view = view;
        this.model = model;
    }

    public void searchSeries() {
        new Thread(() -> {
            String seriesName = view.getSearchSerieField();
            LinkedList<Serie> results = null;
            try {
                results = model.searchSeries(seriesName);
            } catch (IOException e) {
                view.showErrorMessage(e.getMessage());
            }
            view.showResults(results);
        }).start();
    }


    public void getSelectedExtract(Serie selectedResult) {
        String extract = null;
        try {
            extract = model.searchPageExtract(selectedResult);
        } catch (IOException e) {
            view.showErrorMessage(e.getMessage());
        }
        view.setSearchResultTextPane(extract);
    }

    public void showResults(LinkedList<Serie> results) {
        JPopupMenu searchOptionsMenu = new JPopupMenu("Search Results");

        for (Serie searchResult : results) {
            boolean hasScore = hasScore(searchResult.getTitle());
            String displayTitle = hasScore ? "★ " + searchResult.getTitle() : searchResult.getTitle();
            JMenuItem menuItem = new JMenuItem(displayTitle);

            menuItem.addActionListener(actionEvent -> {
                view.setLastSearchedSeries(searchResult);
                view.setSerieName(searchResult.getTitle());
                getSelectedExtract(searchResult);
            });

            searchOptionsMenu.add(menuItem);
        }
        view.showSearchOptionsMenu(searchOptionsMenu);
    }

    private boolean hasScore(String title) {
        return model.hasScore(title);
    }
}
