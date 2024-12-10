package view;

import presenter.Presenter;
import presenter.SeriesPresenter;
import utils.Serie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ScoredSeriesPanel extends JPanel {
    private JPanel scoredPanel;
    private JTable scoredSeriesTable;
    private DefaultTableModel tableModel;
    private Presenter searchPresenter;

    public ScoredSeriesPanel() {
        this.add(scoredPanel);
        this.setVisible(true);
    }

    public void setUpView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.tableModel = new DefaultTableModel();

        tableModel.setColumnCount(0);

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

    public void setPresenter(SeriesPresenter searchPresenter) {
        this.searchPresenter = searchPresenter;
    }

    public void atualizeScore(Serie serie) {
        tableModel.addRow(new Object[]{serie.getTitle(), serie.getScore()});
        repaint();
    }
}
