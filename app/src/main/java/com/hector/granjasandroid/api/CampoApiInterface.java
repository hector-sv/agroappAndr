package com.hector.granjasandroid.api;

import com.hector.granjasandroid.domain.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CampoApiInterface {

//ANIMALES
    @GET("/animales")
    Call<List<Animal>> getAnimales();

    @POST("/animales")
    Call<Animal> addAnimal(@Body Animal animal);

    @DELETE("/animales/{id}")
    Call<Void> deleteAnimales(@Path("id") long id);

    @PUT("/animales/{id}")
    Call<Animal> modifyAnimal(@Path("id") long id, @Body Animal animal);

//CULTIVOS

    @GET("/cultivos")
    Call<List<Cultivo>> getCultivos();

    @POST("/cultivos")
    Call<Cultivo> addCultivo(@Body Cultivo cultivo);

    @DELETE("/cultivos/{id}")
    Call<Void> deleteCultivos(@Path("id") long id);

    @PUT("/cultivos/{id}")
    Call<Cultivo> modifyCultivo(@Path("id") long id, @Body Cultivo cultivo);

//EMPLEADOS
//
    @GET("/empleados")
    Call<List<Empleado>> getEmpleados();

    @POST("/empleados")
    Call<Empleado> addEmpleado(@Body Empleado empleado);

    @DELETE("/empleados/{id}")
    Call<Void> deleteEmpleados(@Path("id") long id);

    @PUT("/empleados/{id}")
    Call<Empleado> modifyEmpleado(@Path("id") long id, @Body Empleado empleado);

//EQUIPOS

    @GET("/equipos")
    Call<List<Equipo>> getEquipos();

    @POST("/equipos")
    Call<Equipo> addEquipo(@Body Equipo equipo);

    @DELETE("/equipos/{id}")
    Call<Void> deleteEquipos(@Path("id") long id);

    @PUT("/equipos/{id}")
    Call<Equipo> modifyEquipo(@Path("id") long id, @Body Equipo equipo);

//GRANJAS

    @GET("/granjas")
    Call<List<Granja>> getGranjas();

    @POST("/granjas")
    Call<Granja> addGranja(@Body Granja granja);

    @DELETE("/granjas/{id}")
    Call<Void> deleteGranjas(@Path("id") long id);

    @PUT("/granjas/{id}")
    Call<Granja> modifyGranja(@Path("id") long id, @Body Granja granja);


}