package com.hector.granjasandroid.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hector.granjasandroid.R;
import com.hector.granjasandroid.contract.Empleado.DeleteEmpleadoContract;
import com.hector.granjasandroid.domain.Empleado;
import com.hector.granjasandroid.presenter.Empleado.DeleteEmpleadoPresenter;
import com.hector.granjasandroid.view.Empleado.ModifyEmpleadoView;


import java.util.List;

import lombok.NonNull;

public class EmpleadoAdapter extends RecyclerView.Adapter<EmpleadoAdapter.EmpleadoHolder> implements DeleteEmpleadoContract.View {

    private Context context;
    private List<Empleado> empleadoList;
    private View snackBarView;
    private DeleteEmpleadoPresenter presenter;

    public EmpleadoAdapter(Context context, List<Empleado> dataList) {
        this.context = context;
        this.empleadoList = dataList;
        presenter = new DeleteEmpleadoPresenter(this);
    }

    public Context getContext() {
        return context;
    }

    @Override
    public EmpleadoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_empleado, parent, false);
        return new EmpleadoHolder(view);
    }

    @Override
    public void onBindViewHolder(EmpleadoHolder holder, int position) {
        holder.empleadoNombre.setText(empleadoList.get(position).getNombre());
        holder.empleadoApellido.setText(empleadoList.get(position).getApellido());
        holder.empleadoCargo.setText(empleadoList.get(position).getCargo());
        holder.empleadoCorreo.setText(empleadoList.get(position).getCorreo());
        holder.empleadoSalario.setText(Float.toString(empleadoList.get(position).getSalario()));
        holder.empleadofechaContrato.setText(empleadoList.get(position).getFechaContrato());
    }

    @Override
    public int getItemCount() {
        return empleadoList.size();
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showMessage(String message) {

    }

    public class EmpleadoHolder extends RecyclerView.ViewHolder {
        public TextView empleadoNombre;
        public TextView empleadoApellido;
        public TextView empleadoCargo;
        public TextView empleadoCorreo;
        public TextView empleadoSalario;
        public TextView empleadofechaContrato;

        public Button modifyEmpleadoButton;
        public Button deleteEmpleadoButton;

        public View parentView;

        //constructor del holder
        public EmpleadoHolder(View view) {
            super(view);
            parentView = view;

            empleadoNombre = view.findViewById((R.id.empleado_nombre));
            empleadoApellido = view.findViewById(R.id.empleado_apellido);
            empleadoCargo = view.findViewById(R.id.empleado_cargo);
            empleadoCorreo = view.findViewById(R.id.empleado_correo);
            empleadoSalario = view.findViewById(R.id.empleado_salario);
            empleadofechaContrato = view.findViewById(R.id.empleado_fecha_contrato);

            modifyEmpleadoButton = view.findViewById(R.id.modify_empleado_button);
            deleteEmpleadoButton = view.findViewById(R.id.delete_empleado_button);

            modifyEmpleadoButton.setOnClickListener(v -> modifyEmpleado(getAdapterPosition()));
            deleteEmpleadoButton.setOnClickListener(v -> deleteEmpleado(getAdapterPosition()));
        }

        private void modifyEmpleado(int position) {
            Empleado empleado = empleadoList.get(position);

            Intent intent = new Intent(context, ModifyEmpleadoView.class);
            intent.putExtra("empleado", empleado);
            context.startActivity(intent);
        }

        private void deleteEmpleado(int position) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.are_you_sure_delete_empleado_message)
                    .setTitle(R.string.delete_empleado_name)
                    .setPositiveButton("Si", (dialog, id) -> {
                        Empleado empleado = empleadoList.get(position);
                        presenter.deleteEmpleado(empleado.getId());

                        empleadoList.remove(position);
                        notifyItemRemoved(position);
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }
}
