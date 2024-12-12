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
    private JLabel scoreLabel;
    private JSlider sliderScore;
    private JButton setScoreButton;
    private LinkedList<Serie> results;

    public SearchPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setVisible(true);
        this.add(searchPanel);
    }

    public void setUpView() {

        searchResultsTextPane.setContentType("text/html");

        searchButton.addActionListener(e -> searchPresenter.searchSeries());

        saveLocallyButton.addActionListener(actionEvent -> {
            searchPresenter.saveLocally();
        });


        sliderScore.setMinimum(1);
        sliderScore.setMaximum(10);

        scorePanel.setVisible(true);
    }


    public void showResults(LinkedList<Serie> results) {
        this.results = results;
        searchPresenter.handleShowResults(results, searchResultsTextPane, this);
    }

    public void showScorePanel() throws SQLException {

        sliderScore.setVisible(true);
        setScoreButton.setVisible(true);

        String currentScore = searchPresenter.getScoreSerie(searchPresenter.getLastSearchedSeries().getTitle());
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

    public void setSearchResultTextPane(String seriesName) {
        searchResultsTextPane.setText(seriesName);
        searchResultsTextPane.setCaretPosition(0);
    }

    public String getsSearchSerieField() {
        return searchSerieField.getText();
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

    public String getSearchResultTextPane() {
        return searchResultsTextPane.getText();
    }
}