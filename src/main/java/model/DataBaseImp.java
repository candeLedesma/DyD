package model;

import utils.Serie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseImp implements DataBase {

  private static final String DB_URL = "jdbc:sqlite:./dictionary.db";

  public DataBaseImp() {}

  private interface ResultSetHandler<T> {
    T handle(ResultSet rs) throws SQLException;
  }


  private <T> T executeQuery(String query, ResultSetHandler<T> handler, Object... params) {
    try (Connection connection = DriverManager.getConnection(DB_URL);
         PreparedStatement statement = connection.prepareStatement(query)) {
      setParameters(statement, params);
      try (ResultSet rs = statement.executeQuery()) {
        return handler.handle(rs);
      }
    } catch (SQLException e) {
      System.err.println("Error en consulta: " + e.getMessage());
      return null;
    }
  }

  private boolean executeUpdate(String query, Object... params) {
    try (Connection connection = DriverManager.getConnection(DB_URL);
         PreparedStatement statement = connection.prepareStatement(query)) {
      setParameters(statement, params);
      statement.executeUpdate();
      return true;
    } catch (SQLException e) {
      System.err.println("Error en actualización: " + e.getMessage());
      return false;
    }
  }

  private void setParameters(PreparedStatement statement, Object... params) throws SQLException {
    for (int i = 0; i < params.length; i++) {
      statement.setObject(i + 1, params[i]);
    }
  }


  public void testDB() {
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
  public ArrayList<String> getTitles() {
    return executeQuery("SELECT title FROM catalog", rs -> {
      ArrayList<String> titles = new ArrayList<>();
      while (rs.next()) {
        titles.add(rs.getString("title"));
      }
      return titles;
    });
  }

  @Override
  public String getExtract(String title) {
    return executeQuery(
            "SELECT extract FROM catalog WHERE title = ?",
            rs -> rs.next() ? rs.getString("extract") : null,
            title
    );
  }

  @Override
  public void deleteEntry(String title) {
    executeUpdate("DELETE FROM catalog WHERE title = ?", title);
  }

  @Override
  public void saveInfo(String title, String extract) {
    executeUpdate(
            "REPLACE INTO catalog (id, title, extract, source) VALUES (NULL, ?, ?, 1)",
            title, extract
    );
  }

  @Override
  public void saveRating(String title, int rating) {
    //TODO
  }

  @Override
  public int getRating(String title) {
    return 0;
  }

  @Override
  public List<Serie> getAllRatedSeries() {
    return null;
  }

  public void setScore(Serie serie) {
    //TODO
  }

  public static void loadDatabase() {
    try (Connection connection = DriverManager.getConnection(DB_URL);
         Statement statement = connection.createStatement()) {
      statement.setQueryTimeout(30);
      statement.executeUpdate("CREATE TABLE IF NOT EXISTS catalog (id INTEGER, title STRING PRIMARY KEY, extract STRING, source INTEGER)");
    } catch (SQLException e) {
      System.err.println("Error al cargar la base de datos: " + e.getMessage());
    }
  }
}
