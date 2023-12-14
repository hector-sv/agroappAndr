package com.hector.granjasandroid.presenter.Empleado;

import com.hector.granjasandroid.contract.Animal.ModifyAnimalContract;
import com.hector.granjasandroid.contract.Empleado.ModifyEmpleadoContract;
import com.hector.granjasandroid.domain.Animal;
import com.hector.granjasandroid.domain.Empleado;
import com.hector.granjasandroid.model.Animal.ModifyAnimalModel;
import com.hector.granjasandroid.model.Empleado.ModifyEmpleadoModel;
import com.hector.granjasandroid.view.Animal.ModifyAnimalView;
import com.hector.granjasandroid.view.Empleado.ModifyEmpleadoView;

public class ModifyEmpleadoPresenter implements ModifyEmpleadoContract.Presenter, ModifyEmpleadoContract.Model.OnModifyEmpleadoListener {

    private ModifyEmpleadoModel model;
    private ModifyEmpleadoView view;

    public ModifyEmpleadoPresenter(ModifyEmpleadoView view) {
        this.model = new ModifyEmpleadoModel();
        this.view = view;
    }

    @Override
    public void modifyEmpleado(long id, Empleado empleado) {
        model.modifyEmpleado(id, empleado, this);

    }

    @Override
    public void onModifySuccess(Empleado empleado) {
        view.showMessage("Empleado modificado correctamente.");

    }

    @Override
    public void onModifyError(String message) {
        view.showError("Se ha producido un error al modificar Empleado");

    }
}
