package com.hector.granjasandroid.contract.Empleado;

import com.hector.granjasandroid.domain.Empleado;

public interface ModifyEmpleadoContract {

    interface Model {
        interface OnModifyEmpleadoListener {
            void onModifySuccess(Empleado empleado);

            void onModifyError(String message);
        }

        void modifyEmpleado(long id, Empleado empleado, ModifyEmpleadoContract.Model.OnModifyEmpleadoListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void modifyEmpleado(long id, Empleado empleado);
    }
}


