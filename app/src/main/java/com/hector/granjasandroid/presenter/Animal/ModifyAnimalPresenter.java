package com.hector.granjasandroid.presenter.Animal;

import com.hector.granjasandroid.contract.Animal.ModifyAnimalContract;
import com.hector.granjasandroid.domain.Animal;
import com.hector.granjasandroid.model.Animal.ModifyAnimalModel;
import com.hector.granjasandroid.view.Animal.ModifyAnimalView;

public class ModifyAnimalPresenter implements ModifyAnimalContract.Presenter, ModifyAnimalContract.Model.OnModifyAnimalListener {

    private ModifyAnimalModel model;
    private ModifyAnimalView view;

    public ModifyAnimalPresenter(ModifyAnimalView view) {
        this.model = new ModifyAnimalModel();
        this.view = view;
    }

    @Override
    public void modifyAnimal(long id, Animal animal) {
        model.modifyAnimal(id, animal, this);
//aqui
    }

    @Override
    public void onModifySuccess(Animal animal) {
        view.showMessage("Animal modificado correctamente.");

    }

    @Override
    public void onModifyError(String message) {
        view.showError("Se ha producido un error al modificar Animal");

    }
}
