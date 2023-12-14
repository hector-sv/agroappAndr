package com.hector.granjasandroid.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Granja implements Serializable {
    private long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private double latitude;
    private double longitud;

    public Granja(String nombre, String direccion, String telefono, String correo, double latitude, double longitud) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.latitude = latitude;
        this.longitud = longitud;
    }

    public Granja(String nombre, String direccion, String telefono, String correo) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }


}
