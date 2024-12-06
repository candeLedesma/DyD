package view;

import utils.Serie;
import presenter.SearchPresenter;
import presenter.SearchPresenterImp;

import javax.swing.*;
import java.util.LinkedList;

public class SearchPanel extends JPanel{
    private JPanel searchPanel;
    private JTextField searchSerieField;
    private JButton searchButton;
    private JTextPane searchResultsTextPane;
    private JButton saveLocallyButton;
    private JPanel setScorePanel;
    private SearchPresenter searchPresenter;
    String serieName = ""; // Last searched text! this variable is central for everything
    private Serie lastSearchedSeries;

    public SearchPanel() {
        this.setVisible(true);

        this.add(searchPanel);
    }

    public void setUpView() {
        searchResultsTextPane.setContentType("text/html");

        searchButton.addActionListener(e -> searchPresenter.searchSeries());

        saveLocallyButton.addActionListener(actionEvent -> {
            if (!serieName.isEmpty()) {
                searchPresenter.saveLocally();
            }
        });
    }

    public void showResults(LinkedList<Serie> results) {
        JPopupMenu searchOptionsMenu = new JPopupMenu("Search Results");
        for (Serie searchResult : results) {
            searchResult.addActionListener(actionEvent -> {
                lastSearchedSeries = searchResult;
                serieName = searchResult.getTitle();
                searchPresenter.getSelectedExtract(searchResult);
            });
            searchOptionsMenu.add(searchResult);
        }
        searchOptionsMenu.show(searchResultsTextPane, searchResultsTextPane.getX(), searchResultsTextPane.getY());
    }

    public void setPresenter(SearchPresenterImp searchPresenter) {
        this.searchPresenter = searchPresenter;
    }

    public Serie getLastSearchedSeries() {
        return this.lastSearchedSeries;
    }

    public void setSearchResultTextPane(String seriesName) {
        this.serieName = seriesName;
        searchResultsTextPane.setText(seriesName);
        searchResultsTextPane.setCaretPosition(0);
    }

    public String getSearchResultTextPane() {
        return searchResultsTextPane.getText();
    }

    public String getSeriesName() {
        return serieName;
    }

    public String getsSearchSerieField() {
        return searchSerieField.getText();
    }
}