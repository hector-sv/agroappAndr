package com.hector.granjasandroid.presenter.Empleado;

import com.hector.granjasandroid.adapter.EmpleadoAdapter;
import com.hector.granjasandroid.contract.Empleado.DeleteEmpleadoContract;
import com.hector.granjasandroid.model.Empleado.DeleteEmpleadoModel;

public class DeleteEmpleadoPresenter implements DeleteEmpleadoContract.Presenter, DeleteEmpleadoContract.Model.OnDeleteEmpleadoListener {

    private DeleteEmpleadoModel model;
    private EmpleadoAdapter view;

    public DeleteEmpleadoPresenter(EmpleadoAdapter view) {
        this.model = new DeleteEmpleadoModel();
        this.view = view;
    }

    @Override
    public void deleteEmpleado(long empleadoId) {
        model.deleteEmpleado(empleadoId, this);

    }

    @Override
    public void onDeleteSuccess() {
        view.showMessage("Empleado eliminado correctamente.");

    }

    @Override
    public void onDeleteError(String message) {
        view.showError("Se ha producido un error al eliminar Animal");

    }
}
