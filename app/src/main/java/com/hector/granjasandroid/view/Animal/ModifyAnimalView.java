package com.hector.granjasandroid.view.Animal;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hector.granjasandroid.R;
import com.hector.granjasandroid.contract.Animal.ModifyAnimalContract;
import com.hector.granjasandroid.domain.Animal;
import com.hector.granjasandroid.presenter.Animal.ModifyAnimalPresenter;

import java.time.LocalDate;

public class ModifyAnimalView extends AppCompatActivity implements ModifyAnimalContract.View {

    private long id;
    private Animal animal;
    private ModifyAnimalPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_animal_view);

        noticeId();

        Bundle bundle = getIntent().getExtras();
        animal = (Animal) bundle.getSerializable("animal");
        id = animal.getId();

        fillData(animal);

        presenter = new ModifyAnimalPresenter(this);
    }


    public void modifyButton(View view) {
        EditText etNombre = findViewById(R.id.nombre_animal_ModifyEditText);
        EditText etTipo = findViewById(R.id.tipo_animal_ModifyEditText);
        EditText etSexo = findViewById(R.id.sexo_animal_ModifyEditText);
        EditText etFechaEntrada = findViewById(R.id.fechaEntrada_animal_ModifyEditText);

        String nombre = etNombre.getText().toString();
        String tipo = etTipo.getText().toString();
        String sexo = etSexo.getText().toString();
        String fechaEntrada = etFechaEntrada.getText().toString();



        Animal modifiedAnimal = new Animal(nombre, tipo, sexo, fechaEntrada);
        presenter.modifyAnimal(id, modifiedAnimal);

        finish();

    }


    public void cancelModifyButton(View view) {
        onBackPressed();
    }


    private void fillData(Animal animal) {
        EditText etNombre = findViewById(R.id.nombre_animal_ModifyEditText);
        EditText etTipo = findViewById(R.id.tipo_animal_ModifyEditText);
        EditText etSexo = findViewById(R.id.sexo_animal_ModifyEditText);
        EditText etFechaEntrada = findViewById(R.id.fechaEntrada_animal_ModifyEditText);

        etNombre.setText(animal.getNombre());
        etTipo.setText(animal.getTipo());
        etSexo.setText(animal.getSexo());
        etFechaEntrada.setText(animal.getFechaEntrada());


    }

    private void noticeId() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.are_you_sure_modify_animal_message)
                .setTitle(R.string.modify_animal_name)
                .setNegativeButton("No", (dialog, id) -> {

                    Intent intent = new Intent(this, AnimalListView.class);
                    intent.putExtra("id", animal.getId());
                    this.startActivity(intent);
                })
                .setPositiveButton("Yes", (dialog, id) -> dialog.dismiss()); //boton del no
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showMessage(String message) {

    }
}

