package com.hector.granjasandroid.presenter.Granja;

import com.hector.granjasandroid.contract.Granja.AddGranjaContract;
import com.hector.granjasandroid.domain.Granja;
import com.hector.granjasandroid.model.Granja.AddGranjaModel;
import com.hector.granjasandroid.view.Granja.AddGranjaView;

public class AddGranjaPresenter implements AddGranjaContract.Presenter, AddGranjaContract.Model.OnRegisterGranjaListener {
    private AddGranjaModel model;
    private AddGranjaView view;

    public AddGranjaPresenter(AddGranjaView view) {
        this.model = new AddGranjaModel();
        this.view = view;
    }

    @Override
    public void addGranja(Granja granja) {
        model.addGranja(granja, this);
    }


    @Override
    public void onRegisterSuccess(Granja granja) {
        view.showMessage("La Granja " + granja.getNombre() + "se ha añadido correctamente.");

    }

    @Override
    public void onRegisterError(String message) {
        view.showError("Se ha producido un error al añadir Granja");

    }
}

