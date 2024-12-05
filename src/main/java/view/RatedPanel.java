package view;

import utils.SearchResult;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

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
    public void showResults(LinkedList<SearchResult> results) {

    }


}
