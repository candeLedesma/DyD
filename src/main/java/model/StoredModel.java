package model;

import model.database.DataBase;

public class StoredModel {
    private DataBase database;

    public StoredModel() {}

    public void deleteSavedInfo(String title) {
        database.deleteEntry(title);
    }

    public Object[] getSavedTitles() {
        return database.getTitles().stream().sorted().toArray();
    }

    public void saveStoredInfo(String title, String text) {
        database.saveInfo(title, text);
    }

    public void saveLocally(String title, String extract) {
        database.saveInfo(title, extract);
    }

    public String getExtract(String title) {
        return database.getExtract(title);
    }

    public void setDatabase(DataBase database) {
        this.database = database;
    }
}
