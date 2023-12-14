package com.hector.granjasandroid.view;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.hector.granjasandroid.R;
import com.hector.granjasandroid.contract.Granja.GranjaListContract;
import com.hector.granjasandroid.domain.Granja;
import com.hector.granjasandroid.presenter.Granja.GranjaListPresenter;
import com.hector.granjasandroid.view.Granja.AddGranjaView;
import com.hector.granjasandroid.view.Granja.GranjaListView;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;


import java.util.List;

public class MapsActivity extends AppCompatActivity implements GranjaListContract.View {

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;
    private GranjaListPresenter granjaListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_view);

        mapView = findViewById(R.id.mapView);
        initializePointManager();

        granjaListPresenter = new GranjaListPresenter(this);
        granjaListPresenter.loadAllGranjas();
    }

    //iniciar pointManager
    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }


    private void addMarker(Point point, String title) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(point)
                .withTextField(title)
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.red_marker_icon_foreground));
        pointAnnotationManager.create(pointAnnotationOptions);
    }


    private void setCameraPosition(Point point) {
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(point)
                .pitch(0.0)
                .zoom(13.5)
                .bearing(-17.6)
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);
    }


    private void addGranjaToMap(List<Granja> granjas) {
        for (Granja granja : granjas) {
            Point point = Point.fromLngLat(granja.getLongitud(), granja.getLatitude());
            addMarker(point, granja.getNombre());
        }
        Granja lastGranja = granjas.get(granjas.size() - 1); //ultima ubicacion
        setCameraPosition(Point.fromLngLat(lastGranja.getLongitud(), lastGranja.getLatitude())); //fijamos la camara en la ultima ubicacion
    }

    @Override
    public void showGranjas(List<Granja> granjas) {
        addGranjaToMap(granjas);

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_maps, menu);
        return true;
    }

    //eleccion en el actionBar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_Granja) {
            Intent intent = new Intent(this, AddGranjaView.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.return_maps) {
            Intent intent = new Intent(this, GranjaListView.class);
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