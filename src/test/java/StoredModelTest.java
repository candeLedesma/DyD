import model.StoredModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class StoredModelTest {

    private StoredModel storedModel;
    private TestDataBase testDataBase;

    @BeforeEach
    void setUp() {
        testDataBase = new TestDataBase();
        storedModel = new StoredModel();
        storedModel.setDatabase(testDataBase);
    }

    @Test
    void testSaveLocallyInfo() throws SQLException {
        storedModel.saveLocally("Breaking Bad", "Un pelado cocina metanfetaminas con su ex alumno");
        assertTrue(testDataBase.getTitles().contains("Breaking Bad"));
    }

    @Test
    void testSavedInfo() throws SQLException {
        storedModel.saveStoredInfo("Breaking Bad", "Un pelado");
        String extract = testDataBase.getExtract("Breaking Bad");
        assertEquals("Un pelado", extract);
    }

    @Test
    void testDeletedInfo() throws SQLException {
        storedModel.deleteSavedInfo("Breaking Bad");
        assertNull(testDataBase.getExtract("Breaking Bad"));
    }


    @Test
    void testGetSavedTitles() throws SQLException {
        storedModel.saveStoredInfo("Braking Bad", "Un pelado cocina metanfetaminas con su ex alumno");
        storedModel.saveStoredInfo("Game of thrones", "Jon Snow sabe algo");
        Object[] titles = storedModel.getSavedTitles();
        assertArrayEquals(new String[]{"Braking Bad", "Game of thrones"}, titles);
    }

    @Test
    void testGetExtract() throws SQLException {
        storedModel.saveStoredInfo("Braking Bad", "Un pelado cocina metanfetaminas con su ex alumno");
        assertEquals("Un pelado cocina metanfetaminas con su ex alumno", storedModel.getExtract("Braking Bad"));
    }
}
