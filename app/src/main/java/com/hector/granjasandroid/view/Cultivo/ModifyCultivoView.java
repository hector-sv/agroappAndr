package com.hector.granjasandroid.view.Cultivo;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hector.granjasandroid.R;
import com.hector.granjasandroid.contract.Cultivo.ModifyCultivoContract;
import com.hector.granjasandroid.domain.Cultivo;
import com.hector.granjasandroid.presenter.Cultivo.ModifyCultivoPresenter;

import java.time.LocalDate;

public class ModifyCultivoView extends AppCompatActivity implements ModifyCultivoContract.View {

    private long id;
    private Cultivo cultivo;
    private ModifyCultivoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_cultivo_view);

        noticeId();

        Bundle bundle = getIntent().getExtras();
        cultivo = (Cultivo) bundle.getSerializable("cultivo");
        id = cultivo.getId();

        fillData(cultivo);

        presenter = new ModifyCultivoPresenter(this);
    }


    public void modifyButton(View view) {
        EditText etNombre = findViewById(R.id.nombre_cultivo_ModifyEditText);
        EditText etTipo = findViewById(R.id.tipo_cultivo_ModifyEditText);
        EditText etFechaSiembra = findViewById(R.id.fechaSiembra_cultivo_ModifyEditText);
        EditText etFechaCosecha = findViewById(R.id.fechaCosecha_cultivo_ModifyEditText);


        String nombre = etNombre.getText().toString();
        String tipo = etTipo.getText().toString();
        String fechaSiembra = etFechaSiembra.getText().toString();
        String fechaCosecha = etFechaCosecha.getText().toString();


        Cultivo modifiedCultivo = new Cultivo(nombre, tipo,  fechaSiembra, fechaCosecha);
        presenter.modifyCultivo(id, modifiedCultivo);

        finish();

    }


    public void cancelModifyButton(View view) {
        onBackPressed();
    }


    private void fillData(Cultivo cultivo) {
        EditText etNombre = findViewById(R.id.nombre_cultivo_ModifyEditText);
        EditText etTipo = findViewById(R.id.tipo_cultivo_ModifyEditText);
        EditText etFechaSiembra = findViewById(R.id.fechaSiembra_cultivo_ModifyEditText);
        EditText etFechaCosecha = findViewById(R.id.fechaCosecha_cultivo_ModifyEditText);

        etNombre.setText(cultivo.getNombre());
        etTipo.setText(cultivo.getTipo());
        etFechaSiembra.setText(cultivo.getFechaSiembra());
        etFechaCosecha.setText(cultivo.getFechaCosecha());

    }


    private void noticeId() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.are_you_sure_modify_cultivo_message)
                .setTitle(R.string.modify_cultivo_name)
                .setNegativeButton("No", (dialog, id) -> {

                    Intent intent = new Intent(this, CultivoListView.class);
                    intent.putExtra("id", cultivo.getId());
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

