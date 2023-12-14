package com.hector.granjasandroid.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Animal implements Serializable {

    private long id;
    private String nombre;
    private String tipo;
    private String fechaEntrada;
    private String sexo;
    //idUsuario?


    public Animal(long id, String nombre, String tipo, String fechaEntrada, String sexo) {
        this.id = id;
        this.nombre = nombre;
        this.sexo = sexo;
        this.tipo = tipo;
        this.fechaEntrada = fechaEntrada;
    }
    public Animal(String nombre, String tipo, String fechaEntrada, String sexo) {

        this.nombre = nombre;
        this.sexo = sexo;
        this.tipo = tipo;
        this.fechaEntrada = fechaEntrada;
    }

    public Animal() {

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

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


}
