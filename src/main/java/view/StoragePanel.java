package view;
import presenter.SearchPresenterImp;

import javax.swing.*;

import utils.TextoHTML;

public class StoragePanel extends JPanel {

    private JPanel storagePanel;

    private SearchPresenterImp searchPresenter;

    private JComboBox storedSeriesComboBox;

    private JTextPane storedInfoTextPane;


    public StoragePanel() {
        this.setVisible(true);

        this.add(storagePanel);
    }


    public void setPresenter(SearchPresenterImp searchPresenter) {
        this.searchPresenter = searchPresenter;
    }

    public void setSavedPanel() {
        setUpComboBox();

        setUpStoredInfoTextPane();

        setUpPopupMenu();

    }

    private void setUpStoredInfoTextPane() {
        storedInfoTextPane.setContentType("text/html");
        storedInfoTextPane.setEditable(true);
    }

    void setUpPopupMenu() {
        JPopupMenu storedInfoPopup = new JPopupMenu();

        JMenuItem deleteItem = new JMenuItem("Delete!");

        deleteItem.addActionListener(actionEvent -> {
            searchPresenter.deleteStoredInfo();
        });
        storedInfoPopup.add(deleteItem);

        JMenuItem saveItem = new JMenuItem("Save Changes!");
        saveItem.addActionListener(actionEvent -> {
            searchPresenter.saveStoredInfo();
        });
        storedInfoPopup.add(saveItem);

        storedInfoTextPane.setComponentPopupMenu(storedInfoPopup);
    }

    void setUpComboBox() {
        searchPresenter.initializeSavedPanel();
    }


    public void setSelectSavedComboBox(Object[] savedTitles) {
        storedSeriesComboBox.setModel(new DefaultComboBoxModel(savedTitles));

        storedSeriesComboBox.addActionListener(actionEvent -> {
            searchPresenter.getStoredInfo();
        });
    }

    public boolean existSavedTitle() {
        return storedSeriesComboBox.getSelectedIndex() > -1;
    }

    public String getSeletedSavedTitle() {
        return storedSeriesComboBox.getSelectedItem().toString();
    }


    public void setStoredTextPane(String extract) {
        storedInfoTextPane.setText(TextoHTML.textToHtml(extract));
    }

    public void deleteSelectedIndex() {
        storedSeriesComboBox.removeItemAt(storedSeriesComboBox.getSelectedIndex());
        storedInfoTextPane.setText("");
    }
}
