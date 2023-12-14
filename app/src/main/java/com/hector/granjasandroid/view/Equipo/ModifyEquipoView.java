package com.hector.granjasandroid.view.Equipo;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hector.granjasandroid.R;
import com.hector.granjasandroid.contract.Equipo.ModifyEquipoContract;
import com.hector.granjasandroid.domain.Equipo;
import com.hector.granjasandroid.presenter.Equipo.ModifyEquipoPresenter;

import java.time.LocalDate;

public class ModifyEquipoView extends AppCompatActivity implements ModifyEquipoContract.View {

    private long id;
    private Equipo equipo;
    private ModifyEquipoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_equipo_view);

        noticeId();

        Bundle bundle = getIntent().getExtras();
        equipo = (Equipo) bundle.getSerializable("equipo");
        id = equipo.getId();

        fillData(equipo);

        presenter = new ModifyEquipoPresenter(this);
    }


    public void modifyButton(View view) {
        EditText etNombre = findViewById(R.id.nombre_equipo_ModifyEditText);
        EditText etTipo = findViewById(R.id.tipo_equipo_ModifyEditText);
        EditText etEstado = findViewById(R.id.estado_equipo_ModifyEditText);
        EditText etFechaCompra = findViewById(R.id.fechaCompra_equipo_ModifyEditText);


        String nombre = etNombre.getText().toString();
        String tipo = etTipo.getText().toString();
        String estado = etEstado.getText().toString();
        String fechaCompra = etFechaCompra.getText().toString();


        Equipo modifiedEquipo = new Equipo(nombre, tipo, fechaCompra, estado);
        presenter.modifyEquipo(id, modifiedEquipo);

        finish();

    }


    public void cancelModifyButton(View view) {
        onBackPressed();
    }


    private void fillData(Equipo equipo) {
        EditText etNombre = findViewById(R.id.nombre_equipo_ModifyEditText);
        EditText etTipo = findViewById(R.id.tipo_equipo_ModifyEditText);
        EditText etEstado = findViewById(R.id.estado_equipo_ModifyEditText);
        EditText etFechaCompra = findViewById(R.id.fechaCompra_equipo_ModifyEditText);

        etNombre.setText(equipo.getNombre());
        etTipo.setText(equipo.getTipo());
        etEstado.setText(equipo.getEstado());
        etFechaCompra.setText(equipo.getEstado());


    }

    private void noticeId() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.are_you_sure_modify_equipo_message)
                .setTitle(R.string.modify_equipo_name)
                .setNegativeButton("No", (dialog, id) -> {

                    Intent intent = new Intent(this, EquipoListView.class);
                    intent.putExtra("id", equipo.getId());
                    this.startActivity(intent);
                })
                .setPositiveButton("Yes", (dialog, id) -> dialog.dismiss());
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

