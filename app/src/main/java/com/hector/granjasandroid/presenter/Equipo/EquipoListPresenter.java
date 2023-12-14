package com.hector.granjasandroid.presenter.Equipo;

import com.hector.granjasandroid.contract.Equipo.EquipoListContract;
import com.hector.granjasandroid.domain.Equipo;
import com.hector.granjasandroid.model.Equipo.EquipoListModel;
import com.hector.granjasandroid.view.Equipo.EquipoListView;

import java.util.List;

public class EquipoListPresenter implements EquipoListContract.Presenter, EquipoListContract.Model.OnLoadEquipoListener {

    private EquipoListModel model;
    private EquipoListView view;

    public EquipoListPresenter(EquipoListView view) {
        this.view = view;
        this.model = new EquipoListModel(view.getApplicationContext());

    }

    @Override
    public void loadAllEquipos() {
        model.loadAllEquipos(this);
    }

    @Override
    public void onLoadEquiposSuccess(List<Equipo> equipos) {
        view.showEquipos(equipos);

    }

    @Override
    public void onLoadEquiposError(String message) {
        view.showMessage(message);

    }
}