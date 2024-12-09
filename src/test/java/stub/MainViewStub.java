package stub;

import utils.Serie;
import view.MainView;

import java.util.LinkedList;
import java.util.List;

public class MainViewStub extends MainView {

    public String searchSerieField = "Breaking Bad";
    public LinkedList<Serie> shownResults;
    public String selectedSavedTitle = "Breaking Bad";
    public String searchResultTextPane;
    public Serie lastSearchedSeries = new Serie("Breaking Bad", "123", "A popular series about chemistry.");
    public int score = 8;
    public List<String> savedTitles;

    public MainViewStub() {
        super(null); // No necesitamos pasar un presenter real
    }

    @Override
    public String getSearchSerieField() {
        return searchSerieField;
    }

    @Override
    public void showResults(LinkedList<Serie> results) {
        shownResults = results;
    }

    @Override
    public String getSeletedSavedTitle() {
        return selectedSavedTitle;
    }

    @Override
    public void setSearchResultTextPane(String text) {
        searchResultTextPane = text;
    }

    @Override
    public Serie getLastSearchedSeries() {
        return lastSearchedSeries;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setSelectSavedComboBox(Object[] titles) {
        savedTitles = List.of((String[]) titles);
    }
}
