package com.hector.granjasandroid.contract.Granja;


import com.hector.granjasandroid.domain.Granja;

import java.util.List;

public interface GranjaListContract {
    interface Model {
        interface OnLoadGranjaListener {
            void onLoadGranjasSuccess(List<Granja> granjas);

            void onLoadGranjasError(String message);
        }

        void loadAllGranjas(OnLoadGranjaListener listener);
    }

    interface View {
        void showGranjas(List<Granja> granjas);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllGranjas();
    }
}
