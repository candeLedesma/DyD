package view;


import javax.swing.*;
import java.sql.SQLException;

public interface SeriesView {

    void showView();

    void showSuccessMessage(String message);

    void showErrorMessage(String message);

    void updateScore();

    void setSearchResultTextPane(String text) throws SQLException;

    String getSearchSerieField();

    int getScore();

    void setSelectSavedComboBox(Object[] savedTitles);

    String getSeletedSavedTitle();

    void deleteSelectedIndex();

    void setStoredTextPane(String extract);

    JTabbedPane getTabbedPane();


    SearchPanel getSearchView();

    StoragePanel getStoredView();

    ScoredSeriesPanel getScoredView();
}
