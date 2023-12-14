package com.hector.granjasandroid.contract.Equipo;

import com.hector.granjasandroid.domain.Equipo;

public interface ModifyEquipoContract {

    interface Model {
        interface OnModifyEquipoListener {
            void onModifySuccess(Equipo equipo);

            void onModifyError(String message);
        }

        void modifyEquipo(long id, Equipo equipo, OnModifyEquipoListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void modifyEquipo(long id, Equipo equipo);
    }
}


