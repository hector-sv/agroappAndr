package com.hector.granjasandroid.domain;

import java.io.Serializable;

public class Empleado implements Serializable {

    private long id;
    private String nombre;
    private String apellido;
    private String cargo;
    private String correo;
    private float salario;
    private String fechaContrato;

    public Empleado(long id, String nombre, String apellido, String cargo, String correo, float salario, String fechaContrato) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.correo = correo;
        this.salario = salario;
        this.fechaContrato = fechaContrato;
    }
    public Empleado( String nombre, String apellido, String cargo, String correo, float salario, String fechaContrato) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.correo = correo;
        this.salario = salario;
        this.fechaContrato = fechaContrato;
    }
    public Empleado(){
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(String fechaContrato) {
        this.fechaContrato = fechaContrato;
    }



}
