package com.hector.granjasandroid.presenter.Cultivo;


import com.hector.granjasandroid.adapter.CultivoAdapter;
import com.hector.granjasandroid.contract.Cultivo.DeleteCultivoContract;
import com.hector.granjasandroid.model.Cultivo.DeleteCultivoModel;

public class DeleteCultivoPresenter implements DeleteCultivoContract.Presenter, DeleteCultivoContract.Model.OnDeleteCultivoListener {

    private DeleteCultivoModel model;
    private CultivoAdapter view;

    public DeleteCultivoPresenter(CultivoAdapter view) {
        this.model = new DeleteCultivoModel();
        this.view = view;
    }


    @Override
    public void deleteCultivo(long cultivolId) {
        model.deleteCultivo(cultivolId, this);

    }

    @Override
    public void onDeleteSuccess() {
        view.showMessage("Animal eliminado correctamente.");

    }

    @Override
    public void onDeleteError(String message) {
        view.showError("Se ha producido un error al eliminar Animal");

    }
}
