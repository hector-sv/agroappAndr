package com.hector.granjasandroid.presenter.Cultivo;


import com.hector.granjasandroid.contract.Cultivo.ModifyCultivoContract;
import com.hector.granjasandroid.domain.Cultivo;
import com.hector.granjasandroid.model.Cultivo.ModifyCultivoModel;
import com.hector.granjasandroid.view.Cultivo.ModifyCultivoView;

public class ModifyCultivoPresenter implements ModifyCultivoContract.Presenter, ModifyCultivoContract.Model.OnModifyCultivoListener {

    private ModifyCultivoModel model;
    private ModifyCultivoView view;

    public ModifyCultivoPresenter(ModifyCultivoView view) {
        this.model = new ModifyCultivoModel();
        this.view = view;
    }

    @Override
    public void modifyCultivo(long id, Cultivo cultivo) {
        model.modifyCultivo(id, cultivo, this);

    }

    @Override
    public void onModifySuccess(Cultivo cultivo) {
        view.showMessage("Cultivo modificado correctamente.");

    }

    @Override
    public void onModifyError(String message) {
        view.showError("Se ha producido un error al modificar Cultivo");

    }
}
