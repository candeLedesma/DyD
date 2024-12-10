package model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import model.database.DataBase;
import model.database.DataBaseImp;
import utils.Serie;
import model.API.WikipediaPageAPI;
import model.API.WikipediaSearchAPI;
import presenter.Presenter;
import presenter.SeriesPresenter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.util.*;

import static utils.TextoHTML.textToHtml;

public class SearchModelImp implements SearchModel {

    private SeriesPresenter searchPresenter;

    private WikipediaSearchAPI searchAPI;

    private WikipediaPageAPI pageAPI;

    private Gson gson;

    private LinkedList<Serie> searchResultsArray;

    private DataBase database;

    public SearchModelImp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/w/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        searchAPI = retrofit.create(WikipediaSearchAPI.class);
        pageAPI = retrofit.create(WikipediaPageAPI.class);
        gson = new Gson();
        database = new DataBaseImp();
        database.loadDatabase();
    }


    @Override
    public LinkedList<Serie> searchSeries(String seriesName) {

        Response<String> callForSearchResponse;

        searchResultsArray = new LinkedList<>();
        try {
            callForSearchResponse = searchAPI.searchForTerm(seriesName + " (Tv series) articletopic:\"television\"").execute();


            JsonObject jobj = gson.fromJson(callForSearchResponse.body(), JsonObject.class);
            JsonObject query = jobj.get("query").getAsJsonObject();
            JsonArray jsonResults = query.get("search").getAsJsonArray();


            for (JsonElement je : jsonResults) {
                JsonObject searchResult = je.getAsJsonObject();
                String searchResultTitle = searchResult.get("title").getAsString();
                String searchResultPageId = searchResult.get("pageid").getAsString();
                String searchResultSnippet = searchResult.get("snippet").getAsString();

                Serie serie = new Serie(searchResultTitle, searchResultPageId, searchResultSnippet);
                searchResultsArray.add(serie);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return searchResultsArray;
    }


    @Override
    public String searchPageExtract(Serie searchResult) {

        Response<String> callForPageResponse;

        String extract = "";

        try {
            callForPageResponse = pageAPI.getExtractByPageID(searchResult.pageID).execute();

            JsonObject jobj2 = gson.fromJson(callForPageResponse.body(), JsonObject.class);
            JsonObject query2 = jobj2.get("query").getAsJsonObject();
            JsonObject pages = query2.get("pages").getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> pagesSet = pages.entrySet();
            Map.Entry<String, JsonElement> first = pagesSet.iterator().next();
            JsonObject page = first.getValue().getAsJsonObject();
            JsonElement searchResultExtract2 = page.get("extract");
            if (searchResultExtract2 == null) {
                extract = "No Results";
            } else {
                extract = "<h1>" + searchResult.title + "</h1>";
                extract+= searchResultExtract2.getAsString().replace("\\n", "\n");
                extract = textToHtml(extract);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("set extract");
        searchResult.setExtract(extract);
        return extract;
    }

    @Override
    public void deleteSavedInfo(String title) {
        database.deleteEntry(title);
    }

    @Override
    public Object[] getSavedTitles() {
        return database.getTitles().stream().sorted().toArray();
    }

    @Override
    public void saveStoredInfo(String title, String text) {
        database.saveInfo(title, text);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.searchPresenter = (SeriesPresenter) presenter;
    }

    public void saveLocally() {
        database.saveInfo(searchPresenter.getLastSearchedSeries().getTitle(), searchPresenter.getLastSearchedSeries().getExtract());
    }

    public void setScore() {
        Serie serie = searchPresenter.getLastSearchedSeries();
        int score = searchPresenter.getScore();
        database.saveScore(serie.getTitle(),score);
    }

    public String getExtract(String title) {
        return database.getExtract(title);
    }

    public boolean hasScore(Serie lastSearchedSeries) {
        int score = database.getScore(lastSearchedSeries.getTitle());
        return score > 0;
    }


    public int getScore() {
        return database.getScore(searchPresenter.getLastSearchedSeries().getTitle());
    }

    public List<Serie> getScoredSeries() {
        return database.getScoredSeries();
    }
}
