package model.database;

import utils.Serie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DataBaseImp implements DataBase {

  private final String DB_URL = "jdbc:sqlite:./dictionary.db";

  private interface ResultSetHandler<T> {
    T handle(ResultSet rs) throws SQLException;
  }

  private <T> T executeQuery(String query, ResultSetHandler<T> handler, Object... params) throws SQLException {
    try (Connection connection = DriverManager.getConnection(DB_URL);
         PreparedStatement statement = connection.prepareStatement(query)) {
      setParameters(statement, params);
      try (ResultSet rs = statement.executeQuery()) {
        return handler.handle(rs);
      }
    }
  }

  private void executeUpdate(String query, Object... params) throws SQLException {
    try (Connection connection = DriverManager.getConnection(DB_URL);
         PreparedStatement statement = connection.prepareStatement(query)) {
      setParameters(statement, params);
      statement.executeUpdate();
    }
  }

  private void setParameters(PreparedStatement statement, Object... params) throws SQLException {
    for (int i = 0; i < params.length; i++) {
      statement.setObject(i + 1, params[i]);
    }
  }

  public void testDB() throws SQLException {
    executeQuery("SELECT * FROM catalog", rs -> {
      while (rs.next()) {
        System.out.println("id = " + rs.getInt("id"));
        System.out.println("title = " + rs.getString("title"));
        System.out.println("extract = " + rs.getString("extract"));
        System.out.println("source = " + rs.getInt("source"));
      }
      return null;
    });
  }

  @Override
  public ArrayList<String> getTitles() throws SQLException {
    return executeQuery("SELECT title FROM catalog", rs -> {
      ArrayList<String> titles = new ArrayList<>();
      while (rs.next()) {
        titles.add(rs.getString("title"));
      }
      return titles;
    });
  }

  @Override
  public String getExtract(String title) throws SQLException {
    return executeQuery(
            "SELECT extract FROM catalog WHERE title = ?",
            rs -> rs.next() ? rs.getString("extract") : null,
            title
    );
  }

  @Override
  public void saveScore(String title, int score) throws SQLException {
    String sql = "INSERT OR REPLACE INTO scored (title, score, updated_at) VALUES (?, ?, datetime('now'))";
    try (Connection connection = DriverManager.getConnection(DB_URL);
         PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setString(1, title);
      pstmt.setInt(2, score);
      pstmt.executeUpdate();
    }
  }



  @Override
  public int getScore(String title) throws SQLException {
    String query = "SELECT score FROM scored WHERE title = ?";
    Integer score = executeQuery(
            query,
            rs -> rs.next() ? rs.getInt("score") : 0,
            title
    );
    return score;
  }


  @Override
  public void deleteEntry(String title) throws SQLException {
    executeUpdate("DELETE FROM catalog WHERE title = ?", title);
  }

  @Override
  public void saveInfo(String title, String extract) throws SQLException {
    executeUpdate(
            "REPLACE INTO catalog (id, title, extract, source) VALUES (NULL, ?, ?, 1)",
            title, extract
    );
  }

  public void loadDatabase() {
    try (Connection connection = DriverManager.getConnection(DB_URL);
         Statement statement = connection.createStatement()) {
      statement.setQueryTimeout(30);

      createCatalogTable(statement);
      createScoredTable(statement);
    } catch (SQLException e) {
      System.err.println("Error al cargar la base de datos: " + e.getMessage());
    }
  }

  @Override
  public List<Serie> getScoredSeries() throws SQLException {
    return executeQuery(
            "SELECT title, updated_at, score FROM scored ORDER BY score ASC",
            rs -> {
              List<Serie> series = new ArrayList<>();
              while (rs.next()) {
                Serie serie = new Serie(rs.getString("title"), rs.getInt("score"));
                serie.setUpdatedAt(rs.getDate("updated_at"));
                series.add(serie);
              }
              return series;
            }
    );
  }


  private static void createScoredTable(Statement statement) throws SQLException {
    String createTableSQL = "CREATE TABLE IF NOT EXISTS scored ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "title TEXT UNIQUE, "
            + "score INTEGER, "
            + "updated_at DATETIME DEFAULT CURRENT_TIMESTAMP)";
    statement.executeUpdate(createTableSQL);
  }




  private void createCatalogTable(Statement statement) throws SQLException {
    statement.executeUpdate(
            "CREATE TABLE IF NOT EXISTS catalog (id INTEGER, title STRING PRIMARY KEY, extract STRING, source INTEGER)"
    );
  }

}