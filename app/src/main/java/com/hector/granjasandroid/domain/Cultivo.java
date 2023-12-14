package com.hector.granjasandroid.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Cultivo implements Serializable {
    private long id;
    private String nombre;
    private String tipo;
    private String fechaSiembra;
    private String fechaCosecha;

    public Cultivo(long id, String nombre, String tipo, String fechaSiembra, String fechaCosecha) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.fechaSiembra = fechaSiembra;
        this.fechaCosecha = fechaCosecha;
    }


    public Cultivo(String nombre, String tipo, String fechaSiembra, String fechaCosecha) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.fechaSiembra = fechaSiembra;
        this.fechaCosecha = fechaCosecha;
    }

    public Cultivo() {

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaSiembra() {
        return fechaSiembra;
    }

    public void setFechaSiembra(String fechaSiembra) {
        this.fechaSiembra = fechaSiembra;
    }

    public String getFechaCosecha() {
        return fechaCosecha;
    }

    public void setFechaCosecha(String fechaCosecha) {
        this.fechaCosecha = fechaCosecha;
    }


}

