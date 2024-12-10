import model.ScoredModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Serie;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoredModelTest {

    private ScoredModel scoredModel;
    private TestDataBase testDataBase;

    @BeforeEach
    void setUp() {
        testDataBase = new TestDataBase(); // Base de datos de prueba
        scoredModel = new ScoredModel();
        scoredModel.setDatabase(testDataBase);
    }

    @Test
    void testSetScore() throws SQLException {
        scoredModel.setScore("Title1", 5);
        assertEquals(5, testDataBase.getScore("Title1"));
    }

    @Test
    void testHasScore() throws SQLException {
        scoredModel.setScore("Title1", 5);
        assertTrue(scoredModel.hasScore("Title1"));
    }

    @Test
    void testGetScore() throws SQLException {
        scoredModel.setScore("Title1", 5);
        assertEquals(5, scoredModel.getScore("Title1"));
    }

    @Test
    void testGetScoredSeries() throws SQLException {
        Serie serie1 = new Serie("Title1", 2);
        Serie serie2 = new Serie("Title2", 3);
        testDataBase.saveScore("Title1", 5);
        testDataBase.saveScore("Title2", 4);
        List<Serie> series = scoredModel.getScoredSeries();
        assertEquals(2, series.size());
    }
}
