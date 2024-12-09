package presenter;

import model.SearchModelImp;

public class Main {
    public static void main(String[] args) {
        SearchModelImp model = new SearchModelImp();
        PresenterImp presenter = new PresenterImp(model);
        presenter.start();
    }
}