package model;

import model.database.DataBase;

import java.sql.SQLException;

public class StoredModel {
    private DataBase database;


    public void deleteSavedInfo(String title) throws SQLException {
        database.deleteEntry(title);
    }

    public Object[] getSavedTitles() throws SQLException {
        return database.getTitles().stream().sorted().toArray();
    }

    public void saveStoredInfo(String title, String text) throws SQLException {
        database.saveInfo(title, text);
    }

    public void saveLocally(String title, String extract) throws SQLException {
        database.saveInfo(title, extract);
    }

    public String getExtract(String title) throws SQLException {
        return database.getExtract(title);
    }

    public void setDatabase(DataBase database) {
        this.database = database;
    }
}
