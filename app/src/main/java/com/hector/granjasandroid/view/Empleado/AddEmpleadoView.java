package com.hector.granjasandroid.view.Empleado;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.hector.granjasandroid.R;
import com.hector.granjasandroid.contract.Empleado.AddEmpleadoContract;
import com.hector.granjasandroid.contract.Equipo.AddEquipoContract;
import com.hector.granjasandroid.domain.Empleado;
import com.hector.granjasandroid.domain.Equipo;
import com.hector.granjasandroid.presenter.Empleado.AddEmpleadoPresenter;
import com.hector.granjasandroid.presenter.Equipo.AddEquipoPresenter;

public class AddEmpleadoView extends AppCompatActivity implements AddEmpleadoContract.View {
    private AddEmpleadoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_empleado_view);

        Log.d("add Empleado", "llamada desde add Empleado View");

        presenter = new AddEmpleadoPresenter(this);
    }


    public void addButton(View view) {
        EditText etNombre = findViewById(R.id.nombre_empleado_EditText);
        EditText etApellido = findViewById(R.id.apellido_empleado_EditText);
        EditText etCargo = findViewById(R.id.cargo_empleado_EditText);
        EditText etCorreo = findViewById(R.id.correo_empleado_EditText);
        EditText etSalario = findViewById(R.id.salario_empleado_EditText);
        EditText etFechaContrato = findViewById(R.id.fechaContrato_empleado_EditText);

        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String cargo = etCargo.getText().toString();
        String correo = etCorreo.getText().toString();
        float salario = Float.parseFloat(etSalario.getText().toString());
        String fechaContrato = etFechaContrato.getText().toString();

        Empleado empleado = new Empleado(nombre,  apellido,  cargo,  correo,  salario,  fechaContrato);
        presenter.addEmpleado(empleado);

        finish();
    }


    public void cancelButton(View view) {
        onBackPressed();
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(((EditText) findViewById(R.id.nombre_empleado_EditText)), errorMessage,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(((EditText) findViewById(R.id.nombre_empleado_EditText)), message,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void resetForm() {
        ((EditText) findViewById(R.id.nombre_empleado_EditText)).setText("");
        ((EditText) findViewById(R.id.apellido_empleado_EditText)).setText("");
        ((EditText) findViewById(R.id.cargo_empleado_EditText)).setText("");
        ((EditText) findViewById(R.id.correo_empleado_EditText)).setText("");
        ((EditText) findViewById(R.id.salario_empleado_EditText)).setText("");
        ((EditText) findViewById(R.id.fechaContrato_empleado_EditText)).setText("");



        ((EditText) findViewById(R.id.nombre_empleado_EditText)).requestFocus();
    }
}
