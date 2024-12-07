package presenter;

import model.DataBase;
import model.DataBaseImp;
import utils.Serie;
import model.SearchModelImp;
import view.MainView;

import java.util.LinkedList;

public class SearchPresenterImp implements SearchPresenter {


    private MainView view;

    private SearchModelImp model;

    private Thread taskThread;

    public SearchPresenterImp(SearchModelImp model) {
        this.model = model;
        model.setPresenter(this);
    }

    public void start(){
        view = new MainView(this);
        view.showView();
    }


    @Override
    public void searchSeries() {
        taskThread = new Thread(() -> {
            String seriesName = view.getSearchSerieField();

            LinkedList<Serie> results = model.searchSeries(seriesName);

            view.showResults(results);


        });

        taskThread.start();

    }

    @Override
    public void getSelectedExtract(Serie selectedResult){
        String extract = model.searchPageExtract(selectedResult);
        view.setSearchResultTextPane(extract);
    }

    @Override
    public void deleteStoredInfo() {
        if (view.hasSelectedIndex()) { // If there is a selected index in the comboBox
            String title = view.getSeletedSavedTitle();
            model.deleteSavedInfo(title);
            view.deleteSelectedIndex();
        }
    }

    @Override
    public void saveStoredInfo() {
        if (view.hasSelectedIndex()) {
            String title = view.getSeletedSavedTitle();
            String text = view.getSearchResultTextPane();
            model.saveStoredInfo(title, text);
        }
    }

    public void saveLocally() {
        try{
            Serie lastSearchedSeries = view.getLastSearchedSeries();
            if(lastSearchedSeries != null) {
                model.saveLocally();
                view.showSuccessMessage("The series was correctly saved!");
                view.setSelectSavedComboBox(model.getSavedTitles());
            }
        } catch(Exception e){
            view.showErrorMessage(e.getMessage());
        }

    }

    @Override
    public Serie getLastSearchedSeries() {
        return view.getLastSearchedSeries();
    }

    @Override
    public void recordScore() {
        Serie lastSearchedSeries = view.getLastSearchedSeries();
        int newScore = view.getScore();
        lastSearchedSeries.setScore(newScore);
        model.setScore();
    }


    public boolean hasScore() {
        return model.hasScore(getLastSearchedSeries());
    }

    public void getStoredInfo() {
        taskThread = new Thread(() -> {
            String selectedTitle = view.getSeletedSavedTitle();
            String extract = model.getExtract(selectedTitle);
            view.setStoredTextPane(extract);
        });
        taskThread.start();
    }


    public void initializeSavedPanel() {
        view.setSelectSavedComboBox(model.getSavedTitles());
    }

    public int getScore() {
        return view.getScore();
    }

    @Override
    public int getScoreSerie(String title) {
        return model.getScore();
    }
}
