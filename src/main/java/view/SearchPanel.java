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
    private JPanel scorePanel;
    private SearchPresenter searchPresenter;
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
        System.out.println("Showing score panel");

        sliderScore.setVisible(true);
        setScoreButton.setVisible(true);

        if (lastSearchedSeries.hasScore()) {
            int currentScore = lastSearchedSeries.getScore();
            scoreLabel.setText(scoreLabel.getText() + currentScore);
            sliderScore.setValue(currentScore); // Mostrar puntuación en el slider
        } else {
            scoreLabel.setText(scoreLabel.getText()+" No score found for this series.");
            sliderScore.setValue(0); // Slider en 0, pero sin que sea obligatorio puntuar
        }

        scoreLabel.repaint();


        // Añadir un listener para el slider, permitiendo modificar la puntuación
        sliderScore.addChangeListener(e -> {
            if (!sliderScore.getValueIsAdjusting()) { // Solo guardar cuando el usuario suelte el slider
                int newScore = sliderScore.getValue();
                lastSearchedSeries.setScore(newScore); // Actualizar la puntuación en la serie
                scoreLabel.setText("Score: "+ newScore); // Mostrar la nueva puntuación
            }
        });


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

}