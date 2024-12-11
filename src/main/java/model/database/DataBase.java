package model.database;

import utils.Serie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface DataBase {

    void saveInfo(String title, String snippet) throws SQLException;

    ArrayList<String> getTitles() throws SQLException;

    void deleteEntry(String title) throws SQLException;

    String getExtract(String title) throws SQLException;

    void saveScore(String title, int score) throws SQLException;

    int getScore(String title) throws SQLException;

    void loadDatabase();

    List<Serie> getScoredSeries() throws SQLException;

}
