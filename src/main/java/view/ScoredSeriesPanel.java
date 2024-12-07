package view;

import utils.Serie;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ScoredSeriesPanel extends JPanel implements SearchView {
    private JPanel scoredPanel;
    private JList scoredSeriesList;

    public ScoredSeriesPanel() {
        this.setVisible(true);

        this.add(scoredPanel);
    }

    @Override
    public void showView() {
        scoredPanel.add(new JScrollPane(scoredSeriesList), BorderLayout.CENTER);
    }

    @Override
    public void showResults(LinkedList<Serie> results) {

    }


}
