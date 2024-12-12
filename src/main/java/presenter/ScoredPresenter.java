package presenter;

import model.Serie;
import model.SeriesModel;
import view.MainView;

import java.sql.SQLException;
import java.util.List;


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

    public void updateScoredSeriesTable(List<Serie> scoredSeries) {
        for (Serie serie : scoredSeries) {
            view.addSerieToTable(serie.getTitle(), serie.getScore(), serie.getLastUpdated());
        }
    }
}
