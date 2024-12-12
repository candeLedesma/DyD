package stub;

import model.database.DataBase;
import model.Serie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseStub implements DataBase {

    private final Map<String, String> seriesExtracts;
    private final Map<String, Integer> seriesScores;

    public DataBaseStub() {
        seriesExtracts = new HashMap<>();
        seriesScores = new HashMap<>();
        seriesExtracts.put("Breaking Bad", "Example for Breaking Bad");
        seriesScores.put("Breaking Bad", 10);
    }

    @Override
    public void saveInfo(String title, String snippet) throws SQLException {
        seriesExtracts.put(title, snippet);
    }

    @Override
    public ArrayList<String> getTitles() throws SQLException {
        return new ArrayList<>(seriesExtracts.keySet());
    }

    @Override
    public void deleteEntry(String title) throws SQLException {
        seriesExtracts.remove(title);
        seriesScores.remove(title);
    }

    @Override
    public String getExtract(String title) throws SQLException {
        return seriesExtracts.getOrDefault(title, "");
    }

    @Override
    public void saveScore(String title, int score) throws SQLException {
        seriesScores.put(title, score);
    }

    @Override
    public int getScore(String title) throws SQLException {
        return seriesScores.getOrDefault(title, 0);
    }

    @Override
    public void loadDatabase() {
        // No-op for the stub implementation
    }

    @Override
    public List<Serie> getScoredSeries() throws SQLException {
        List<Serie> scoredSeries = new ArrayList<>();
        for (String title : seriesScores.keySet()) {
            int score = seriesScores.get(title);
            scoredSeries.add(new Serie(title, score));
        }
        return scoredSeries;
    }
}