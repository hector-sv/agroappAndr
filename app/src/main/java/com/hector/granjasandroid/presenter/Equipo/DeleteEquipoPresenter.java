package com.hector.granjasandroid.presenter.Equipo;


import com.hector.granjasandroid.adapter.EquipoAdapter;
import com.hector.granjasandroid.contract.Equipo.DeleteEquipoContract;
import com.hector.granjasandroid.model.Equipo.DeleteEquipoModel;

public class DeleteEquipoPresenter implements DeleteEquipoContract.Presenter, DeleteEquipoContract.Model.OnDeleteEquipoListener {

    private DeleteEquipoModel model;
    private EquipoAdapter view;

    public DeleteEquipoPresenter(EquipoAdapter view) {
        this.model = new DeleteEquipoModel();
        this.view = view;
    }

    @Override
    public void deleteEquipo(long equipoId) {
        model.deleteEquipo(equipoId, this);

    }

    @Override
    public void onDeleteSuccess() {
        view.showMessage("Equipo eliminado correctamente.");

    }

    @Override
    public void onDeleteError(String message) {
        view.showError("Se ha producido un error al eliminar Equipo");

    }
}
