package com.hector.granjasandroid.contract.Animal;

import com.hector.granjasandroid.domain.Animal;

public interface ModifyAnimalContract {

    interface Model {
        interface OnModifyAnimalListener {
            void onModifySuccess(Animal animal);

            void onModifyError(String message);
        }

        void modifyAnimal(long id, Animal animal, ModifyAnimalContract.Model.OnModifyAnimalListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void modifyAnimal(long id, Animal animal);
    }
}


