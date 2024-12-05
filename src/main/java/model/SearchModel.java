package model;

import fulllogic.SearchResult;
import presenter.SearchPresenter;

import java.util.LinkedList;
import java.util.List;

public interface SearchModel {
    LinkedList<SearchResult> searchSeries(String query);

    String searchPageExtract(SearchResult searchResult);

    void deleteSavedInfo(String title);

    Object[] getSavedTitles();

    void saveStoredInfo(String title, String text);

    void setPresenter(SearchPresenter presenter);

    //for new functionality
    void saveRating(String title, int rating);

    int getRating(String title);

    List<RatedSeries> getAllRatedSeries();
}
