package model.database;

import utils.Serie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface DataBase {

    void saveInfo(String title, String snippet);

    ArrayList<String> getTitles();

    void deleteEntry(String title);

    String getExtract(String title);

    void saveScore(String title, int score);

    int getScore(String title);

    void loadDatabase();

    List<Serie> getScoredSeries();
}
