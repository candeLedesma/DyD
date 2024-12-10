package view;

import utils.Serie;
import presenter.Presenter;
import presenter.SeriesPresenter;

import javax.swing.*;
import java.util.LinkedList;

public class SearchPanel extends JPanel{
    private JPanel searchPanel;
    private JTextField searchSerieField;
    private JButton searchButton;
    private JTextPane searchResultsTextPane;
    private JButton saveLocallyButton;
    private JPanel scorePanel;
    private Presenter searchPresenter;
    String serieName = ""; // Last searched text! this variable is central for everything
    private Serie lastSearchedSeries;
    private JLabel scoreLabel;
    private JSlider sliderScore;
    private JButton setScoreButton;

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

    private void showScorePanel() {

        sliderScore.setVisible(true);
        setScoreButton.setVisible(true);

        if (searchPresenter.hasScore()) {
            System.out.println("Score found for this series.");
            int currentScore = searchPresenter.getScoreSerie(lastSearchedSeries.getTitle());
            scoreLabel.setText("Score: " + currentScore);
        } else {
            scoreLabel.setText("Score: No score found for this series.");
        }

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
        showScorePanel();
    }

    public String getSearchResultTextPane() {
        return searchResultsTextPane.getText();
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
}