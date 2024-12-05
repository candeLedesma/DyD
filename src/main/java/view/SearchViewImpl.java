package view;
import model.DataBaseImp;
import utils.SearchResult;
import model.RatedSeries;
import presenter.SearchPresenterImp;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class SearchViewImpl implements SearchView {
    private final SearchPresenterImp searchPresenter;
    private JPanel contentPane;
    private JTabbedPane tabbedPaneRatedSeries;
    private SearchPanel searchPanel;
    private RatedPanel ratedPanel;
    private StoragePanel storagePanel;
    //private StoragePanel storagePanel;


    String selectedResultTitle = null; //For storage purposes, it may not coincide with the searched term (see below)


    public SearchViewImpl(SearchPresenterImp searchPresenter) {
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

    //------------------StoragePanel methods------------------

    public void setSelectSavedComboBox(Object[] savedTitles) {
        storagePanel.setSelectSavedComboBox(savedTitles);
    }


    private void setSavedPanel() {
        storagePanel.setSavedPanel();

    }

    private void setPopupMenu() {
        storagePanel.setPopupMenu();

    }

    private void setComboBox() {
        storagePanel.setComboBox();
    }



    public boolean existSavedTitle() {
        return storagePanel.existSavedTitle();
    }


    public String getSeletedSavedTitle() {
        return storagePanel.getSeletedSavedTitle();
    }

    //------------------RatedPanel methods------------------


    public void showRating(int rating) {


    }


    public int getRatingInput() {
        return 0;
    }


    public void showRatedSeries(List<RatedSeries> ratedSeries) {
        ratedPanel.showRatedSeries(ratedSeries);
    }

    private void setRatedSeriesPanel() {
        ratedPanel.setRatedSeriesPanel();
    }



    //------------------SearchPanel methods------------------


    public void showSuccessMessage(String s) {
        JOptionPane.showMessageDialog(contentPane, s);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(contentPane, message);
    }

    public String getSeriesName() {
        return searchPanel.getSeriesName();
    }

    public void setSearchResultTextPane(String text) {
        searchPanel.setSearchResultTextPane(text);
    }

    public String getSearchResultTextPane() {
        return searchPanel.getSearchResultTextPane();
    }

    public SearchResult getLastSearchedSeries() {
        return searchPanel.getLastSearchedSeries();
    }

    @Override
    public void showResults(LinkedList<SearchResult> results) {
        searchPanel.showResults(results);
    }
}
