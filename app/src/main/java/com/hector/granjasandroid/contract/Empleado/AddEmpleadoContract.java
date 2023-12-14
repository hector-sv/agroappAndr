package com.hector.granjasandroid.contract.Empleado;

import com.hector.granjasandroid.domain.Animal;
import com.hector.granjasandroid.domain.Empleado;

public interface AddEmpleadoContract {

    interface Model {
        interface OnRegisterEmpleadoListener {
            void onRegisterSuccess(Empleado empleado);

            void onRegisterError(String message);
        }

        void addEmpleado(Empleado empleado, OnRegisterEmpleadoListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);

        void resetForm();
    }

    interface Presenter {
        void addEmpleado(Empleado empleado);
    }
}

