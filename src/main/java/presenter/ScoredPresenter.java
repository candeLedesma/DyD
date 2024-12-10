package presenter;

import utils.Serie;
import model.SearchModelImp;
import view.MainView;


public class ScoredPresenter {
    private final MainView view;
    private final SearchModelImp model;

    public ScoredPresenter(MainView view, SearchModelImp model) {
        this.view = view;
        this.model = model;
    }

    public void recordScore() {
        Serie lastSearchedSeries = view.getLastSearchedSeries();
        if (lastSearchedSeries != null) {
            int newScore = view.getScore();
            lastSearchedSeries.setScore(newScore);
            model.setScore();
            view.atualizeScore(lastSearchedSeries);
        }
    }

}
