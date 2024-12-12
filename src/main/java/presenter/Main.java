package presenter;

import model.MainModel;

public class Main {
    public static void main(String[] args) {
        MainModel model = new MainModel();
        MainPresenter presenter = new MainPresenter(model);
        presenter.start();
    }
}