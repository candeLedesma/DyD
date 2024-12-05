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
    private JTabbedPane textPaneRatedSeries;
    private SearchPanel searchPanel;
    private JPanel storagePanel;
    private JComboBox storedSeriesComboBox;
    private JTextPane storedInfoTextPane;
    private RatedPanel ratedPanel;

    DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
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


        setSavedPanel();

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


    private void setRatedSeriesPanel() {
        ratedPanel.setRatedSeriesPanel();
    }
    private void setSavedPanel() {

        setComboBox();

        storedInfoTextPane.setContentType("text/html");

        setPopupMenu();

    }

    private void setPopupMenu() {
        JPopupMenu storedInfoPopup = new JPopupMenu();

        JMenuItem deleteItem = new JMenuItem("Delete!");
        deleteItem.addActionListener(actionEvent -> {
            System.out.println("eliminando "+ storedSeriesComboBox.getSelectedItem());
            searchPresenter.deleteStoredInfo();
        });
        storedInfoPopup.add(deleteItem);

        JMenuItem saveItem = new JMenuItem("Save Changes!");
        saveItem.addActionListener(actionEvent -> {
            System.out.println("Guardando "+ storedSeriesComboBox.getSelectedItem());
            searchPresenter.saveStoredInfo();
        });
        storedInfoPopup.add(saveItem);

        storedInfoTextPane.setComponentPopupMenu(storedInfoPopup);

    }

    private void setComboBox() {
       storedSeriesComboBox.setModel(new DefaultComboBoxModel(DataBaseImp.getTitles().stream().sorted().toArray()));
    }



    public boolean existSavedTitle() {
        return storedSeriesComboBox.getSelectedItem() != null;
    }


    public String getSeletedSavedTitle() {
        return (String) storedSeriesComboBox.getSelectedItem();
    }


    public void showRating(int rating) {


    }


    public int getRatingInput() {
        return 0;
    }


    public void showRatedSeries(List<RatedSeries> ratedSeries) {
        DefaultListModel<RatedSeries> listModel = new DefaultListModel<>();
        for (RatedSeries series : ratedSeries) {
            listModel.addElement(series);
        }
        ratedPanel.add(new JList<>(listModel));
    }


    public void setSelectSavedComboBox(Object[] savedTitles) {
        storedSeriesComboBox.setModel(new DefaultComboBoxModel(savedTitles));
        storedSeriesComboBox.addActionListener(actionEvent -> {
            System.out.println("Seleccioando "+ storedSeriesComboBox.getSelectedItem());
        });
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
