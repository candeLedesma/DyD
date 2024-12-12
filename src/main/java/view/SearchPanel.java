package view;

import model.Serie;
import presenter.SeriesPresenter;
import presenter.MainPresenter;

import javax.swing.*;
import java.sql.SQLException;
import java.util.LinkedList;

public class SearchPanel extends JPanel implements View {
    private JPanel searchPanel;
    private JTextField searchSerieField;
    private JButton searchButton;
    private JTextPane searchResultsTextPane;
    private JButton saveLocallyButton;
    private JPanel scorePanel;
    private SeriesPresenter presenter;
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
        resultItems = new LinkedList<>();
    }

    @Override
    public void showView() throws SQLException {
        sliderScore.setVisible(true);
        setScoreButton.setVisible(true);

        String currentScore = presenter.getScoreSerie(presenter.getLastSearchedSeries().getTitle());
        updateScoreLabel(currentScore);

        scoreLabel.repaint();

        sliderScore.addChangeListener(e -> {
            if (!sliderScore.getValueIsAdjusting()) {
                updateScoreLabel(String.valueOf(sliderScore.getValue()));
            }
        });

        setScoreButton.addActionListener(e -> {
            presenter.recordScore();
        });
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
        searchButton.addActionListener(e -> presenter.searchSeries());
    }

    private void configureSaveLocallyButton() {
        saveLocallyButton.addActionListener(actionEvent -> presenter.saveLocally());
    }

    private void configureSliderScore() {
        sliderScore.setMinimum(1);
        sliderScore.setMaximum(10);
    }

    public void showResults(LinkedList<Serie> results) {
        presenter.handleShowResults(results, searchResultsTextPane, this);
    }


    private void updateScoreLabel(String value) {
        scoreLabel.setText("Score: " + value);
    }

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
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
        return resultItems.get(0);
    }

    public String getSearchResultTextPane() {
        return searchResultsTextPane.getText();
    }

    public void addMenuItem(SerieMenuItem menuItem) {
        resultItems.add(menuItem);
    }
}
