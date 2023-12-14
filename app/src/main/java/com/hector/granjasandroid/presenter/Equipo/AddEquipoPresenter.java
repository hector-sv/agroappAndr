package com.hector.granjasandroid.presenter.Equipo;


import com.hector.granjasandroid.contract.Equipo.AddEquipoContract;
import com.hector.granjasandroid.domain.Equipo;
import com.hector.granjasandroid.model.Equipo.AddEquipoModel;
import com.hector.granjasandroid.view.Equipo.AddEquipoView;

public class AddEquipoPresenter implements AddEquipoContract.Presenter, AddEquipoContract.Model.OnRegisterEquipoListener {
    private AddEquipoModel model;
    private AddEquipoView view;

    public AddEquipoPresenter(AddEquipoView view) {
        this.model = new AddEquipoModel();
        this.view = view;
    }

    @Override
    public void addEquipo(Equipo equipo) {
        model.addEquipo(equipo, this);
    }


    @Override
    public void onRegisterSuccess(Equipo equipo) {
        view.showMessage("El Equipo " + equipo.getNombre() + "se ha añadido correctamente.");

    }

    @Override
    public void onRegisterError(String message) {
        view.showError("Se ha producido un error al añadir Animal");

    }
}

