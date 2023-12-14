package com.hector.granjasandroid.contract.Equipo;

public interface DeleteEquipoContract {

    interface Model {
        interface OnDeleteEquipoListener {
            void onDeleteSuccess();

            void onDeleteError(String message);
        }

        void deleteEquipo(long equipoId, OnDeleteEquipoListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void deleteEquipo(long equipoId);
    }
}

