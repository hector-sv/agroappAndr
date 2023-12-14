package com.hector.granjasandroid.presenter.Cultivo;


import com.hector.granjasandroid.contract.Cultivo.AddCultivoContract;
import com.hector.granjasandroid.domain.Cultivo;
import com.hector.granjasandroid.model.Cultivo.AddCultivoModel;
import com.hector.granjasandroid.view.Cultivo.AddCultivoView;

public class AddCultivoPresenter implements AddCultivoContract.Presenter, AddCultivoContract.Model.OnRegisterCultivoListener {
    private AddCultivoModel model;
    private AddCultivoView view;

    public AddCultivoPresenter(AddCultivoView view) {
        this.model = new AddCultivoModel();
        this.view = view;
    }

    @Override
    public void addCultivo(Cultivo cultivo) {
        model.addCultivo(cultivo, this);
    }


    @Override
    public void onRegisterSuccess(Cultivo cultivo) {
        view.showMessage("El Cultivo " + cultivo.getNombre() + "se ha añadido correctamente.");

    }

    @Override
    public void onRegisterError(String message) {
        view.showError("Se ha producido un error al añadir Cultivo");

    }
}

