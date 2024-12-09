package model;

import utils.Serie;
import presenter.Presenter;

import java.util.LinkedList;

public interface SearchModel {
    LinkedList<Serie> searchSeries(String query);

    String searchPageExtract(Serie searchResult);

    void deleteSavedInfo(String title);

    Object[] getSavedTitles();

    void saveStoredInfo(String title, String text);

    void setPresenter(Presenter presenter);

}
