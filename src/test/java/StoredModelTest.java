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
        testDataBase = new TestDataBase(); // Base de datos de prueba
        storedModel = new StoredModel();
        storedModel.setDatabase(testDataBase);
    }

    @Test
    void testSaveStoredInfo() throws SQLException {
        storedModel.saveStoredInfo("Title1", "Extract1");
        assertEquals("Extract1", testDataBase.getExtract("Title1"));
    }

    @Test
    void testDeleteSavedInfo() throws SQLException {
        storedModel.saveStoredInfo("Title1", "Extract1");
        storedModel.deleteSavedInfo("Title1");
        assertNull(testDataBase.getExtract("Title1"));
    }

    @Test
    void testGetSavedTitles() throws SQLException {
        storedModel.saveStoredInfo("Title1", "Extract1");
        storedModel.saveStoredInfo("Title2", "Extract2");
        Object[] titles = storedModel.getSavedTitles();
        assertArrayEquals(new String[]{"Title1", "Title2"}, titles);
    }

    @Test
    void testGetExtract() throws SQLException {
        storedModel.saveStoredInfo("Title1", "Extract1");
        assertEquals("Extract1", storedModel.getExtract("Title1"));
    }
}
