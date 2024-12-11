package view;


import javax.swing.*;
import java.sql.SQLException;

public interface View {

    void showView() throws Exception;

    void setSearchResultTextPane(String breakingBad) throws SQLException;

    SearchPanel getSearchView();

    StoragePanel getStoredView();

    ScoredSeriesPanel getScoredView();

    JTabbedPane getTabbedPane();
}
