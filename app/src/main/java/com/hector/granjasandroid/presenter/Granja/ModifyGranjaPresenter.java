package com.hector.granjasandroid.presenter.Granja;


import com.hector.granjasandroid.contract.Granja.ModifyGranjaContract;
import com.hector.granjasandroid.domain.Granja;
import com.hector.granjasandroid.model.Granja.ModifyGranjaModel;
import com.hector.granjasandroid.view.Granja.ModifyGranjaView;

public class ModifyGranjaPresenter implements ModifyGranjaContract.Presenter, ModifyGranjaContract.Model.OnModifyGranjaListener {

    private ModifyGranjaModel model;
    private ModifyGranjaView view;

    public ModifyGranjaPresenter(ModifyGranjaView view) {
        this.model = new ModifyGranjaModel();
        this.view = view;
    }

    @Override
    public void modifyGranja(long id, Granja granja) {
        model.modifyGranja(id, granja, this);

    }

    @Override
    public void onModifySuccess(Granja granja) {
        view.showMessage("Granja modificada correctamente.");

    }

    @Override
    public void onModifyError(String message) {
        view.showError("Se ha producido un error al modificar Granja");

    }
}
