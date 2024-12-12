package view;
import presenter.MainPresenter;

import javax.swing.*;

import utils.HtmlTextFormatter;

import java.awt.event.ActionListener;

public class StoragePanel extends JPanel implements View {

    private JPanel storagePanel;

    private MainPresenter presenter;

    private JComboBox storedSeriesComboBox;

    private JTextPane storedInfoTextPane;


    public StoragePanel() {
        initComponents();
    }

    private void initComponents() {
        this.setVisible(true);

        this.add(storagePanel);
    }


    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showView() {
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
        addMenuItem(storedInfoPopup, "Delete!", actionEvent -> presenter.deleteStoredInfo());
        addMenuItem(storedInfoPopup, "Save Changes!", actionEvent -> presenter.saveStoredInfo());
        storedInfoTextPane.setComponentPopupMenu(storedInfoPopup);
    }

    private void addMenuItem(JPopupMenu menu, String title, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.addActionListener(actionListener);
        menu.add(menuItem);
    }

    void setUpComboBox() {
        presenter.initializeSavedPanel();
    }


    public void setSelectSavedComboBox(Object[] savedTitles) {
        storedSeriesComboBox.setModel(new DefaultComboBoxModel(savedTitles));

        storedSeriesComboBox.addActionListener(actionEvent -> {
            presenter.getStoredInfo();
        });
    }

    public boolean existSavedTitle() {
        return storedSeriesComboBox.getSelectedIndex() > -1;
    }

    public String getSeletedSavedTitle() {
        return storedSeriesComboBox.getSelectedItem().toString();
    }


    public void setStoredTextPane(String extract) {
        storedInfoTextPane.setText(HtmlTextFormatter.textToHtml(extract));
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
