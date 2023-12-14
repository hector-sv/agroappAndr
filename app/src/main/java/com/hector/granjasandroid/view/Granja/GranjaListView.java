package com.hector.granjasandroid.view.Granja;

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
import com.hector.granjasandroid.adapter.GranjaAdapter;
import com.hector.granjasandroid.contract.Animal.AnimalListContract;
import com.hector.granjasandroid.contract.Granja.GranjaListContract;
import com.hector.granjasandroid.domain.Granja;
import com.hector.granjasandroid.presenter.Granja.GranjaListPresenter;
import com.hector.granjasandroid.view.MainActivity;
import com.hector.granjasandroid.view.MapsActivity;

import java.util.ArrayList;
import java.util.List;

public class GranjaListView extends AppCompatActivity implements GranjaListContract.View {

    private List<Granja> granjaList;
    private GranjaAdapter adapter;
    private GranjaListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_granja_list_view);

        presenter = new GranjaListPresenter(this);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {

        granjaList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.granja_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new GranjaAdapter(this, granjaList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("Granja", "Llamada desde Granja View");
        presenter.loadAllGranjas();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_granja, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_granja) {
            Intent intent = new Intent(this, AddGranjaView.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.view_map) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    public void showGranjas(List<Granja> granjas) {
        granjaList.clear();
        granjaList.addAll(granjas);
        adapter.notifyDataSetChanged();
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}