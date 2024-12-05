package presenter;

import utils.SearchResult;

public interface SearchPresenter {

    void searchSeries();

    void getSelectedExtract(SearchResult searchResult);

    void deleteStoredInfo();

    void saveStoredInfo();


    void saveLocally();

    SearchResult getLastSearchedSeries();
}
