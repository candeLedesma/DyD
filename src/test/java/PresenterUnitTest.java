import org.junit.Before;
import org.junit.Test;
import presenter.PresenterImp;
import stub.MainViewStub;
import stub.SearchModelStub;
import utils.Serie;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class PresenterUnitTest {

    private PresenterImp presenter;
    private SearchModelStub modelStub;
    private MainViewStub viewStub;

    @Before
    public void setUp() {
        modelStub = new SearchModelStub();
        viewStub = new MainViewStub();

        presenter = new PresenterImp(modelStub);
        presenter.view = viewStub; // Inyecta el stub de la vista
    }

    @Test
    public void testSearchSeries() {
        LinkedList<Serie> expectedResults = new LinkedList<>();
        expectedResults.add(new Serie("Breaking Bad", "123", "A popular series about chemistry."));
        modelStub.searchResults = expectedResults;

        presenter.searchSeries();

        assertEquals("Breaking Bad", modelStub.lastSearchSeriesName);
        assertEquals(expectedResults, viewStub.shownResults);
    }

    @Test
    public void testGetSelectedExtract() {
        Serie serie = new Serie("Breaking Bad", "123", "A popular series about chemistry.");
        String expectedExtract = "Sample extract text.";
        modelStub.returnedExtract = expectedExtract;

        presenter.getSelectedExtract(serie);

        assertEquals(serie, modelStub.lastSearchedSerieForExtract);
        assertEquals(expectedExtract, viewStub.searchResultTextPane);
    }

    @Test
    public void testSaveStoredInfo() {
        viewStub.selectedSavedTitle = "Breaking Bad";
        viewStub.searchResultTextPane = "Extract about Breaking Bad.";

        presenter.saveStoredInfo();

        assertEquals("Breaking Bad", modelStub.savedTitle);
        assertEquals("Extract about Breaking Bad.", modelStub.savedText);
    }

    @Test
    public void testSaveLocally() {
        presenter.saveLocally();

        assertTrue(modelStub.saveLocallyCalled);
        assertNotNull(viewStub.savedTitles);
        assertEquals(2, viewStub.savedTitles.size());
        assertTrue(viewStub.savedTitles.contains("Breaking Bad"));
    }

    @Test
    public void testInitializeSavedPanel() {
        presenter.initializeSavedPanel();

        assertNotNull(viewStub.savedTitles);
        assertEquals(2, viewStub.savedTitles.size());
        assertTrue(viewStub.savedTitles.contains("Breaking Bad"));
    }
}
