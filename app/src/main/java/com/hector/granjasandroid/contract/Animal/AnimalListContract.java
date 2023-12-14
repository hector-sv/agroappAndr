package com.hector.granjasandroid.contract.Animal;

import com.hector.granjasandroid.domain.Animal;

import java.util.List;

public interface AnimalListContract {
    interface Model {
        interface OnLoadAnimalListener {
            void onLoadAnimalesSuccess(List<Animal> animales);

            void onLoadAnimalesError(String message);
        }

        void loadAllAnimales(OnLoadAnimalListener listener);
    }

    interface View {
        void showAnimales(List<Animal> animales);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllAnimales();
    }
}
