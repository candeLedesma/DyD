package presenter;

import model.SeriesModel;
import utils.Serie;
import view.MainView;

import java.util.LinkedList;

public class SearchPresenter {
    private final MainView view;
    private final SeriesModel model;

    public SearchPresenter(MainView view, SeriesModel model) {
        this.view = view;
        this.model = model;
    }

    public void searchSeries() {
        new Thread(() -> {
            String seriesName = view.getSearchSerieField();
            LinkedList<Serie> results = model.searchSeries(seriesName);
            view.showResults(results);
        }).start();
    }

    public void getSelectedExtract(Serie selectedResult) {
        String extract = model.searchPageExtract(selectedResult);
        view.setSearchResultTextPane(extract);
    }

}
