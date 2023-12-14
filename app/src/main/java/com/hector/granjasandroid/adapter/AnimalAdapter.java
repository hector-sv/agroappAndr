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
import com.hector.granjasandroid.contract.Animal.DeleteAnimalContract;
import com.hector.granjasandroid.domain.Animal;
import com.hector.granjasandroid.presenter.Animal.DeleteAnimalPresenter;
import com.hector.granjasandroid.view.Animal.ModifyAnimalView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.NonNull;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalHolder> implements DeleteAnimalContract.View {

    private Context context;
    private List<Animal> animalList;
    private View snackBarView;
    private DeleteAnimalPresenter presenter;

    public AnimalAdapter(Context context, List<Animal> dataList) {
        this.context = context;
        this.animalList = dataList;
        presenter = new DeleteAnimalPresenter(this);
    }

    public Context getContext() {
        return context;
    }

    @Override
    public AnimalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_animal, parent, false);
        return new AnimalHolder(view);
    }

    @Override
    public void onBindViewHolder(AnimalHolder holder, int position) {
        holder.animalNombre.setText(animalList.get(position).getNombre());
        holder.animalTipo.setText(animalList.get(position).getTipo());
        holder.animalSexo.setText(animalList.get(position).getSexo());
        holder.animalfechaEntrada.setText(animalList.get(position).getFechaEntrada());
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showMessage(String message) {

    }

    public class AnimalHolder extends RecyclerView.ViewHolder {
        public TextView animalNombre;
        public TextView animalTipo;
        public TextView animalSexo;
        public TextView animalfechaEntrada;
        public Button modifyAnimalButton;
        public Button deleteAnimalButton;

        public View parentView;

        //constructor del holder
        public AnimalHolder(View view) {
            super(view);
            parentView = view;

            animalNombre = view.findViewById((R.id.animal_nombre));
            animalTipo = view.findViewById(R.id.animal_tipo);
            animalSexo = view.findViewById(R.id.animal_sexo);
            animalfechaEntrada = view.findViewById(R.id.animal_fecha_entrada);

            modifyAnimalButton = view.findViewById(R.id.modify_animal_button);
            deleteAnimalButton = view.findViewById(R.id.delete_animal_button);

            modifyAnimalButton.setOnClickListener(v -> modifyAnimal(getAdapterPosition()));
            deleteAnimalButton.setOnClickListener(v -> deleteAnimal(getAdapterPosition()));
        }

        private void modifyAnimal(int position) {
            Animal animal = animalList.get(position);

            Intent intent = new Intent(context, ModifyAnimalView.class);
            intent.putExtra("animal", animal);
            context.startActivity(intent);
        }

        private void deleteAnimal(int position) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.are_you_sure_delete_animal_message)
                    .setTitle(R.string.delete_animal_name)
                    .setPositiveButton("Si", (dialog, id) -> {
                        Animal animal = animalList.get(position);
                        presenter.deleteAnimal(animal.getId());

                        animalList.remove(position);
                        notifyItemRemoved(position);
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }
}
