package stub;

import model.SearchModelImp;
import utils.Serie;

import java.util.LinkedList;
import java.util.List;

public class SearchModelStub extends SearchModelImp {

    public String lastSearchSeriesName;
    public Serie lastSearchedSerieForExtract;
    public String savedTitle;
    public String savedText;
    public boolean saveLocallyCalled = false;
    public LinkedList<Serie> searchResults = new LinkedList<>();
    public List<String> savedTitles = List.of("Breaking Bad", "Game of Thrones");
    public String returnedExtract = "Sample extract text.";

    @Override
    public LinkedList<Serie> searchSeries(String seriesName) {
        this.lastSearchSeriesName = seriesName;
        return searchResults;
    }

    @Override
    public String searchPageExtract(Serie serie) {
        this.lastSearchedSerieForExtract = serie;
        return returnedExtract;
    }

    @Override
    public void saveStoredInfo(String title, String text) {
        this.savedTitle = title;
        this.savedText = text;
    }

    @Override
    public void saveLocally() {
        saveLocallyCalled = true;
    }

    @Override
    public Object[] getSavedTitles() {
        return savedTitles.toArray();
    }
}
