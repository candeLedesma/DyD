package view;

import utils.Serie;
import presenter.Presenter;
import presenter.SeriesPresenter;

import javax.swing.*;
import java.sql.SQLException;
import java.util.LinkedList;

public class SearchPanel extends JPanel{
    private JPanel searchPanel;
    private JTextField searchSerieField;
    private JButton searchButton;
    private JTextPane searchResultsTextPane;
    private JButton saveLocallyButton;
    private JPanel scorePanel;
    private Presenter searchPresenter;
    String serieName = "";
    private Serie lastSearchedSeries;
    private JLabel scoreLabel;
    private JSlider sliderScore;
    private JButton setScoreButton;
    private LinkedList<Serie> results;

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


        sliderScore.setMinimum(1);
        sliderScore.setMaximum(10);

        scorePanel.setVisible(true);
    }



    public void showResults(LinkedList<Serie> results) {
        JPopupMenu searchOptionsMenu = new JPopupMenu("Search Results");

        this.results = results;

        for (Serie searchResult : results) {
            boolean hasScore = searchPresenter.hasScore(searchResult.getTitle());
            String displayTitle = hasScore ? "★ " + searchResult.getTitle() : searchResult.getTitle();
            JMenuItem menuItem = new JMenuItem(displayTitle);

            menuItem.addActionListener(actionEvent -> {
                lastSearchedSeries = searchResult;
                serieName = searchResult.getTitle();
                try {
                    searchPresenter.getSelectedExtract(searchResult);
                } catch (SQLException e) {
                    searchPresenter.showError(e.getMessage());
                }
            });

            searchOptionsMenu.add(menuItem);
        }
        searchOptionsMenu.show(searchResultsTextPane, searchResultsTextPane.getX(), searchResultsTextPane.getY());
    }

    public void showScorePanel() throws SQLException {

        sliderScore.setVisible(true);
        setScoreButton.setVisible(true);


        String currentScore = searchPresenter.getScoreSerie(lastSearchedSeries.getTitle());
        scoreLabel.setText("Score: " + currentScore);

        scoreLabel.repaint();

        sliderScore.addChangeListener(e -> {
            if (!sliderScore.getValueIsAdjusting()) {
                scoreLabel.setText("Score: "+ sliderScore.getValue());
            }
        });

        setScoreButton.addActionListener(e -> {
            searchPresenter.recordScore();
        });


    }


    public void setPresenter(SeriesPresenter searchPresenter) {
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

    public String getsSearchSerieField() {
        return searchSerieField.getText();
    }

    public void showSuccessMessage() {
        JOptionPane.showMessageDialog(this, "The series was correctly saved!");
    }

    public int getScoreSliderValue() {
        return sliderScore.getValue();
    }

    public AbstractButton getSearchButton() {
        return searchButton;
    }

    public Serie getResult() {
        return results.get(0);
    }
}