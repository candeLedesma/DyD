package view;

import model.Serie;
import view.SerieMenuItem;
import presenter.Presenter;
import presenter.SeriesPresenter;

import javax.swing.*;
import java.sql.SQLException;
import java.util.LinkedList;

public class SearchPanel extends JPanel {
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
    private LinkedList<SerieMenuItem> resultItems;

    public SearchPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setVisible(true);
        this.add(searchPanel);
    }

    public void setUpView() {
        configureSearchResultsTextPane();
        configureSearchButton();
        configureSaveLocallyButton();
        configureSliderScore();
        scorePanel.setVisible(true);
    }

    private void configureSearchResultsTextPane() {
        searchResultsTextPane.setContentType("text/html");
    }

    private void configureSearchButton() {
        searchButton.addActionListener(e -> searchPresenter.searchSeries());
    }

    private void configureSaveLocallyButton() {
        saveLocallyButton.addActionListener(actionEvent -> searchPresenter.saveLocally());
    }

    private void configureSliderScore() {
        sliderScore.setMinimum(1);
        sliderScore.setMaximum(10);
    }

    public void showResults(LinkedList<Serie> results) {
        searchPresenter.handleShowResults(results, searchResultsTextPane, this);
    }

    public void showScorePanel() throws SQLException {
        sliderScore.setVisible(true);
        setScoreButton.setVisible(true);

        String currentScore = searchPresenter.getScoreSerie(searchPresenter.getLastSearchedSeries().getTitle());
        updateScoreLabel(currentScore);

        scoreLabel.repaint();

        sliderScore.addChangeListener(e -> {
            if (!sliderScore.getValueIsAdjusting()) {
                updateScoreLabel(String.valueOf(sliderScore.getValue()));
            }
        });

        setScoreButton.addActionListener(e -> searchPresenter.recordScore());
    }

    private void updateScoreLabel(String value) {
        scoreLabel.setText("Score: " + value);
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

    public SerieMenuItem getResult() {
        return resultItems.isEmpty() ? null : resultItems.get(0);
    }

    public String getSearchResultTextPane() {
        return searchResultsTextPane.getText();
    }
}
