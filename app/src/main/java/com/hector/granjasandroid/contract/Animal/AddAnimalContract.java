package com.hector.granjasandroid.contract.Animal;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.hector.granjasandroid.domain.Animal;

public interface AddAnimalContract {

    interface Model {
        interface OnRegisterAnimalListener {
            void onRegisterSuccess(Animal animal);

            void onRegisterError(String message);
        }

        void addAnimal(Animal animal, OnRegisterAnimalListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);

        void resetForm();
    }

    interface Presenter {
        void addAnimal(Animal animal);
    }
}

