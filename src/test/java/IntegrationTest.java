import model.API.WikipediaPageAPI;
import model.API.WikipediaSearchAPI;
import model.SeriesModel;
import org.junit.Before;
import org.junit.Test;
import presenter.SeriesPresenter;
import stub.WikipediaPageAPIStub;
import stub.WikipediaSearchAPIStub;
import utils.Serie;
import view.*;

import javax.swing.*;

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
        SeriesModel seriesModel = new SeriesModel(searchAPI, pageAPI);

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
        Thread.sleep(2000);
        JMenuItem firstItem = (JMenuItem) searcherView.getResult();
        System.out.println(firstItem.getText());
        assertEquals(firstItem.getText(), "<html><font face=\"arial\">Breaking Bad: Snippet for Breaking Bad");
    }

    @Test
    public void testSearchWikiPage() throws InterruptedException {
        /*searcherView.setSearchTextField("Breaking Bad");
        searcherView.getSearchButton().doClick();
        Thread.sleep(2000);
        JMenuItem firstItem = (JMenuItem) searcherView.getResults().getComponent(0);
        firstItem.doClick();
        Thread.sleep(2000);
        System.out.println(searcherView.getSearchResultTextPane());
        assertEquals(searcherView.getSearchResultTextPane(),"<html>\n" +
                "  <head>\n" +
                "    \n" +
                "  </head>\n" +
                "  <body>\n" +
                "    EXAMPLE EXTRACT<a href=\"https://en.wikipedia.org/?curid=1\">https://en.wikipedia.org/?curid=1</a>\n" +
                "  </body>\n" +
                "</html>\n");*/
    }

    @Test
    public void testShowSavedSeries() throws InterruptedException {
        /*mainView.getTabbedPane().setSelectedIndex(1);
        Thread.sleep(2000);
        assertEquals(storedView.getStoredSeries().getItemAt(0).toString(), "title");*/
    }

    @Test
    public void testShowSavedExtract() throws InterruptedException {
        /*mainView.getTabbedPane().setSelectedIndex(1);
        Thread.sleep(2000);
        storedView.getStoredSeries().setSelectedIndex(0);
        Thread.sleep(2000);
        assertEquals( "<html>\n" + "  <head>\n" +  "    \n" + "  </head>\n" +"  <body>\n" +"    <font face=\"arial\">EXAMPLE EXTRACT</font>\n" +
                        "  </body>\n" +
                        "</html>\n",
                storedView.getSelectedSavedExtract());*/
    }

    @Test
    public void testShowScoredSeries() throws InterruptedException {
        /*mainView.getTabbedPane().setSelectedIndex(2);
        Thread.sleep(2000);
        assertEquals("title",scoredView.getScoresTable().getValueAt(0, 0));*/
    }
}