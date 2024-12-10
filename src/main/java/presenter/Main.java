package presenter;

import model.SeriesModel;

public class Main {
    public static void main(String[] args) {
        SeriesModel model = new SeriesModel();
        SeriesPresenter presenter = new SeriesPresenter(model);
        presenter.start();
    }
}