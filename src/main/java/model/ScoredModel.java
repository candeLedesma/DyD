package model;

import model.database.DataBase;
import model.database.DataBaseImp;
import presenter.Presenter;
import presenter.SeriesPresenter;
import utils.Serie;

import java.util.List;

public class ScoredModel {
    private DataBase database;


    public void setScore(String title, int score) {
        database.saveScore(title, score);
    }

    public boolean hasScore(String title) {
        return database.getScore(title) > 0;
    }

    public int getScore(String title) {
        return database.getScore(title);
    }

    public List<Serie> getScoredSeries() {
        return database.getScoredSeries();
    }

    public void setDatabase(DataBase database) {
        this.database = database;
    }
}