package com.hector.granjasandroid.contract.Granja;

public interface DeleteGranjaContract {

    interface Model {
        interface OnDeleteGranjaListener {
            void onDeleteSuccess();

            void onDeleteError(String message);
        }

        void deleteGranja(long granjaId, OnDeleteGranjaListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void deleteGranja(long granjaId);
    }
}

