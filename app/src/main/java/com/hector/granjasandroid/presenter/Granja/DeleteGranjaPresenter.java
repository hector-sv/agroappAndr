package com.hector.granjasandroid.presenter.Granja;


import com.hector.granjasandroid.adapter.GranjaAdapter;
import com.hector.granjasandroid.contract.Granja.DeleteGranjaContract;
import com.hector.granjasandroid.model.Granja.DeleteGranjaModel;

public class DeleteGranjaPresenter implements DeleteGranjaContract.Presenter, DeleteGranjaContract.Model.OnDeleteGranjaListener {

    private DeleteGranjaModel model;
    private GranjaAdapter view;

    public DeleteGranjaPresenter(GranjaAdapter view) {
        this.model = new DeleteGranjaModel();
        this.view = view;
    }

    @Override
    public void deleteGranja(long granjaId) {
        model.deleteGranja(granjaId, this);

    }

    @Override
    public void onDeleteSuccess() {
        view.showMessage("Granja eliminado correctamente.");

    }

    @Override
    public void onDeleteError(String message) {
        view.showError("Se ha producido un error al eliminar Granja");

    }
}
