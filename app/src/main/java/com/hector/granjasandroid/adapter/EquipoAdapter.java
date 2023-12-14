package com.hector.granjasandroid.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hector.granjasandroid.R;
import com.hector.granjasandroid.contract.Equipo.DeleteEquipoContract;
import com.hector.granjasandroid.domain.Equipo;
import com.hector.granjasandroid.presenter.Equipo.DeleteEquipoPresenter;
import com.hector.granjasandroid.view.Equipo.ModifyEquipoView;

import java.time.LocalDate;
import java.util.List;

import lombok.NonNull;

public class EquipoAdapter  extends RecyclerView.Adapter<EquipoAdapter.EquipoHolder> implements DeleteEquipoContract.View {

    private Context context;
    private List<Equipo> equipoList;
    private View snackBarView;
    private DeleteEquipoPresenter presenter;

    public EquipoAdapter(Context context, List<Equipo> dataList) {
        this.context = context;
        this.equipoList = dataList;
        presenter = new DeleteEquipoPresenter(this);
    }

    public Context getContext() {
        return context;
    }
    @Override
    public EquipoAdapter.EquipoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_equipo, parent, false);
        return new EquipoAdapter.EquipoHolder(view);
    }

    @Override
    public void onBindViewHolder(EquipoAdapter.EquipoHolder holder, int position) {

        holder.equipoNombre.setText(equipoList.get(position).getNombre());
        holder.equipoTipo.setText(equipoList.get(position).getTipo());
        holder.equipoEstado.setText(equipoList.get(position).getEstado());
        holder.equipoFechaCompra.setText(equipoList.get(position).getFechaCompra());

    }

    @Override
    public int getItemCount() {
        return equipoList.size();
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showMessage(String message) {

    }

    public class EquipoHolder extends RecyclerView.ViewHolder {
        public TextView equipoNombre;
        public TextView equipoTipo;
        public TextView equipoEstado;
        public TextView equipoFechaCompra;

        public Button modifyEquipoButton;
        public Button deleteEquipoButton;

        public View parentView;

        //constructor del holder
        public EquipoHolder(View view) {
            super(view);
            parentView = view;

            equipoNombre = view.findViewById((R.id.equipo_nombre));
            equipoTipo = view.findViewById(R.id.equipo_tipo);
            equipoEstado = view.findViewById(R.id.equipo_estado);
            equipoFechaCompra = view.findViewById(R.id.equipo_fechaCompra);


            modifyEquipoButton = view.findViewById(R.id.modify_equipo_button);
            deleteEquipoButton = view.findViewById(R.id.delete_equipo_button);

            modifyEquipoButton.setOnClickListener(v -> modifyEquipo(getAdapterPosition()));
            deleteEquipoButton.setOnClickListener(v -> deleteEquipo(getAdapterPosition()));
        }

        private void modifyEquipo(int position) {
            Equipo equipo = equipoList.get(position);

            Intent intent = new Intent(context, ModifyEquipoView.class);
            intent.putExtra("equipo", equipo);
            context.startActivity(intent);
        }

        private void deleteEquipo(int position) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.are_you_sure_delete_equipo_message)
                    .setTitle(R.string.delete_equipo_name)
                    .setPositiveButton("Si", (dialog, id) -> {
                        Equipo equipo = equipoList.get(position);
                        presenter.deleteEquipo(equipo.getId());

                        equipoList.remove(position);
                        notifyItemRemoved(position);
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }
}
