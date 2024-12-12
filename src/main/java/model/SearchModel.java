package model;

import model.API.WikipediaPageAPI;
import model.API.WikipediaSearchAPI;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.util.LinkedList;

import static utils.TextoHTML.textToHtml;

public class SearchModel {
    private final WikipediaSearchAPI searchAPI;
    private final WikipediaPageAPI pageAPI;
    private final JsonParser jsonParser;

    public SearchModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/w/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        searchAPI = retrofit.create(WikipediaSearchAPI.class);
        pageAPI = retrofit.create(WikipediaPageAPI.class);
        jsonParser = new JsonParser();
    }

    public SearchModel(WikipediaSearchAPI searchAPI, WikipediaPageAPI pageAPI) {
        this.searchAPI = searchAPI;
        this.pageAPI = pageAPI;
        this.jsonParser = new JsonParser();
    }

    public LinkedList<Serie> searchSeries(String seriesName) throws IOException {
        Response<String> response = searchAPI.searchForTerm(seriesName + " (Tv series) articletopic:\"television\"").execute();
        return jsonParser.parseSearchResults(response.body());
    }

    public String searchPageExtract(Serie searchResult) throws IOException {
        Response<String> response = pageAPI.getExtractByPageID(searchResult.getPageID()).execute();
        String extract = jsonParser.parsePageExtract(response.body(), searchResult);
        searchResult.setExtract(textToHtml(extract));
        return extract;
    }
}