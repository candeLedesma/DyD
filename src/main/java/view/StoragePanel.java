package view;

import model.DataBaseImp;
import presenter.SearchPresenterImp;
import utils.SearchResult;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

import utils.TextoHTML;

public class StoragePanel extends JPanel {

    private JPanel storagePanel;

    private JList savedSeriesList;

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
        setComboBox();

        storedInfoTextPane.setContentType("text/html");
        storedInfoTextPane.setEditable(true);

        setPopupMenu();

    }

    void setPopupMenu() {
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

    void setComboBox() {
        storedSeriesComboBox.setModel(new DefaultComboBoxModel(DataBaseImp.getTitles().stream().sorted().toArray()));
        storedSeriesComboBox.addActionListener(actionEvent -> {
            System.out.println("Obteniendo "+ storedSeriesComboBox.getSelectedItem());
            searchPresenter.handleStoredInfo();
        });
    }


    public void setSelectSavedComboBox(Object[] savedTitles) {
        storedSeriesComboBox.setModel(new DefaultComboBoxModel(savedTitles));
        storedSeriesComboBox.addActionListener(actionEvent -> {
            System.out.println("Obteniendo "+ storedSeriesComboBox.getSelectedItem());
            searchPresenter.getStoredInfo();
        });
    }

    public boolean existSavedTitle() {
        return storedSeriesComboBox.getSelectedItem() != null;
    }

    public String getSeletedSavedTitle() {
        return (String) storedSeriesComboBox.getSelectedItem();
    }


    public void setStoredTextPane(String extract) {
        SwingUtilities.invokeLater(() -> {
            storedInfoTextPane.setText(TextoHTML.textToHtml(extract));
            //storedInfoTextPane.setCaretPosition(0);
        });
    }
}
