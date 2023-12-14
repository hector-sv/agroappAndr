package com.hector.granjasandroid.view.Granja;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.hector.granjasandroid.R;
import com.hector.granjasandroid.contract.Granja.AddGranjaContract;
import com.hector.granjasandroid.domain.Granja;
import com.hector.granjasandroid.presenter.Granja.AddGranjaPresenter;
import com.hector.granjasandroid.view.MainActivity;
import com.hector.granjasandroid.view.MapsActivity;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;

public class AddGranjaView extends AppCompatActivity implements AddGranjaContract.View {

    private MapView granjaMap;
    private Point point;
    private PointAnnotationManager pointAnnotationManager;

    private AddGranjaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_granja_view);

        Log.d("add Granja", "llamada desde add Granja View");

        presenter = new AddGranjaPresenter(this);
        granjaMap = findViewById(R.id.granjaMapView);

        GesturesPlugin gesturesPlugin = GesturesUtils.getGestures(granjaMap);
        gesturesPlugin.addOnMapClickListener(point -> {
            removeAllMarkers();
            this.point = point;
            addMarker(point);
            return true;
        });

        initializePointManager();
    }


    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(granjaMap);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }


    private void addMarker(Point point) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(point)
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.red_marker_icon_foreground));
        pointAnnotationManager.create(pointAnnotationOptions);
    }


    private void removeAllMarkers() {
        pointAnnotationManager.deleteAll();
    }


    public void addButton(View view) {
        EditText etNombre = findViewById(R.id.nombre_granja_EditText);
        EditText etDireccion = findViewById(R.id.direccion_granja_EditText);
        EditText etTelefono = findViewById(R.id.telefono_granja_EditText);
        EditText etCorreo = findViewById(R.id.correo_granja_EditText);

        String nombre = etNombre.getText().toString();
        String direccion = etDireccion.getText().toString();
        String telefono = etTelefono.getText().toString();
        String correo = etCorreo.getText().toString();


        if (point == null) {
            Snackbar.make(etNombre, R.string.choose_location_message, BaseTransientBottomBar.LENGTH_LONG);
            return;
        }

        Granja granja = new Granja(nombre, direccion, telefono, correo, point.latitude(), point.longitude());
        presenter.addGranja(granja);

        finish();
    }


    public void cancelButton(View view) {
        onBackPressed();
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(((EditText) findViewById(R.id.nombre_granja_EditText)), errorMessage,
                BaseTransientBottomBar.LENGTH_LONG).show();

    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(((EditText) findViewById(R.id.nombre_granja_EditText)), message,
                BaseTransientBottomBar.LENGTH_LONG).show();

    }

    @Override
    public void resetForm() {
        ((EditText) findViewById(R.id.nombre_granja_EditText)).setText("");
        ((EditText) findViewById(R.id.direccion_granja_EditText)).setText("");
        ((EditText) findViewById(R.id.telefono_granja_EditText)).setText("");
        ((EditText) findViewById(R.id.correo_granja_EditText)).setText("");

        ((EditText) findViewById(R.id.nombre_granja_EditText)).requestFocus();

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
}