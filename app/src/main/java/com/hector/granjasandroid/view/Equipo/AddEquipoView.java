package com.hector.granjasandroid.view.Equipo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.hector.granjasandroid.R;
import com.hector.granjasandroid.contract.Equipo.AddEquipoContract;
import com.hector.granjasandroid.domain.Equipo;
import com.hector.granjasandroid.presenter.Equipo.AddEquipoPresenter;

import java.time.LocalDate;

public class AddEquipoView extends AppCompatActivity implements AddEquipoContract.View {
    private AddEquipoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_equipo_view);

        Log.d("add Equipo", "llamada desde add Equipo View");

        presenter = new AddEquipoPresenter(this);
    }


    public void addButton(View view) {
        EditText etNombre = findViewById(R.id.nombre_equipo_EditText);
        EditText etTipo = findViewById(R.id.tipo_equipo_EditText);
        EditText etEstado = findViewById(R.id.estado_equipo_EditText);
        EditText etFechaCompra = findViewById(R.id.fechaCompra_equipo_EditText);

        String nombre = etNombre.getText().toString();
        String tipo = etTipo.getText().toString();
        String fechaCompra = etFechaCompra.getText().toString();
        String estado = etEstado.getText().toString();

        Equipo equipo = new Equipo(nombre, tipo, fechaCompra, estado);
        presenter.addEquipo(equipo);

        finish();
    }


    public void cancelButton(View view) {
        onBackPressed();
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(((EditText) findViewById(R.id.nombre_equipo_EditText)), errorMessage,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(((EditText) findViewById(R.id.nombre_equipo_EditText)), message,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void resetForm() {
        ((EditText) findViewById(R.id.nombre_equipo_EditText)).setText("");
        ((EditText) findViewById(R.id.tipo_equipo_EditText)).setText("");
        ((EditText) findViewById(R.id.estado_equipo_EditText)).setText("");
        ((EditText) findViewById(R.id.fechaCompra_equipo_EditText)).setText("");


        ((EditText) findViewById(R.id.nombre_equipo_EditText)).requestFocus();
    }
}
