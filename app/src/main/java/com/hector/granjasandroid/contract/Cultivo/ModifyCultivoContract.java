package com.hector.granjasandroid.contract.Cultivo;

import com.hector.granjasandroid.domain.Cultivo;

public interface ModifyCultivoContract {

    interface Model {
        interface OnModifyCultivoListener {
            void onModifySuccess(Cultivo cultivo);

            void onModifyError(String message);
        }

        void modifyCultivo(long id, Cultivo cultivo, ModifyCultivoContract.Model.OnModifyCultivoListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void modifyCultivo(long id, Cultivo cultivo);
    }
}

