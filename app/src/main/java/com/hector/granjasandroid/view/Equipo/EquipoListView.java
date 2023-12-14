package com.hector.granjasandroid.view.Equipo;

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
import com.hector.granjasandroid.adapter.EquipoAdapter;
import com.hector.granjasandroid.contract.Equipo.EquipoListContract;
import com.hector.granjasandroid.domain.Equipo;
import com.hector.granjasandroid.presenter.Equipo.EquipoListPresenter;
import com.hector.granjasandroid.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class EquipoListView extends AppCompatActivity implements EquipoListContract.View {

    private List<Equipo> equipoList;
    private EquipoAdapter adapter;
    private EquipoListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo_list_view);

        presenter = new EquipoListPresenter(this);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {

        equipoList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.equipo_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new EquipoAdapter(this, equipoList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("Equipo", "Llamada desde Equipo View");
        presenter.loadAllEquipos();

    }

    @Override
    public void showEquipos(List<Equipo> equipos) {
        equipoList.clear();
        equipoList.addAll(equipos);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_equipo, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_Equipo) {
            Intent intent = new Intent(this, AddEquipoView.class);
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

