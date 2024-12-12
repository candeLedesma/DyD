package model;

import model.API.WikipediaPageAPI;
import model.API.WikipediaSearchAPI;
import model.database.DataBase;
import model.database.DataBaseImp;
import presenter.Presenter;
import presenter.SeriesPresenter;
import utils.Serie;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SeriesModel  implements Model {
    private final SearchModel searchModel;
    private final StoredModel storedModel;
    private final ScoredModel scoredModel;
    private Presenter presenter;
    private DataBase database;

    public SeriesModel() {
        this.searchModel = new SearchModel();
        this.storedModel = new StoredModel();
        this.scoredModel = new ScoredModel();
        this.database = new DataBaseImp();
        initDatabase();
    }


    public SeriesModel(WikipediaSearchAPI wikipediaSearchAPI, WikipediaPageAPI wikipediaPageAPI, DataBase databaseStub) {
        searchModel = new SearchModel(wikipediaSearchAPI, wikipediaPageAPI);
        this.storedModel = new StoredModel();
        this.scoredModel = new ScoredModel();
        database = databaseStub;
        initDatabase();
    }

    private void initDatabase() {
        database.loadDatabase();
        scoredModel.setDatabase(database);
        storedModel.setDatabase(database);
    }


    @Override
    public LinkedList<Serie> searchSeries(String seriesName) throws IOException {
        return searchModel.searchSeries(seriesName);
    }


    @Override
    public String searchPageExtract(Serie searchResult) throws IOException {
        return searchModel.searchPageExtract(searchResult);
    }


    @Override
    public void deleteSavedInfo(String title) {
        try {
            storedModel.deleteSavedInfo(title);
        } catch (SQLException e) {
            presenter.showError("Error deleting saved information");
        }
    }

    @Override
    public Object[] getSavedTitles() {
        try {
            return storedModel.getSavedTitles();
        } catch (SQLException e) {
            presenter.showError("Error getting saved titles");
            return null;
        }
    }


    @Override
    public void saveStoredInfo(String title, String text) {
        try {
            storedModel.saveStoredInfo(title, text);
        } catch (SQLException e) {
            presenter.showError("Error saving stored information");
        }
    }

    @Override
    public void saveLocally() {
        try {
            storedModel.saveLocally(presenter.getLastSearchedSeries().getTitle(), presenter.getLastSearchedSeries().getExtract());
        } catch (SQLException e) {
            presenter.showError("Error saving locally");
        }
    }

    @Override
    public void setScore(){
        try {
            scoredModel.setScore(presenter.getLastSearchedSeries().getTitle(), presenter.getScore());
        } catch (SQLException e) {
            presenter.showError("Error setting score");
        }
    }

    @Override
    public boolean hasScore(String title){
        try {
            return scoredModel.hasScore(title);
        } catch (SQLException e) {
            presenter.showError("Error getting score");
            return false;
        }
    }

    @Override
    public int getScore() throws SQLException {
        return scoredModel.getScore(presenter.getLastSearchedSeries().getTitle());
    }

    @Override
    public List<Serie> getScoredSeries() {
        try {
            return scoredModel.getScoredSeries();
        } catch (SQLException e) {
            presenter.showError("Error getting scored series");
            return null;
        }
    }

    @Override
    public String getExtract(String selectedTitle) {
        try {
            return storedModel.getExtract(selectedTitle);
        } catch (SQLException e) {
            presenter.showError("Error getting extract");
            return null;
        }
    }

    @Override
    public void setPresenter(SeriesPresenter seriesPresenter) {
        this.presenter = seriesPresenter;
    }

}
