package presenter;

import model.SeriesModel;
import utils.Serie;
import view.MainView;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class SeriesPresenter implements Presenter {
    private final MainView view;
    private final SeriesModel model;

    private final ScoredPresenter scoredPresenter;
    private final StoredPresenter storedPresenter;
    private final SearchPresenter searchPresenter;

    public SeriesPresenter(SeriesModel model) {
        this.model = model;
        model.setPresenter(this);
        this.view = new MainView(this);
        this.scoredPresenter = new ScoredPresenter(view, model);
        this.storedPresenter = new StoredPresenter(view, model);
        this.searchPresenter = new SearchPresenter(view, model);
    }

    @Override
    public void start() {
        try {
            view.showView();
        } catch (Exception e) {
            view.showErrorMessage(e.getMessage());
        }
    }

    @Override
    public Serie getLastSearchedSeries() {
        return view.getLastSearchedSeries();
    }

    @Override
    public void initializeSavedPanel() {
        storedPresenter.initializeSavedPanel();
    }

    @Override
    public int getScore() {
        return view.getScore();
    }

    @Override
    public void getStoredInfo() {
        storedPresenter.getStoredInfo();
    }

    @Override
    public void deleteStoredInfo() {
        storedPresenter.deleteStoredInfo();
    }

    @Override
    public void saveStoredInfo() {
        storedPresenter.saveStoredInfo();
    }

    @Override
    public Object getLastUpdatedScore() {
        return model.getLastUpdatedScore();
    }

    @Override
    public void showResults(LinkedList<Serie> results) {
        searchPresenter.showResults(results);
    }

    @Override
    public List<Serie> getScoredSeries() {
        return model.getScoredSeries();
    }

    @Override
    public void searchSeries() {
        searchPresenter.searchSeries();
    }

    @Override
    public void saveLocally() {
        storedPresenter.saveLocally();

    }

    @Override
    public void getSelectedExtract(Serie searchResult) {
        searchPresenter.getSelectedExtract(searchResult);
    }


    @Override
    public String getScoreSerie(String title) {
        if (model.hasScore(view.getLastSearchedSeries().getTitle())) {
            return String.valueOf(model.getScore());
        }else{
            return "Not found";
        }
    }

    @Override
    public void recordScore() {
        scoredPresenter.recordScore();
    }
}
