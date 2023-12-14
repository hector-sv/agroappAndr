package com.hector.granjasandroid.view.Animal;

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
import com.hector.granjasandroid.adapter.AnimalAdapter;
import com.hector.granjasandroid.contract.Animal.AnimalListContract;
import com.hector.granjasandroid.domain.Animal;
import com.hector.granjasandroid.presenter.Animal.AnimalListPresenter;
import com.hector.granjasandroid.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class AnimalListView extends AppCompatActivity implements AnimalListContract.View {

    private List<Animal> animalList;
    private AnimalAdapter adapter;
    private AnimalListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_list_view);

        presenter = new AnimalListPresenter(this);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {

        animalList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.animal_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AnimalAdapter(this, animalList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("Animal", "Llamada desde Animal View");
        presenter.loadAllAnimales();

    }

    @Override
    public void showAnimales(List<Animal> animales) {
        animalList.clear();
        animalList.addAll(animales);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_animal, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_Animal) {
            Intent intent = new Intent(this, AddAnimalView.class);
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

