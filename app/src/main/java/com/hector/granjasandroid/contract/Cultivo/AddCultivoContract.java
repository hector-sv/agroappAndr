package com.hector.granjasandroid.contract.Cultivo;

import com.hector.granjasandroid.domain.Cultivo;

public interface AddCultivoContract {

    interface Model {
        interface OnRegisterCultivoListener {
            void onRegisterSuccess(Cultivo cultivo);

            void onRegisterError(String message);
        }

        void addCultivo(Cultivo cultivo, OnRegisterCultivoListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);

        void resetForm();
    }

    interface Presenter {
        void addCultivo(Cultivo cultivo);
    }
}

