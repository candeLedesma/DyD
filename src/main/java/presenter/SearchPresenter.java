package presenter;

import utils.SearchResult;

public interface SearchPresenter {

    void searchSeries();

    void getSelectedExtract(SearchResult searchResult);

    void deleteStoredInfo();

    void saveStoredInfo();

    //for new functionality
    void saveRating();

    void loadRating();

    void showAllRatedSeries();

    void saveLocally();

    SearchResult getLastSearchedSeries();
}
