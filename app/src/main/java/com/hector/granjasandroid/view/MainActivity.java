package com.hector.granjasandroid.view;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.hector.granjasandroid.R;
import com.hector.granjasandroid.view.Animal.AnimalListView;
import com.hector.granjasandroid.view.Cultivo.CultivoListView;
import com.hector.granjasandroid.view.Empleado.EmpleadoListView;
import com.hector.granjasandroid.view.Equipo.EquipoListView;
import com.hector.granjasandroid.view.Granja.GranjaListView;

public class MainActivity extends AppCompatActivity {

    Button listAnimal;
    Button listCultivo;
    Button listGranja;
    Button listEquipo;
    Button listEmpleado;

    //faltan empleado equipo granja y usuairo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listAnimal = findViewById(R.id.animal_list);
        listAnimal.setOnClickListener(view -> {
            Intent intent = new Intent(this, AnimalListView.class);
            startActivity(intent);
        });

        listCultivo = findViewById(R.id.cultivo_list);
        listCultivo.setOnClickListener(view -> {
            Intent intent = new Intent(this, CultivoListView.class);
            startActivity(intent);
        });

        listGranja = findViewById(R.id.granja_list);
        listGranja.setOnClickListener(view -> {
            Intent intent = new Intent(this, GranjaListView.class);
            startActivity(intent);
        });

        listEquipo = findViewById(R.id.equipo_list);
        listEquipo.setOnClickListener(view -> {
            Intent intent = new Intent(this, EquipoListView.class);
            startActivity(intent);
        });

        listEmpleado = findViewById(R.id.empleado_list);
        listEmpleado.setOnClickListener(view -> {
            Intent intent = new Intent(this, EmpleadoListView.class);
            startActivity(intent);
        });
    }
}
