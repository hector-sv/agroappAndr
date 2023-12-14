package com.hector.granjasandroid.contract.Cultivo;

public interface DeleteCultivoContract {

    interface Model {
        interface OnDeleteCultivoListener {
            void onDeleteSuccess();

            void onDeleteError(String message);
        }

        void deleteCultivo(long cultivoId, DeleteCultivoContract.Model.OnDeleteCultivoListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void deleteCultivo(long coultivoId);
    }
}
