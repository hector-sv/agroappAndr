package com.hector.granjasandroid.presenter.Empleado;

import com.hector.granjasandroid.contract.Animal.AddAnimalContract;
import com.hector.granjasandroid.contract.Empleado.AddEmpleadoContract;
import com.hector.granjasandroid.domain.Animal;
import com.hector.granjasandroid.domain.Empleado;
import com.hector.granjasandroid.model.Animal.AddAnimalModel;
import com.hector.granjasandroid.model.Empleado.AddEmpleadoModel;
import com.hector.granjasandroid.view.Animal.AddAnimalView;
import com.hector.granjasandroid.view.Empleado.AddEmpleadoView;

public class AddEmpleadoPresenter implements AddEmpleadoContract.Presenter, AddEmpleadoContract.Model.OnRegisterEmpleadoListener {
    private AddEmpleadoModel model;
    private AddEmpleadoView view;

    public AddEmpleadoPresenter(AddEmpleadoView view) {
        this.model = new AddEmpleadoModel();
        this.view = view;
    }

    @Override
    public void addEmpleado(Empleado empleado) {
        model.addEmpleado(empleado, this);
    }


    @Override
    public void onRegisterSuccess(Empleado empleado) {
        view.showMessage("El Empleado " + empleado.getNombre() + "se ha añadido correctamente.");

    }

    @Override
    public void onRegisterError(String message) {
        view.showError("Se ha producido un error al añadir Animal");

    }
}

