package view;

import utils.SearchResult;

import java.util.LinkedList;

public interface SearchView {

    void showView();

    void showResults(LinkedList<SearchResult> results);


}
