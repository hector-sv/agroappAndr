package com.hector.granjasandroid.contract.Animal;

public interface DeleteAnimalContract {

    interface Model {
        interface OnDeleteAnimalListener {
            void onDeleteSuccess();

            void onDeleteError(String message);
        }

        void deleteAnimal(long animalId, OnDeleteAnimalListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void deleteAnimal(long animalId);
    }
}

