import model.API.WikipediaPageAPI;
import model.API.WikipediaSearchAPI;
import model.SearchModel;
import model.Serie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stub.WikipediaPageAPIStub;
import stub.WikipediaSearchAPIStub;

import java.io.IOException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SearchModelTest {

    private SearchModel searchModel;

    @BeforeEach
    void setUp() {
        WikipediaSearchAPI searchAPI = new WikipediaSearchAPIStub();
        WikipediaPageAPI pageAPI = new WikipediaPageAPIStub();
        searchModel = new SearchModel(searchAPI, pageAPI);
    }

    @Test
    void testSearchByTitle() throws IOException {
        LinkedList<Serie> series = searchModel.searchSeries("Breaking Bad");

        assertNotNull(series, "Series list should not be null");
        assertFalse(series.isEmpty(), "Series list should not be empty");

        boolean found = series.stream().anyMatch(serie -> "Breaking Bad".equals(serie.getTitle()));

        assertTrue(found, "Breaking Bad should be found in the series list");
    }

    @Test
    void testSearchByTitleNotFound() throws IOException {
        LinkedList<Serie> series = searchModel.searchSeries("Killing Eve");

        boolean found = series.stream().anyMatch(serie -> "Killing Eve".equals(serie.getTitle()));

        assertFalse(found, "Killing Eve should not be found in the series list");
    }

    @Test
    void testSearchByTitleEmpty() throws IOException {
        LinkedList<Serie> series = searchModel.searchSeries(" ");

        boolean found = series.stream().anyMatch(serie -> " ".equals(serie.getTitle()));

        assertFalse(found, "Empty string should not be found in the series list");

    }
}