import model.database.DataBase;
import utils.Serie;

import java.sql.SQLException;
import java.util.*;

public class TestDataBase implements DataBase {

    private final Map<String, String> data = new HashMap<>();
    private final Map<String, Integer> scores = new HashMap<>();

    @Override
    public void loadDatabase() {
        // No-op para pruebas
    }

    @Override
    public void saveInfo(String title, String text) throws SQLException {
        data.put(title, text);
    }

    @Override
    public void deleteEntry(String title) throws SQLException {
        data.remove(title);
        scores.remove(title);
    }

    @Override
    public String getExtract(String title) throws SQLException {
        return data.get(title);
    }

    @Override
    public  ArrayList<String> getTitles() throws SQLException {
        return new ArrayList<>(data.keySet());
    }

    @Override
    public void saveScore(String title, int score) throws SQLException {
        scores.put(title, score);
    }

    @Override
    public int getScore(String title) throws SQLException {
        return scores.getOrDefault(title, 0);
    }

    @Override
    public List<Serie> getScoredSeries() throws SQLException {
        List<Serie> series = new ArrayList<>();
        //TODO: Implementar
        return series;
    }
}
