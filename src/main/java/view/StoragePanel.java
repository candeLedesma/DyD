package view;
import presenter.SeriesPresenter;

import javax.swing.*;

import utils.TextoHTML;

import java.awt.event.ActionListener;

public class StoragePanel extends JPanel {

    private JPanel storagePanel;

    private SeriesPresenter searchPresenter;

    private JComboBox storedSeriesComboBox;

    private JTextPane storedInfoTextPane;


    public StoragePanel() {
        initComponents();
    }

    private void initComponents() {
        this.setVisible(true);

        this.add(storagePanel);
    }


    public void setPresenter(SeriesPresenter searchPresenter) {
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
        addMenuItem(storedInfoPopup, "Delete!", actionEvent -> searchPresenter.deleteStoredInfo());
        addMenuItem(storedInfoPopup, "Save Changes!", actionEvent -> searchPresenter.saveStoredInfo());
        storedInfoTextPane.setComponentPopupMenu(storedInfoPopup);
    }

    private void addMenuItem(JPopupMenu menu, String title, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.addActionListener(actionListener);
        menu.add(menuItem);
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

    public String getStoredTextPane() {
        return storedInfoTextPane.getText();
    }

    public JComboBox<Object> getStoredSeries() {
        return storedSeriesComboBox;
    }

    public void showSuccessSavedMessage() {
        JOptionPane.showMessageDialog(this, "The series was correctly saved!");
    }

    public void showSuccessDeletedMessage() {
        JOptionPane.showMessageDialog(this, "The series was correctly deleted!");
    }
}
