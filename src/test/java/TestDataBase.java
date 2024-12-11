import model.database.DataBase;
import utils.Serie;

import java.sql.SQLException;
import java.util.*;

public class TestDataBase implements DataBase {

    private final Map<String, String> data = new HashMap<>();
    private final List<Serie> scoredSeries = new ArrayList<>();

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
        scoredSeries.add(new Serie(title, score));
    }

    @Override
    public int getScore(String title) throws SQLException {
        return scoredSeries.stream()
                .filter(serie -> serie.getTitle().equals(title))
                .map(Serie::getScore)
                .findFirst()
                .orElse(0);
    }

    @Override
    public List<Serie> getScoredSeries() throws SQLException {
        return scoredSeries;
    }
}
