package com.hector.granjasandroid.view.Empleado;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hector.granjasandroid.R;
import com.hector.granjasandroid.adapter.EmpleadoAdapter;
import com.hector.granjasandroid.contract.Empleado.EmpleadoListContract;
import com.hector.granjasandroid.domain.Empleado;
import com.hector.granjasandroid.presenter.Empleado.EmpleadoListPresenter;
import com.hector.granjasandroid.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoListView extends AppCompatActivity implements EmpleadoListContract.View {

    private List<Empleado> empleadoList;
    private EmpleadoAdapter adapter;
    private EmpleadoListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado_list_view);

        presenter = new EmpleadoListPresenter(this);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {

        empleadoList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.empleado_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new EmpleadoAdapter(this, empleadoList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("Empleado", "Llamada desde Empleado View");
        presenter.loadAllEmpleados();

    }

    @Override
    public void showEmpleados(List<Empleado> empleados) {
        empleadoList.clear();
        empleadoList.addAll(empleados);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_empleado, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_Empleado) {
            Intent intent = new Intent(this, AddEmpleadoView.class);
            startActivity(intent);
            return true;

        } else if (item.getItemId() == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}

