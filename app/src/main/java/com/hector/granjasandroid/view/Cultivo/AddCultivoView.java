package com.hector.granjasandroid.view.Cultivo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.hector.granjasandroid.R;
import com.hector.granjasandroid.contract.Cultivo.AddCultivoContract;
import com.hector.granjasandroid.domain.Cultivo;
import com.hector.granjasandroid.presenter.Cultivo.AddCultivoPresenter;

import java.time.LocalDate;

public class AddCultivoView extends AppCompatActivity implements AddCultivoContract.View {
    private AddCultivoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cultivo_view);

        Log.d("add Cultivo", "llamada desde add Cultivo View");

        presenter = new AddCultivoPresenter(this);

    }

    public void addButton(View view) {
        EditText etNombre = findViewById(R.id.nombre_cultivo_EditText);
        EditText etTipo = findViewById(R.id.tipo_cultivo_EditText);
        EditText etFechaSiembra = findViewById(R.id.fechaSiembra_cultivo_EditText);
        EditText etFechaCosecha = findViewById(R.id.fechaSiembra_cultivo_EditText);

        String nombre = etNombre.getText().toString();
        String tipo = etTipo.getText().toString();
        String fechaSiembra = etFechaSiembra.getText().toString();
        String fechaCosecha = etFechaCosecha.getText().toString();

        Cultivo cultivo = new Cultivo(nombre, tipo, fechaSiembra, fechaCosecha);
        presenter.addCultivo(cultivo);

        finish();
    }

    public void cancelButton(View view) {
        onBackPressed();
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(((EditText) findViewById(R.id.nombre_cultivo_EditText)), errorMessage,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(((EditText) findViewById(R.id.nombre_cultivo_EditText)), message,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void resetForm() {
        ((EditText) findViewById(R.id.nombre_cultivo_EditText)).setText("");
        ((EditText) findViewById(R.id.tipo_cultivo_EditText)).setText("");
        ((EditText) findViewById(R.id.fechaSiembra_cultivo_EditText)).setText("");
        ((EditText) findViewById(R.id.fechaCosecha_cultivo_EditText)).setText("");


        ((EditText) findViewById(R.id.nombre_cultivo_EditText)).requestFocus();
    }
}
