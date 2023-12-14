package com.hector.granjasandroid.view.Granja;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.hector.granjasandroid.R;
import com.hector.granjasandroid.contract.Granja.ModifyGranjaContract;
import com.hector.granjasandroid.domain.Granja;
import com.hector.granjasandroid.presenter.Granja.ModifyGranjaPresenter;
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

public class ModifyGranjaView extends AppCompatActivity implements ModifyGranjaContract.View {

    private long id;
    private Granja granja;
    private ModifyGranjaPresenter presenter;

    private MapView granjaMap;
    private Point point;
    private PointAnnotationManager pointAnnotationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_granja_view);

        noticeId();

        Bundle bundle = getIntent().getExtras();
        granja = (Granja) bundle.getSerializable("granja");
        id = granja.getId();

        fillData(granja);

        presenter = new ModifyGranjaPresenter(this);
        granjaMap = findViewById(R.id.granjaMapView);

        GesturesPlugin gesturesPlugin = GesturesUtils.getGestures(granjaMap);
        gesturesPlugin.addOnMapClickListener(point -> {
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


    public void modifyGranjaButton(View view) {
        EditText etNombre = findViewById(R.id.nombreGranjaModifyEditText);
        EditText etDireccion = findViewById(R.id.direccionGranjaModifyEditText);
        EditText etTelefono = findViewById(R.id.telefonoGranjaModifyEditText);
        EditText etCorreo = findViewById(R.id.correoGranjaModifyEditText);

        String nombre = etNombre.getText().toString();
        String direccion = etDireccion.getText().toString();
        String telefono = etTelefono.getText().toString();
        String correo = etCorreo.getText().toString();

        if (point == null) {
            Snackbar.make(etNombre, R.string.choose_location_message, BaseTransientBottomBar.LENGTH_LONG);
            return;
        }

        Granja modifiedGranja = new Granja(nombre, direccion, telefono, correo, point.latitude(), point.longitude());
        presenter.modifyGranja(id, modifiedGranja);

        finish();
    }


    public void cancelModifyButton(View view) {
        onBackPressed();
    }

    private void fillData(Granja granja) {
        EditText etNombre = findViewById(R.id.nombreGranjaModifyEditText);
        EditText etDireccion = findViewById(R.id.direccionGranjaModifyEditText);
        EditText etTelefono = findViewById(R.id.telefonoGranjaModifyEditText);
        EditText etCorreo = findViewById(R.id.correoGranjaModifyEditText);

        etNombre.setText(granja.getNombre());
        etDireccion.setText(granja.getDireccion());
        etTelefono.setText(granja.getTelefono());
        etCorreo.setText(granja.getCorreo());

    }

    private void noticeId() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.are_you_sure_modify_granja_message)
                .setTitle(R.string.modify_granja_title)
                .setNegativeButton("No", (dialog, id) -> { //boton de si

                    Intent intent = new Intent(this, GranjaListView.class);
                    intent.putExtra("id", granja.getId());
                    this.startActivity(intent);
                })
                .setPositiveButton("Yes", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void showMessage(String s) {

    }

    public void showError(String s) {

    }
}