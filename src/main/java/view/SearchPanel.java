package view;

import utils.SearchResult;
import presenter.SearchPresenter;
import presenter.SearchPresenterImp;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class SearchPanel extends JPanel implements SearchView {
    private JPanel searchPanel;
    private JTextField searchText;
    private JButton searchButton;
    private JTextPane searchResultsTextPane;
    private JButton saveLocallyButton;
    private SearchPresenter searchPresenter;
    String text = ""; // Last searched text! this variable is central for everything
    private SearchResult lastSearchedSeries;

    public SearchPanel() {
        this.setVisible(true);

        this.add(searchPanel);

    }

    public void setUpView() {
        searchResultsTextPane.setContentType("text/html");

        searchButton.addActionListener(e -> searchPresenter.searchSeries());

        saveLocallyButton.addActionListener(actionEvent -> {
            if (!text.isEmpty()) {
                searchPresenter.saveLocally();
            }
        });
    }

    @Override
    public void showView() {

    }

    @Override
    public void showResults(LinkedList<SearchResult> results) {
        JPopupMenu searchOptionsMenu = new JPopupMenu("Search Results");
        for (SearchResult searchResult : results) {
            searchResult.addActionListener(actionEvent -> {
                lastSearchedSeries = searchResult;
                searchPresenter.getSelectedExtract(searchResult);
            });
            searchOptionsMenu.add(searchResult);
        }
        searchOptionsMenu.show(searchResultsTextPane, searchResultsTextPane.getX(), searchResultsTextPane.getY());
    }

    public void setPresenter(SearchPresenterImp searchPresenter) {
        this.searchPresenter = searchPresenter;
    }

    public SearchResult getLastSearchedSeries() {
        return this.lastSearchedSeries;
    }

    public void setSearchResultTextPane(String text) {
        searchResultsTextPane.setText(text);
    }

    public String getSearchResultTextPane() {
        return searchResultsTextPane.getText();
    }

    public String getSeriesName() {
        text = searchText.getText();
        return text;
    }
}