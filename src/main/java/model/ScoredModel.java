package model;

import model.database.DataBase;
import utils.Serie;

import java.sql.SQLException;
import java.util.List;

public class ScoredModel {
    private DataBase database;


    public void setScore(String title, int score) throws SQLException {
        database.saveScore(title, score);
    }

    public boolean hasScore(String title) throws SQLException {
        return database.getScore(title) > 0;
    }

    public int getScore(String title) throws SQLException {
        return database.getScore(title);
    }

    public List<Serie> getScoredSeries() throws SQLException {
        return database.getScoredSeries();
    }

    public void setDatabase(DataBase database) {
        this.database = database;
    }

}
