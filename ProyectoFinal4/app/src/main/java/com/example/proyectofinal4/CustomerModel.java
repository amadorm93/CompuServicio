package com.example.proyectofinal4;

import java.io.Serializable;

public class CustomerModel implements Serializable {
    //Es recomendable declarar aqui el id para evitar problemas
    private int id;
    //declaracion de variables
    private String name;
    private String fecha;
    private String direccion;
    private String numero;
    private String Key;
    private boolean isActive;

    //Constructores

    public CustomerModel(int id, String name, String fecha, String direccion, String numero, boolean isActive) {
        this.id = id;
        this.name = name;
        this.fecha = fecha;
        this.direccion = direccion;
        this.numero = numero;
        this.isActive = isActive;
    }

    public CustomerModel() {
    }

    //Metodo toString para poder imprimir el contenido de la clase

    @Override
    public String toString() {
        return  "id=" + id +
                ", Nombre:'" + name + '\'' +
                ", fecha:'" + fecha + '\'' +
                ", Telefono:'" + numero + '\''+
                ", direccion:'" + direccion + '\'';
    }


    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion(){return direccion;}

    public void setDireccion(String direccion){this.direccion=direccion;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String Fecha) {
        this.fecha = fecha;
    }

    public  String getNumero(){return numero;}

    public void setNumero(String numero){this.numero = numero;}

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}