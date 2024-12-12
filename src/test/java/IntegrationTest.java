import model.API.WikipediaPageAPI;
import model.API.WikipediaSearchAPI;
import model.SeriesModel;
import model.database.DataBase;
import org.junit.Before;
import org.junit.Test;
import presenter.SeriesPresenter;
import stub.DataBaseStub;
import stub.WikipediaPageAPIStub;
import stub.WikipediaSearchAPIStub;
import view.SerieMenuItem;
import view.*;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    private SeriesPresenter presenter;

    private View mainView;

    private SearchPanel searcherView;

    private StoragePanel storedView;

    private ScoredSeriesPanel scoredView;

    @Before
    public void setUp() {
        WikipediaSearchAPI searchAPI = new WikipediaSearchAPIStub();
        WikipediaPageAPI pageAPI = new WikipediaPageAPIStub();
        DataBase dataBaseStub = new DataBaseStub();
        SeriesModel seriesModel = new SeriesModel(searchAPI, pageAPI, dataBaseStub);

        presenter = new SeriesPresenter(seriesModel);
        presenter.start();

        mainView = presenter.getView();
        searcherView = mainView.getSearchView();
        storedView = mainView.getStoredView();
        scoredView = mainView.getScoredView();
    }

    @Test
    public void testSearchSeries() throws InterruptedException {
        searcherView.setSearchResultTextPane("Breaking Bad");
        searcherView.getSearchButton().doClick();
        Thread.sleep(1000);
        SerieMenuItem firstItem = (SerieMenuItem) searcherView.getResult(); // Cambiar a SerieMenuItem
        System.out.println(firstItem.getText());
        assertEquals(firstItem.getText(), "<html><font face=\"arial\">Breaking Bad: Snippet for Breaking Bad");
    }

    @Test
    public void testSearchWikiPage() throws InterruptedException {
        searcherView.setSearchResultTextPane("Breaking Bad");
        searcherView.getSearchButton().doClick();
        Thread.sleep(1000);
        SerieMenuItem firstItem = (SerieMenuItem) searcherView.getResult(); // Cambiar a SerieMenuItem
        firstItem.doClick();
        Thread.sleep(1000);
        System.out.println(searcherView.getSearchResultTextPane());
        assertEquals(searcherView.getSearchResultTextPane(), "<html>\n" +
                "  <head>\n" +
                "    \n" +
                "  </head>\n" +
                "  <body>\n" +
                "    Breaking Bad\n" +
                "  </body>\n" +
                "</html>\n");
    }

    @Test
    public void testShowSavedSeries() throws InterruptedException {
        mainView.getTabbedPane().setSelectedIndex(1);
        Thread.sleep(1000);
        assertEquals(storedView.getStoredSeries().getItemAt(0).toString(), "Breaking Bad");
    }

    @Test
    public void testShowSavedExtract() throws InterruptedException {
        mainView.getTabbedPane().setSelectedIndex(1);
        Thread.sleep(1000);
        storedView.getStoredSeries().setSelectedIndex(0);
        Thread.sleep(1000);

        assertEquals("Breaking Bad", storedView.getSeletedSavedTitle());
    }

    @Test
    public void testShowScoredSeries() throws InterruptedException {
        mainView.getTabbedPane().setSelectedIndex(2);
        Thread.sleep(1000);
        assertEquals("Breaking Bad", scoredView.getScoresTable().getValueAt(0, 0));
    }
}
