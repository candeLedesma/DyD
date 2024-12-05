package view;

import fulllogic.SearchResult;
import model.RatedSeries;

import java.util.LinkedList;
import java.util.List;

public interface SearchView {

    void showView();

    void showResults(LinkedList<SearchResult> results);

    void setSearchResultTextPane(String text);

    void setSelectSavedComboBox(Object[] savedTitles);

    String getSeriesName();

    boolean existSavedTitle();

    String getSeletedSavedTitle();

    //for new functionality
    void showRating(int rating);

    int getRatingInput();

    void showRatedSeries(List<RatedSeries> ratedSeries);
}
