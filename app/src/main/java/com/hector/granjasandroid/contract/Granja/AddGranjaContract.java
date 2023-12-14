package com.hector.granjasandroid.contract.Granja;


import com.hector.granjasandroid.domain.Granja;

public interface AddGranjaContract {

    interface Model {
        interface OnRegisterGranjaListener {
            void onRegisterSuccess(Granja granja);

            void onRegisterError(String message);
        }

        void addGranja(Granja granja, OnRegisterGranjaListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);

        void resetForm();
    }

    interface Presenter {
        void addGranja(Granja granja);
    }
}

