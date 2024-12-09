package view;
import utils.Serie;
import presenter.SearchPresenterImp;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class MainView implements SearchView {
    private final SearchPresenterImp searchPresenter;
    private JPanel contentPane;
    private JTabbedPane tabbedPaneRatedSeries;
    private SearchPanel searchPanel;
    private ScoredSeriesPanel scoredSeriesPanel;
    private StoragePanel storagePanel;


    public MainView(SearchPresenterImp searchPresenter) {
        this.searchPresenter = searchPresenter;
    }


    @Override
    public void showView() {

        JFrame frame = new JFrame("TV Series Info Repo");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400, 428);

        setSearchPanel();

        setStoragePanel();

        setScoredSeriesPanel();


        try {
            UIManager.put("nimbusSelection", new Color(247,248,250));

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.out.println("Something went wrong with UI!");
        }


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


    public void setSearchResultTextPane(String text) {
        searchPanel.setSearchResultTextPane(text);
    }

    public String getSearchResultTextPane() {
        return storagePanel.getStoredTextPane();
    }

    public Serie getLastSearchedSeries() {
        return searchPanel.getLastSearchedSeries();
    }

    @Override
    public void showResults(LinkedList<Serie> results) {
        searchPanel.showResults(results);
    }

    public void setStoredTextPane(String extract) {
        storagePanel.setStoredTextPane(extract);
    }

    public String getSearchSerieField() {
        return searchPanel.getsSearchSerieField();
    }

    public void deleteSelectedIndex() {
        storagePanel.deleteSelectedIndex();
        searchPanel.showSuccessMessage();
    }

    public int getScore() {
        return searchPanel.getScoreSliderValue();
    }

    public void atualizeScore() {
        scoredSeriesPanel.atualizeScore();
    }
}
