package com.hector.granjasandroid.contract.Granja;

import com.hector.granjasandroid.domain.Granja;

public interface ModifyGranjaContract {

    interface Model {
        interface OnModifyGranjaListener {
            void onModifySuccess(Granja granja);

            void onModifyError(String message);
        }

        void modifyGranja(long id, Granja granja, OnModifyGranjaListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void modifyGranja(long id, Granja granja);
    }
}


