package com.hector.granjasandroid.presenter.Empleado;

import com.hector.granjasandroid.contract.Empleado.EmpleadoListContract;
import com.hector.granjasandroid.domain.Empleado;
import com.hector.granjasandroid.model.Empleado.EmpleadoListModel;
import com.hector.granjasandroid.view.Empleado.EmpleadoListView;

import java.util.List;

public class EmpleadoListPresenter implements EmpleadoListContract.Presenter, EmpleadoListContract.Model.OnLoadEmpleadoListener {

    private EmpleadoListModel model;
    private EmpleadoListView view;

    public EmpleadoListPresenter(EmpleadoListView view) {
        this.view = view;
        this.model = new EmpleadoListModel(view.getApplicationContext());

    }

    @Override
    public void loadAllEmpleados() {
        model.loadAllEmpleados(this);
    }

    @Override
    public void onLoadEmpleadosSuccess(List<Empleado> empleados) {
        view.showEmpleados(empleados);

    }

    @Override
    public void onLoadEmpleadosError(String message) {
        view.showMessage(message);

    }
}