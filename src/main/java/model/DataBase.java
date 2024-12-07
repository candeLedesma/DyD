package model;

import utils.Serie;

import java.util.ArrayList;
import java.util.List;

public interface DataBase {


    void saveRating(String title, int rating);

    int getRating(String title);

    List<Serie> getAllRatedSeries();

    void saveInfo(String title, String snippet);

    ArrayList<String> getTitles();

    void deleteEntry(String title);

    String getExtract(String title);
}
