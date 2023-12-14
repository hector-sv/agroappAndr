package com.hector.granjasandroid.view.Cultivo;

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
import com.hector.granjasandroid.adapter.CultivoAdapter;
import com.hector.granjasandroid.contract.Cultivo.CultivoListContract;
import com.hector.granjasandroid.domain.Cultivo;
import com.hector.granjasandroid.presenter.Cultivo.CultivoListPresenter;
import com.hector.granjasandroid.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class CultivoListView extends AppCompatActivity implements CultivoListContract.View {

    private List<Cultivo> cultivoList;
    private CultivoAdapter adapter;
    private CultivoListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivo_list_view);

        presenter = new CultivoListPresenter(this);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {

        cultivoList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.cultivo_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CultivoAdapter(this, cultivoList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("Cultivo", "Llamada desde Cultivo View");
        presenter.loadAllCultivos();

    }

    @Override
    public void showCultivos(List<Cultivo> cultivos) {
        cultivoList.clear();
        cultivoList.addAll(cultivos);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_cultivo, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_Cultivo) {
            Intent intent = new Intent(this, AddCultivoView.class);
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

