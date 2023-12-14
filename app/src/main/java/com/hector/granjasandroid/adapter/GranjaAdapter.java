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
import com.hector.granjasandroid.contract.Granja.DeleteGranjaContract;
import com.hector.granjasandroid.domain.Granja;
import com.hector.granjasandroid.presenter.Granja.DeleteGranjaPresenter;
import com.hector.granjasandroid.view.Granja.ModifyGranjaView;

import java.util.List;

import lombok.NonNull;

public class GranjaAdapter extends RecyclerView.Adapter<GranjaAdapter.GranjaHolder> implements DeleteGranjaContract.View {

    private Context context;
    private List<Granja> granjaList;
    private DeleteGranjaPresenter presenter;

    public GranjaAdapter(Context context, List<Granja> dataList) {
        this.context = context;
        this.granjaList = dataList;
        presenter = new DeleteGranjaPresenter(this);
    }

    public Context getContext() {
        return context;
    }

    @Override
    public GranjaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_granja, parent, false);
        return new GranjaHolder(view);
    }

    @Override
    public void onBindViewHolder(GranjaAdapter.GranjaHolder holder, int position) {

        holder.granjaNombre.setText(granjaList.get(position).getNombre());
        holder.granjaDireccion.setText(granjaList.get(position).getDireccion());
        holder.granjaTelefono.setText(granjaList.get(position).getTelefono());
        holder.granjaCorreo.setText(granjaList.get(position).getCorreo());
    }

    @Override
    public int getItemCount() {
        return granjaList.size();
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showMessage(String message) {

    }

    public class GranjaHolder extends RecyclerView.ViewHolder {
        public TextView granjaNombre;
        public TextView granjaDireccion;
        public TextView granjaTelefono;
        public TextView granjaCorreo;

        public Button modifyGranjaButton;
        public Button deleteGranjaButton;

        public View parentView;

        //constructor del holder
        public GranjaHolder(View view) {
            super(view);
            parentView = view;

            granjaNombre = view.findViewById((R.id.granja_nombre));
            granjaDireccion = view.findViewById(R.id.granja_direccion);
            granjaTelefono = view.findViewById(R.id.granja_telefono);
            granjaCorreo = view.findViewById((R.id.granja_correo));

            modifyGranjaButton = view.findViewById(R.id.modify_granja_button);
            deleteGranjaButton = view.findViewById(R.id.delete_granja_button);

            modifyGranjaButton.setOnClickListener(v -> modifyGranja(getAdapterPosition()));
            deleteGranjaButton.setOnClickListener(v -> deleteGranja(getAdapterPosition()));
        }

        private void modifyGranja(int position) {
            Granja granja = granjaList.get(position);

            Intent intent = new Intent(context, ModifyGranjaView.class);
            intent.putExtra("granja", granja);
            context.startActivity(intent);
        }

        private void deleteGranja(int position) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.are_you_sure_delete_granja_message)
                    .setTitle(R.string.delete_granja_name)
                    .setPositiveButton("Si", (dialog, id) -> {
                        Granja granja = granjaList.get(position);
                        presenter.deleteGranja(granja.getId());

                        granjaList.remove(position);
                        notifyItemRemoved(position);
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }
}
