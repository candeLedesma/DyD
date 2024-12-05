package presenter;

import fulllogic.SearchResult;

public interface SearchPresenter {

    void searchSeries();

    void getSelectedExtract(SearchResult searchResult);

    void deleteStoredInfo();

    void saveStoredInfo();

    //for new functionality
    void saveRating();

    void loadRating();

    void showAllRatedSeries();

}
