package com.hector.granjasandroid.contract.Cultivo;

import com.hector.granjasandroid.domain.Cultivo;

import java.util.List;

public interface CultivoListContract {
    interface Model {
        interface OnLoadCultivoListener {
            void onLoadCultivosSuccess(List<Cultivo> cultivos);

            void onLoadCultivosError(String message);
        }

        void loadAllCultivos(CultivoListContract.Model.OnLoadCultivoListener listener);
    }

    interface View {
        void showCultivos(List<Cultivo> cultivos);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllCultivos();
    }

}
