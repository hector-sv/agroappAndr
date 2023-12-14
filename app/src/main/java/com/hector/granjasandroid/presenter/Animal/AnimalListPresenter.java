package com.hector.granjasandroid.presenter.Animal;

import com.hector.granjasandroid.contract.Animal.AnimalListContract;
import com.hector.granjasandroid.domain.Animal;
import com.hector.granjasandroid.model.Animal.AnimalListModel;
import com.hector.granjasandroid.view.Animal.AnimalListView;

import java.util.List;

public class AnimalListPresenter implements AnimalListContract.Presenter, AnimalListContract.Model.OnLoadAnimalListener {

    private AnimalListModel model;
    private AnimalListView view;

    public AnimalListPresenter(AnimalListView view) {
        this.view = view;
        this.model = new AnimalListModel(view.getApplicationContext());

    }

    @Override
    public void loadAllAnimales() {
        model.loadAllAnimales(this);
    }

    @Override
    public void onLoadAnimalesSuccess(List<Animal> animales) {
        view.showAnimales(animales);

    }

    @Override
    public void onLoadAnimalesError(String message) {
        view.showMessage(message);

    }
}