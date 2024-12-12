package presenter;

import model.SeriesModel;
import model.Serie;
import view.MainView;

public class StoredPresenter {
    private final MainView view;
    private final SeriesModel model;

    public StoredPresenter(MainView view, SeriesModel model) {
        this.view = view;
        this.model = model;
    }

    public void deleteStoredInfo() {
        if (view.hasSelectedIndex()) {
            String title = view.getSeletedSavedTitle();
            model.deleteSavedInfo(title);
            view.deleteSelectedIndex();
        }
    }

    public void saveStoredInfo() {
        if (view.hasSelectedIndex()) {
            String title = view.getSeletedSavedTitle();
            String text = view.getSearchResultTextPane();
            model.saveStoredInfo(title, text);
            view.showSuccessSaveMessage();
        }
    }

    public void saveLocally() {
        try {
            Serie lastSearchedSeries = view.getLastSearchedSeries();
            if (lastSearchedSeries != null) {
                model.saveLocally();
                view.showSuccessMessage("Saved locally");
                view.setSelectSavedComboBox(model.getSavedTitles());
            }else{
                view.showErrorMessage("No series selected");
            }
        } catch (Exception e) {
            view.showErrorMessage(e.getMessage());
        }
    }

    public void getStoredInfo() {
        new Thread(() -> {
            String selectedTitle = view.getSeletedSavedTitle();
            String extract = model.getExtract(selectedTitle);
            view.setStoredTextPane(extract);
        }).start();
    }

    public void initializeSavedPanel() {
        view.setSelectSavedComboBox(model.getSavedTitles());
    }
}
