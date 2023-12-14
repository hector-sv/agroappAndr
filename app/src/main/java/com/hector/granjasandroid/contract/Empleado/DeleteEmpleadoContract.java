package com.hector.granjasandroid.contract.Empleado;

public interface DeleteEmpleadoContract {

    interface Model {
        interface OnDeleteEmpleadoListener {
            void onDeleteSuccess();

            void onDeleteError(String message);
        }

        void deleteEmpleado(long empleadoId, OnDeleteEmpleadoListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void deleteEmpleado(long empleadoId);
    }
}

