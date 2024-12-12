import model.ScoredModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Serie;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoredModelTest {

    private ScoredModel scoredModel;
    private TestDataBase testDataBase;

    @BeforeEach
    void setUp() {
        testDataBase = new TestDataBase();
        scoredModel = new ScoredModel();
        scoredModel.setDatabase(testDataBase);
    }

    @Test
    void testSetGetScore() throws SQLException {
        scoredModel.setScore("Breaking Bad", 5);
        assertEquals(5, testDataBase.getScore("Breaking Bad"));
    }

    @Test
    void testHasScore() throws SQLException {
        scoredModel.setScore("Breaking Bad", 5);
        assertTrue(scoredModel.hasScore("Breaking Bad"));
    }

    @Test
    void testGetScoredSeries() throws SQLException {
        testDataBase.saveScore("Breaking Bad", 5);
        testDataBase.saveScore("Game of thrones", 4);

        List<Serie> series = testDataBase.getScoredSeries();
        assertEquals(2, series.size());
        assertEquals(5, testDataBase.getScore("Breaking Bad"));
        assertEquals(4, testDataBase.getScore("Game of thrones"));
    }

}
