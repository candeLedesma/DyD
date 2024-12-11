package view;
import utils.Serie;
import presenter.SeriesPresenter;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.LinkedList;

public class MainView implements View {
    private final SeriesPresenter searchPresenter;
    private JPanel contentPane;
    private JTabbedPane tabbedPaneRatedSeries;
    private SearchPanel searchPanel;
    private ScoredSeriesPanel scoredSeriesPanel;
    private StoragePanel storagePanel;


    public MainView(SeriesPresenter searchPresenter) {
        this.searchPresenter = searchPresenter;
    }


    @Override
    public void showView() throws Exception {

        JFrame frame = new JFrame("TV Series");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400, 428);

        setSearchPanel();

        setStoragePanel();

        setScoredSeriesPanel();

        UIManager.put("nimbusSelection", new Color(247,248,250));
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }

    private void setStoragePanel() {
        storagePanel.setPresenter(searchPresenter);
        storagePanel.setSavedPanel();
    }

    private void setSearchPanel() {
        searchPanel.setPresenter(searchPresenter);
        searchPanel.setUpView();
        searchPanel.setVisible(true);
    }

    private void setScoredSeriesPanel() {
        scoredSeriesPanel.setPresenter(searchPresenter);
        scoredSeriesPanel.setUpView();
        scoredSeriesPanel.showView();

    }


    public void setSelectSavedComboBox(Object[] savedTitles) {
        storagePanel.setSelectSavedComboBox(savedTitles);
    }


    public boolean hasSelectedIndex() {
        return storagePanel.existSavedTitle();
    }


    public String getSeletedSavedTitle() {
        return storagePanel.getSeletedSavedTitle();
    }


    public void showSuccessMessage(String s) {
        JOptionPane.showMessageDialog(contentPane, s);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(contentPane, message);
    }


    public void setSearchResultTextPane(String text) throws SQLException {
        searchPanel.setSearchResultTextPane(text);
        searchPanel.showScorePanel();
    }

    public String getSearchResultTextPane() {
        return storagePanel.getStoredTextPane();
    }

    public Serie getLastSearchedSeries() {
        return searchPanel.getLastSearchedSeries();
    }

    public void showResults(LinkedList<Serie> searchResults) {
        searchPanel.showResults(searchResults);
    }

    public void setStoredTextPane(String extract) {
        storagePanel.setStoredTextPane(extract);
    }

    public String getSearchSerieField() {
        return searchPanel.getsSearchSerieField();
    }

    public void deleteSelectedIndex() {
        storagePanel.deleteSelectedIndex();
        storagePanel.showSuccessDeletedMessage();
    }

    public int getScore() {
        return searchPanel.getScoreSliderValue();
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
    public JTabbedPane getTabbedPane() {
        return tabbedPaneRatedSeries;
    }

    public void updateScore() {
        scoredSeriesPanel.updateTable();
    }

    public void showSuccessSaveMessage() {
        storagePanel.showSuccessSavedMessage();
    }
}
