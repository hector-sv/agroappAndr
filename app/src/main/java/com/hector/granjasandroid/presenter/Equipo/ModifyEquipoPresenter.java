package com.hector.granjasandroid.presenter.Equipo;

import com.hector.granjasandroid.contract.Equipo.ModifyEquipoContract;
import com.hector.granjasandroid.domain.Equipo;
import com.hector.granjasandroid.model.Equipo.ModifyEquipoModel;
import com.hector.granjasandroid.view.Equipo.ModifyEquipoView;

public class ModifyEquipoPresenter implements ModifyEquipoContract.Presenter, ModifyEquipoContract.Model.OnModifyEquipoListener {

    private ModifyEquipoModel model;
    private ModifyEquipoView view;

    public ModifyEquipoPresenter(ModifyEquipoView view) {
        this.model = new ModifyEquipoModel();
        this.view = view;
    }

    @Override
    public void modifyEquipo(long id, Equipo equipo) {
        model.modifyEquipo(id, equipo, this);

    }

    @Override
    public void onModifySuccess(Equipo equipo) {
        view.showMessage("Equipo modificado correctamente.");

    }

    @Override
    public void onModifyError(String message) {
        view.showError("Se ha producido un error al modificar Equipo");

    }
}
