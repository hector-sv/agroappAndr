package com.hector.granjasandroid.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Equipo implements Serializable {

    private long id;
    private String nombre;
    private String tipo;
    private String fechaCompra;
    private String estado;

    public Equipo(){}

    public Equipo(long id, String nombre, String tipo, String fechaCompra, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fechaCompra = fechaCompra;
        this.estado = estado;
    }

    public Equipo(String nombre, String tipo, String fechaCompra, String estado) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.fechaCompra = fechaCompra;
        this.estado = estado;
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

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}
