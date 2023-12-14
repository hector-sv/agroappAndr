package com.hector.granjasandroid.presenter.Cultivo;


import com.hector.granjasandroid.contract.Cultivo.CultivoListContract;
import com.hector.granjasandroid.domain.Cultivo;
import com.hector.granjasandroid.model.Cultivo.CultivoListModel;
import com.hector.granjasandroid.view.Cultivo.CultivoListView;

import java.util.List;

public class CultivoListPresenter implements CultivoListContract.Presenter, CultivoListContract.Model.OnLoadCultivoListener {

    private CultivoListModel model;
    private CultivoListView view;

    public CultivoListPresenter(CultivoListView view) {
        this.view = view;
        this.model = new CultivoListModel(view.getApplicationContext());

    }

    @Override
    public void loadAllCultivos() {
        model.loadAllCultivos(this);
    }

    @Override
    public void onLoadCultivosSuccess(List<Cultivo> cultivos) {
        view.showCultivos(cultivos);

    }

    @Override
    public void onLoadCultivosError(String message) {
        view.showMessage(message);

    }
}