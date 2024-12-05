package view;

import utils.Serie;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class RatedPanel extends JPanel implements SearchView {
    private JPanel ratedPanel;
    private JList ratedSeriesList;
    private JTabbedPane textPaneRatedSeries;

    public RatedPanel() {
        this.setVisible(true);

        this.add(ratedPanel);
    }

    @Override
    public void showView() {

    }

    void setRatedSeriesPanel() {
        ratedPanel.add(new JScrollPane(ratedSeriesList), BorderLayout.CENTER);
        textPaneRatedSeries.addTab("Rated Series", ratedPanel);
    }

    @Override
    public void showResults(LinkedList<Serie> results) {

    }

    public void showRatedSeries(List<Serie> ratedSeries) {
        DefaultListModel<Serie> listModel = new DefaultListModel<>();
        for (Serie series : ratedSeries) {
            listModel.addElement(series);
        }
        ratedPanel.add(new JList<>(listModel));
    }


}
