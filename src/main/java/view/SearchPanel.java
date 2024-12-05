package view;

import utils.SearchResult;
import presenter.SearchPresenter;
import presenter.SearchPresenterImp;

import javax.swing.*;
import java.util.LinkedList;

public class SearchPanel extends JPanel{
    private JPanel searchPanel;
    private JTextField searchText;
    private JButton searchButton;
    private JTextPane searchResultsTextPane;
    private JButton saveLocallyButton;
    private JPanel setScorePanel;
    private SearchPresenter searchPresenter;
    String serieName = ""; // Last searched text! this variable is central for everything
    private SearchResult lastSearchedSeries;

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

    public void showResults(LinkedList<SearchResult> results) {
        JPopupMenu searchOptionsMenu = new JPopupMenu("Search Results");
        for (SearchResult searchResult : results) {
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

    public SearchResult getLastSearchedSeries() {
        return this.lastSearchedSeries;
    }

    public void setSearchResultTextPane(String text) {
        searchResultsTextPane.setText(text);
    }

    public String getSearchResultTextPane() {
        System.out.println("RESULTADO DE BUSQUEDA "+searchResultsTextPane.getText());
        return searchResultsTextPane.getText();
    }

    public String getSeriesName() {
        return serieName;
    }
}