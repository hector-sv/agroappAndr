package com.hector.granjasandroid.presenter.Granja;

import com.hector.granjasandroid.contract.Granja.GranjaListContract;
import com.hector.granjasandroid.domain.Granja;
import com.hector.granjasandroid.model.Granja.GranjaListModel;

import java.util.List;

public class GranjaListPresenter implements GranjaListContract.Presenter, GranjaListContract.Model.OnLoadGranjaListener {
    private GranjaListModel model;
    private GranjaListContract.View view;

    public GranjaListPresenter(GranjaListContract.View view) {
        this.view = view;
        this.model = new GranjaListModel();

    }

    @Override
    public void loadAllGranjas() {
        model.loadAllGranjas(this);
    }

    @Override
    public void onLoadGranjasSuccess(List<Granja> granjas) {
        view.showGranjas(granjas);
    }

    @Override
    public void onLoadGranjasError(String message) {
        view.showMessage(message);

    }
}



