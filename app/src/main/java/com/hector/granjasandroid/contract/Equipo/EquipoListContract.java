package com.hector.granjasandroid.contract.Equipo;

import com.hector.granjasandroid.domain.Equipo;

import java.util.List;

public interface EquipoListContract {
    interface Model {
        interface OnLoadEquipoListener {
            void onLoadEquiposSuccess(List<Equipo> equipos);

            void onLoadEquiposError(String message);
        }

        void loadAllEquipos(OnLoadEquipoListener listener);
    }

    interface View {
        void showEquipos(List<Equipo> equipos);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllEquipos();
    }
}
