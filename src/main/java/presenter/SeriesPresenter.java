package presenter;

import model.SeriesModel;
import utils.Serie;
import view.MainView;

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
        view.showView();
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
    public boolean hasScore() {
        return model.hasScore(view.getLastSearchedSeries());
    }

    @Override
    public int getScoreSerie(String title) {
        return model.getScore();
    }

    @Override
    public void recordScore() {
        scoredPresenter.recordScore();
    }
}
