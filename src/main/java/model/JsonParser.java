package model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import utils.Serie;

import java.util.LinkedList;
import java.util.Map;

public class JsonParser {
    private final Gson gson;

    public JsonParser() {
        this.gson = new Gson();
    }

    public LinkedList<Serie> parseSearchResults(String jsonResponse) {
        LinkedList<Serie> searchResults = new LinkedList<>();
        JsonObject query = gson.fromJson(jsonResponse, JsonObject.class)
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

    public String parsePageExtract(String jsonResponse, Serie searchResult) {
        JsonObject pages = gson.fromJson(jsonResponse, JsonObject.class)
                .get("query").getAsJsonObject()
                .get("pages").getAsJsonObject();

        Map.Entry<String, JsonElement> firstPage = pages.entrySet().iterator().next();
        JsonObject page = firstPage.getValue().getAsJsonObject();
        JsonElement pageExtract = page.get("extract");

        if (pageExtract == null) {
            return "No Results";
        } else {
            String extract = "<h1>" + searchResult.title + "</h1>";
            extract += pageExtract.getAsString().replace("\\n", "\n");
            return extract;
        }
    }
}