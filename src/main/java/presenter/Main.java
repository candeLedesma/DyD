package presenter;

import model.SearchModelImp;

public class Main {
    public static void main(String[] args) {
        SearchModelImp model = new SearchModelImp();
        SearchPresenterImp presenter = new SearchPresenterImp(model);
        presenter.start();
    }
}