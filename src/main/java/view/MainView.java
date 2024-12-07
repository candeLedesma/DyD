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
    private ScoredSeriesPanel ratedPanel;
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
        frame.setSize(400, 428);//width=400,height=428

        searchPanel.setPresenter(searchPresenter);


        storagePanel.setPresenter(searchPresenter);
        storagePanel.setSavedPanel();

        searchPanel.setUpView();
        searchPanel.setVisible(true);

        setRatedSeriesPanel();


        try {
            // Set System L&F
            UIManager.put("nimbusSelection", new Color(247,248,250));
            //UIManager.put("nimbusBase", new Color(51,98,140)); //This is redundant!

            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception e) {
            System.out.println("Something went wrong with UI!");
        }


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


    private void setRatedSeriesPanel() {
        ratedPanel.showView();
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
        return searchPanel.getSearchResultTextPane();
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
}
