package view;
import model.Serie;
import presenter.MainPresenter;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

public class MainView implements SeriesView, View {
    private final MainPresenter seriesPresenter;
    private JPanel contentPane;
    private JTabbedPane tabbedPaneRatedSeries;
    private SearchPanel searchPanel;
    private ScoredSeriesPanel scoredSeriesPanel;
    private StoragePanel storagePanel;


    public MainView(MainPresenter searchPresenter) {
        this.seriesPresenter = searchPresenter;
    }


    @Override
    public void showView() {

        JFrame frame = new JFrame("TV Series");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400, 428);

        setUpPanels();

        UIManager.put("nimbusSelection", new Color(247,248,250));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            showErrorMessage("Error setting the look and feel");
        }
    }

    public void setUpPanels() {
        setSearchPanel();
        setStoragePanel();
        setScoredSeriesPanel();
    }

    private void setStoragePanel() {
        storagePanel.setPresenter(seriesPresenter);
        storagePanel.showView();
    }

    private void setSearchPanel() {
        searchPanel.setPresenter(seriesPresenter);
        searchPanel.setUpView();
        searchPanel.setVisible(true);
    }

    private void setScoredSeriesPanel() {
        scoredSeriesPanel.setPresenter(seriesPresenter);
        scoredSeriesPanel.setUpView();
        scoredSeriesPanel.showView();

    }


    @Override
    public void setSelectSavedComboBox(Object[] savedTitles) {
        storagePanel.setSelectSavedComboBox(savedTitles);
    }


    public boolean hasSelectedIndex() {
        return storagePanel.existSavedTitle();
    }


    @Override
    public String getSeletedSavedTitle() {
        return storagePanel.getSeletedSavedTitle();
    }


    @Override
    public void showSuccessMessage(String s) {
        JOptionPane.showMessageDialog(contentPane, s);
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(contentPane, message);
    }


    @Override
    public void setSearchResultTextPane(String text) throws SQLException {
        searchPanel.setSearchResultTextPane(text);
        searchPanel.showView();
    }


    public String getSearchResultTextPane() {
        return storagePanel.getStoredTextPane();
    }


    public Serie getLastSearchedSeries() {
        return seriesPresenter.getLastSearchedSeries();
    }

    public void showResults(LinkedList<Serie> searchResults) {
        searchPanel.showResults(searchResults);
    }

    @Override
    public void setStoredTextPane(String extract) {
        storagePanel.setStoredTextPane(extract);
    }

    @Override
    public String getSearchSerieField() {
        return searchPanel.getsSearchSerieField();
    }

    @Override
    public void deleteSelectedIndex() {
        storagePanel.deleteSelectedIndex();
        storagePanel.showSuccessDeletedMessage();
    }

    @Override
    public int getScore() {
        return searchPanel.getScoreSliderValue();
    }

    @Override
    public JTabbedPane getTabbedPane() {
        return tabbedPaneRatedSeries;
    }

    @Override
    public SearchPanel getSearchView() {
        return searchPanel;
    }

    @Override
    public StoragePanel getStoredView() {
        return storagePanel;
    }

    @Override
    public ScoredSeriesPanel getScoredView() {
        return scoredSeriesPanel;
    }


    @Override
    public void updateScore() {
        scoredSeriesPanel.updateTable();
    }


    public void showSuccessSaveMessage() {
        storagePanel.showSuccessSavedMessage();
    }

    public void addSerieToTable(String title, int score, Date lastUpdated) {
        scoredSeriesPanel.addSerieToTable(title, score, lastUpdated);
    }


    public void setMenuItem(SerieMenuItem menuItem) {
        searchPanel.addMenuItem(menuItem);
    }
}
