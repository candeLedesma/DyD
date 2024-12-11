package presenter;

import utils.Serie;
import model.SeriesModel;
import view.MainView;

import java.sql.SQLException;
import java.util.Date;


public class ScoredPresenter {
    private final MainView view;
    private final SeriesModel model;

    public ScoredPresenter(MainView view, SeriesModel model) {
        this.view = view;
        this.model = model;
    }

    public void recordScore() throws SQLException {
        Serie lastSearchedSeries = view.getLastSearchedSeries();
        if (lastSearchedSeries != null) {
            int newScore = view.getScore();
            lastSearchedSeries.setScore(newScore);
            model.setScore();
            view.updateScore();
        }
    }

}
