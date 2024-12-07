package model;

import utils.Serie;
import presenter.SearchPresenter;

import java.util.LinkedList;
import java.util.List;

public interface SearchModel {
    LinkedList<Serie> searchSeries(String query);

    String searchPageExtract(Serie searchResult);

    void deleteSavedInfo(String title);

    Object[] getSavedTitles();

    void saveStoredInfo(String title, String text);

    void setPresenter(SearchPresenter presenter);

}
