import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.Serie;

public class SerieUnitTest {

    private Serie serieWithSnippet;
    private Serie serieWithScore;

    @Before
    public void setUp() {
        serieWithSnippet = new Serie("Breaking Bad", "12345", "A popular TV series about chemistry.");
        serieWithScore = new Serie("Game of Thrones", 10);
    }

    @Test
    public void testConstructorWithSnippet() {
        assertEquals("Breaking Bad", serieWithSnippet.getTitle());
        assertEquals("12345", serieWithSnippet.getPageID());
        assertEquals("A popular TV series about chemistry.", serieWithSnippet.getSnippet());
    }

    @Test
    public void testConstructorWithScore() {
        assertEquals("Game of Thrones", serieWithScore.getTitle());
        assertEquals(10, serieWithScore.getScore());
        assertTrue(serieWithScore.hasScore);
    }

    @Test
    public void testSetAndGetScore() {
        serieWithSnippet.setScore(8);
        assertEquals(8, serieWithSnippet.getScore());
        assertTrue(serieWithSnippet.hasScore);
    }

    @Test
    public void testSetAndGetExtract() {
        String extractText = "A detailed summary of the series.";
        serieWithSnippet.setExtract(extractText);
        assertEquals(extractText, serieWithSnippet.getExtract());
    }

    @Test
    public void testSetTextToDisplay() {
        String displayedText = serieWithSnippet.getText();
        assertFalse(displayedText.contains("<span"));
        assertFalse(displayedText.contains("</span>"));
        assertTrue(displayedText.contains("Breaking Bad"));
    }
}
