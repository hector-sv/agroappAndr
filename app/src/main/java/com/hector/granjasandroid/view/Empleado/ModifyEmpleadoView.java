package com.hector.granjasandroid.view.Empleado;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hector.granjasandroid.R;
import com.hector.granjasandroid.contract.Animal.ModifyAnimalContract;
import com.hector.granjasandroid.contract.Empleado.ModifyEmpleadoContract;
import com.hector.granjasandroid.domain.Animal;
import com.hector.granjasandroid.domain.Empleado;
import com.hector.granjasandroid.presenter.Animal.ModifyAnimalPresenter;
import com.hector.granjasandroid.presenter.Empleado.ModifyEmpleadoPresenter;
import com.hector.granjasandroid.view.Animal.AnimalListView;

public class ModifyEmpleadoView extends AppCompatActivity implements ModifyEmpleadoContract.View {

    private long id;
    private Empleado empleado;
    private ModifyEmpleadoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_empleado_view);

        noticeId();

        Bundle bundle = getIntent().getExtras();
        empleado = (Empleado) bundle.getSerializable("empleado");
        id = empleado.getId();

        fillData(empleado);

        presenter = new ModifyEmpleadoPresenter(this);
    }


    public void modifyButton(View view) {
        EditText etNombre = findViewById(R.id.nombre_empleado_ModifyEditText);
        EditText etApellido = findViewById(R.id.apellido_empleado_ModifyEditText);
        EditText etCargo = findViewById(R.id.cargo_empleado_ModifyEditText);
        EditText etCorreo = findViewById(R.id.correo_empleado_ModifyEditText);
        EditText etSalario = findViewById(R.id.salario_empleado_ModifyEditText);
        EditText etFechaContrato = findViewById(R.id.fechaContrato_empleado_ModifyEditText);

        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String cargo = etCargo.getText().toString();
        String correo = etCorreo.getText().toString();
        float salario = Float.parseFloat(etSalario.getText().toString());
        String fechaContrato = etFechaContrato.getText().toString();



        Empleado modifiedEmpleado = new Empleado(nombre,  apellido,  cargo,  correo,  salario,  fechaContrato);
        presenter.modifyEmpleado(id, modifiedEmpleado);

        finish();

    }


    public void cancelModifyButton(View view) {
        onBackPressed();
    }


    private void fillData(Empleado empleado) {
        EditText etNombre = findViewById(R.id.nombre_empleado_ModifyEditText);
        EditText etApellido = findViewById(R.id.apellido_empleado_ModifyEditText);
        EditText etCargo = findViewById(R.id.cargo_empleado_ModifyEditText);
        EditText etCorreo = findViewById(R.id.correo_empleado_ModifyEditText);
        EditText etSalario = findViewById(R.id.salario_empleado_ModifyEditText);
        EditText etFechaContrato = findViewById(R.id.fechaContrato_empleado_ModifyEditText);

        etNombre.setText(empleado.getNombre());
        etApellido.setText(empleado.getApellido());
        etCargo.setText(empleado.getCargo());
        etCorreo.setText(empleado.getCorreo());
        etSalario.setText(String.valueOf(empleado.getSalario()));
        etFechaContrato.setText(empleado.getFechaContrato());



    }

    private void noticeId() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.are_you_sure_modify_empleado_message)
                .setTitle(R.string.modify_empleado_name)
                .setNegativeButton("No", (dialog, id) -> {

                    Intent intent = new Intent(this, EmpleadoListView.class);
                    intent.putExtra("id", empleado.getId());
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

