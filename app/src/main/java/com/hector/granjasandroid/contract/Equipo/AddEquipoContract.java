package com.hector.granjasandroid.contract.Equipo;

import com.hector.granjasandroid.domain.Equipo;

public interface AddEquipoContract {

    interface Model {
        interface OnRegisterEquipoListener {
            void onRegisterSuccess(Equipo equipo);

            void onRegisterError(String message);
        }

        void addEquipo(Equipo equipo, OnRegisterEquipoListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);

        void resetForm();
    }

    interface Presenter {
        void addEquipo(Equipo equipo);
    }
}

