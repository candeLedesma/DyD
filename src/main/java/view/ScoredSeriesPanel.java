package view;

import presenter.Presenter;
import utils.Serie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ScoredSeriesPanel extends JPanel {
    private JPanel scoredPanel;
    private JTable scoredSeriesTable;
    private DefaultTableModel tableModel; // Modelo de la tabla
    private Presenter searchPresenter;

    public ScoredSeriesPanel() {
        this.add(scoredPanel);
        this.setVisible(true);
    }

    public void setUpView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.tableModel = new DefaultTableModel();

        tableModel.addColumn("Title");
        tableModel.addColumn("Score");

        scoredSeriesTable.setModel(tableModel);

    }

    public void showView() {

        List<Serie> scoredSeries = searchPresenter.getScoredSeries();
        for (Serie serie : scoredSeries) {
            tableModel.addRow(new Object[]{serie.getTitle(), serie.getScore()});
        }

        scoredSeriesTable.setVisible(true);
    }

    public void setPresenter(Presenter searchPresenter) {
        this.searchPresenter = searchPresenter;
    }

    public void atualizeScore() {
        showView();
    }
}
