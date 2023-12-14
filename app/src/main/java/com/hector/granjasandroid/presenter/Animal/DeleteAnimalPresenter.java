package com.hector.granjasandroid.presenter.Animal;

import com.hector.granjasandroid.adapter.AnimalAdapter;
import com.hector.granjasandroid.contract.Animal.DeleteAnimalContract;
import com.hector.granjasandroid.model.Animal.DeleteAnimalModel;

public class DeleteAnimalPresenter implements DeleteAnimalContract.Presenter, DeleteAnimalContract.Model.OnDeleteAnimalListener {

    private DeleteAnimalModel model;
    private AnimalAdapter view;

    public DeleteAnimalPresenter(AnimalAdapter view) {
        this.model = new DeleteAnimalModel();
        this.view = view;
    }

    @Override
    public void deleteAnimal(long animalId) {
        model.deleteAnimal(animalId, this);

    }

    @Override
    public void onDeleteSuccess() {
        view.showMessage("Animal eliminado correctamente.");

    }

    @Override
    public void onDeleteError(String message) {
        view.showError("Se ha producido un error al eliminar Animal");

    }
}
