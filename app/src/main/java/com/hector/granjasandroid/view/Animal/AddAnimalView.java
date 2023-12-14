package com.hector.granjasandroid.view.Animal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.hector.granjasandroid.R;
import com.hector.granjasandroid.contract.Animal.AddAnimalContract;
import com.hector.granjasandroid.domain.Animal;
import com.hector.granjasandroid.presenter.Animal.AddAnimalPresenter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddAnimalView extends AppCompatActivity implements AddAnimalContract.View {
    private AddAnimalPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal_view);

        Log.d("add Animal", "llamada desde add Animal View");

        presenter = new AddAnimalPresenter(this);
    }

    public void addButton(View view) {
        EditText etNombre = findViewById(R.id.nombre_animal_EditText);
        EditText etTipo = findViewById(R.id.tipo_animal_EditText);
        EditText etFechaEntrada= findViewById(R.id.fechaEntrada_animal_EditText);
        EditText etSexo = findViewById(R.id.sexo_animal_EditText);

        String nombre = etNombre.getText().toString();
        String tipo = etTipo.getText().toString();
        String sexo = etSexo.getText().toString();
        String fechaEntrada = etFechaEntrada.getText().toString();


        Animal animal = new Animal(nombre, tipo, fechaEntrada, sexo);
        presenter.addAnimal(animal);

        finish();
    }

    public void cancelButton(View view) {
        onBackPressed();
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(((EditText) findViewById(R.id.nombre_animal_EditText)), errorMessage,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(((EditText) findViewById(R.id.nombre_animal_EditText)), message,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void resetForm() {
        ((EditText) findViewById(R.id.nombre_animal_EditText)).setText("");
        ((EditText) findViewById(R.id.tipo_animal_EditText)).setText("");
        ((EditText) findViewById(R.id.sexo_animal_EditText)).setText("");
        ((EditText) findViewById(R.id.fechaEntrada_animal_EditText)).setText("");


        ((EditText) findViewById(R.id.nombre_animal_EditText)).requestFocus();
    }


}
