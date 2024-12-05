package presenter;

import utils.Serie;

public interface SearchPresenter {

    void searchSeries();

    void getSelectedExtract(Serie searchResult);

    void deleteStoredInfo();

    void saveStoredInfo();


    void saveLocally();

    Serie getLastSearchedSeries();
}
