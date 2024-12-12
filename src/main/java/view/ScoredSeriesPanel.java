package view;

import presenter.Presenter;
import presenter.SeriesPresenter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

public class ScoredSeriesPanel extends JPanel {
    private JPanel scoredPanel;
    private JTable scoredSeriesTable;
    private DefaultTableModel tableModel;
    private Presenter searchPresenter;

    public ScoredSeriesPanel() {
        inicomponents();
    }

    private void inicomponents() {
        this.add(scoredPanel);
        this.setVisible(true);
    }


    public void setUpView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initializeTableModel();
        scoredSeriesTable.setModel(tableModel);
    }

    private void initializeTableModel() {
        this.tableModel = new DefaultTableModel();
        tableModel.setColumnCount(0);
        tableModel.addColumn("Title");
        tableModel.addColumn("Score");
        tableModel.addColumn("Last Updated");
    }


    public void showView() {
        tableModel.setRowCount(0);
        searchPresenter.updateScoredSeriesTable();
        scoredSeriesTable.setVisible(true);
    }

    public void setPresenter(SeriesPresenter searchPresenter) {
        this.searchPresenter = searchPresenter;
    }

    public JTable getScoresTable() {
        return scoredSeriesTable;
    }

    public void updateTable() {
        showView();
        repaint();
    }

    public void addSerieToTable(String title, int score, Date lastUpdated) {
        tableModel.addRow(new Object[]{title, score, lastUpdated});
    }
}
