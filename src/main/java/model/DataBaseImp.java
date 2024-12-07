package model;

import utils.Serie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseImp implements DataBase{

  private Connection connection;

  public DataBaseImp(){}

  private void setConection() {
    String url = "jdbc:sqlite:./dictionary.db"; // Define la URL de la base de datos
    try {
      connection = DriverManager.getConnection(url);
      System.out.println("Conexión establecida correctamente.");
    } catch (SQLException e) {
      System.err.println("Error al establecer conexión: " + e.getMessage());
    }
  }


  private void closeConection() {
    try {
      if (connection != null && !connection.isClosed()) {
        connection.close();
        System.out.println("Conexión cerrada correctamente.");
      }
    } catch (SQLException e) {
      System.err.println("Error al cerrar conexión: " + e.getMessage());
    }
  }


  public static void loadDatabase() {
    String url = "jdbc:sqlite:./dictionary.db";
    try (Connection connection = DriverManager.getConnection(url)) {
      if (connection != null) {
        DatabaseMetaData meta = connection.getMetaData();
        System.out.println("The driver name is " + meta.getDriverName());
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS catalog (id INTEGER, title STRING PRIMARY KEY, extract STRING, source INTEGER)");
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }


  public void testDB() {
    try {
      setConection();
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);

      ResultSet rs = statement.executeQuery("SELECT * FROM catalog");
      while (rs.next()) {
        System.out.println("id = " + rs.getInt("id"));
        System.out.println("title = " + rs.getString("title"));
        System.out.println("extract = " + rs.getString("extract"));
        System.out.println("source = " + rs.getInt("source"));
      }
    } catch (SQLException e) {
      System.err.println("Error en testDB: " + e.getMessage());
    } finally {
      closeConection();
    }
  }


  public ArrayList<String> getTitles() {
    ArrayList<String> titles = new ArrayList<>();
    try {
      setConection();
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);

      ResultSet rs = statement.executeQuery("SELECT * FROM catalog");
      while (rs.next()) {
        titles.add(rs.getString("title"));
      }
    } catch (SQLException e) {
      System.err.println("Error al obtener títulos: " + e.getMessage());
    } finally {
      closeConection();
    }
    return titles;
  }


  public String getExtract(String title) {
    try {
      setConection();
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);

      ResultSet rs = statement.executeQuery("SELECT * FROM catalog WHERE title = '" + title + "'");
      if (rs.next()) {
        return rs.getString("extract");
      }
    } catch (SQLException e) {
      System.err.println("Error al obtener extracto: " + e.getMessage());
    } finally {
      closeConection();
    }
    return null;
  }


  public void deleteEntry(String title) {
    try {
      setConection();
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);

      statement.executeUpdate("DELETE FROM catalog WHERE title = '" + title + "'");
    } catch (SQLException e) {
      System.err.println("Error al eliminar entrada: " + e.getMessage());
    } finally {
      closeConection();
    }
  }

  public void saveInfo(String title, String extract) {
    try {
      setConection();
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);

      statement.executeUpdate("REPLACE INTO catalog VALUES(null, '" + title + "', '" + extract + "', 1)");
    } catch (SQLException e) {
      System.err.println("Error al guardar información: " + e.getMessage());
    } finally {
      closeConection();
    }
  }


  public void saveRating(String title, int rating) {
    //TODO
  }

  public int getRating(String title) {
    //TODO
    return 0;
  }

  public List<Serie> getAllRatedSeries() {
    return null;
  }


  public void setScore(Serie serie) {
    // TODO
  }
}
