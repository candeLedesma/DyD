package model;

import model.database.DataBase;
import model.database.DataBaseImp;
import presenter.Presenter;
import presenter.SeriesPresenter;
import utils.Serie;

import java.io.IOException;
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
        database = new DataBaseImp();
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
        storedModel.deleteSavedInfo(title);
    }

    @Override
    public Object[] getSavedTitles() {
        return storedModel.getSavedTitles();
    }


    @Override
    public void saveStoredInfo(String title, String text) {
        storedModel.saveStoredInfo(title, text);
    }

    @Override
    public void saveLocally() {
        storedModel.saveLocally(presenter.getLastSearchedSeries().getTitle(), presenter.getLastSearchedSeries().getExtract());
    }

    @Override
    public void setScore() {
        scoredModel.setScore(presenter.getLastSearchedSeries().getTitle(), presenter.getScore());
    }

    @Override
    public boolean hasScore(Serie serie) {
        return scoredModel.hasScore(serie.getTitle());
    }

    @Override
    public int getScore() {
        return scoredModel.getScore(presenter.getLastSearchedSeries().getTitle());
    }

    @Override
    public List<Serie> getScoredSeries() {
        return scoredModel.getScoredSeries();
    }

    @Override
    public String getExtract(String selectedTitle) {
        return storedModel.getExtract(selectedTitle);
    }

    @Override
    public void setPresenter(SeriesPresenter seriesPresenter) {
        this.presenter = seriesPresenter;
    }
}
