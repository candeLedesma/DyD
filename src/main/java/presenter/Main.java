package presenter;

import model.SearchModelImp;

public class Main {
    public static void main(String[] args) {
        SearchModelImp model = new SearchModelImp();
        SeriesPresenter presenter = new SeriesPresenter(model);
        presenter.start();
    }
}