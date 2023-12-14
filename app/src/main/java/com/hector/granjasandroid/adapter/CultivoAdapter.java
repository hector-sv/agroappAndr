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
import com.hector.granjasandroid.contract.Cultivo.DeleteCultivoContract;
import com.hector.granjasandroid.domain.Cultivo;
import com.hector.granjasandroid.presenter.Cultivo.DeleteCultivoPresenter;
import com.hector.granjasandroid.view.Cultivo.ModifyCultivoView;

import java.time.LocalDate;
import java.util.List;

import lombok.NonNull;

public class CultivoAdapter extends RecyclerView.Adapter<CultivoAdapter.CultivoHolder> implements DeleteCultivoContract.View {

    private Context context;
    private List<Cultivo> cultivoList;
    private View snackBarView;
    private DeleteCultivoPresenter presenter;

    public CultivoAdapter(Context context, List<Cultivo> dataList) {
        this.context = context;
        this.cultivoList = dataList;
        presenter = new DeleteCultivoPresenter(this);
    }

    public Context getContext() {
        return context;
    }
    @Override
    public CultivoAdapter.CultivoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cultivo, parent, false);
        return new CultivoAdapter.CultivoHolder(view);
    }

    @Override
    public void onBindViewHolder(CultivoAdapter.CultivoHolder holder, int position) {

        holder.cultivoNombre.setText(cultivoList.get(position).getNombre());
        holder.cultivoTipo.setText(cultivoList.get(position).getTipo());
    }


    @Override
    public int getItemCount() {
        return cultivoList.size();
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showMessage(String message) {

    }

    public class CultivoHolder extends RecyclerView.ViewHolder {
        public TextView cultivoNombre;
        public TextView cultivoTipo;
        public TextView cultivoFechaSiembra;
        public TextView cultivoFechaCosecha;

        public Button modifyCultivoButton;
        public Button deleteCultivoButton;

        public View parentView;

        //constructor del holder
        public CultivoHolder(View view) {
            super(view);
            parentView = view;

            cultivoNombre = view.findViewById((R.id.cultivo_nombre));
            cultivoTipo = view.findViewById(R.id.cultivo_tipo);
            cultivoFechaSiembra = view.findViewById(R.id.cultivo_fecha_siembra);
            cultivoFechaCosecha = view.findViewById(R.id.cultivo_fecha_cosecha);


            modifyCultivoButton = view.findViewById(R.id.modify_cultivo_button);
            deleteCultivoButton = view.findViewById(R.id.delete_cultivo_button);

            modifyCultivoButton.setOnClickListener(v -> modifyCultivo(getAdapterPosition()));
            deleteCultivoButton.setOnClickListener(v -> deleteCultivo(getAdapterPosition()));
        }

        private void modifyCultivo(int position) {
            Cultivo cultivo = cultivoList.get(position);

            Intent intent = new Intent(context, ModifyCultivoView.class);
            intent.putExtra("cultivo", cultivo);
            context.startActivity(intent);
        }

        private void deleteCultivo(int position) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.are_you_sure_delete_cultivo_message)
                    .setTitle(R.string.delete_cultivo_name)
                    .setPositiveButton("Si", (dialog, id) -> {
                        Cultivo cultivo = cultivoList.get(position);
                        presenter.deleteCultivo(cultivo.getId());

                        cultivoList.remove(position);
                        notifyItemRemoved(position);
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }
}

