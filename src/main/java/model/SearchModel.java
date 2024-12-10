package model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import model.API.WikipediaPageAPI;
import model.API.WikipediaSearchAPI;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import utils.Serie;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

import static utils.TextoHTML.textToHtml;

public class SearchModel {
    private final WikipediaSearchAPI searchAPI;
    private final WikipediaPageAPI pageAPI;
    private final Gson gson;

    public SearchModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://en.wikipedia.org/w/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        searchAPI = retrofit.create(WikipediaSearchAPI.class);
        pageAPI = retrofit.create(WikipediaPageAPI.class);
        gson = new Gson();
    }

    public LinkedList<Serie> searchSeries(String seriesName) throws IOException {
        LinkedList<Serie> searchResults = new LinkedList<>();

        Response<String> response = searchAPI.searchForTerm(seriesName + " (Tv series) articletopic:\"television\"").execute();
        JsonObject query = gson.fromJson(response.body(), JsonObject.class)
                .get("query").getAsJsonObject();
        JsonArray jsonResults = query.get("search").getAsJsonArray();

        for (JsonElement element : jsonResults) {
            JsonObject result = element.getAsJsonObject();
            Serie serie = new Serie(
                    result.get("title").getAsString(),
                    result.get("pageid").getAsString(),
                    result.get("snippet").getAsString()
            );
            searchResults.add(serie);
        }

        return searchResults;
    }

    public String searchPageExtract(Serie searchResult) throws IOException {
        String extract = "";

        Response<String> response = pageAPI.getExtractByPageID(searchResult.pageID).execute();
        JsonObject pages = gson.fromJson(response.body(), JsonObject.class)
                .get("query").getAsJsonObject()
                .get("pages").getAsJsonObject();

        Map.Entry<String, JsonElement> firstPage = pages.entrySet().iterator().next();
        JsonObject page = firstPage.getValue().getAsJsonObject();
        JsonElement pageExtract = page.get("extract");

        if (pageExtract == null) {
            extract = "No Results";
        } else {
            extract = "<h1>" + searchResult.title + "</h1>";
            extract += pageExtract.getAsString().replace("\\n", "\n");
            extract = textToHtml(extract);
        }

        searchResult.setExtract(extract);
        return extract;
    }

}
