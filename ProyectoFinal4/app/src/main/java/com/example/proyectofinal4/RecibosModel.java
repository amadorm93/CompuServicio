package com.example.proyectofinal4;

import java.io.Serializable;

public class RecibosModel implements Serializable {
    private int id;
    //declaracion de variables
    private String name;
    private String fecha;
    private String mes;
    private String monto;
    private String Key;

    public RecibosModel(int id, String name, String fecha, String monto, String mes) {
        this.id = id;
        this.setName(name);
        this.setFecha(fecha);
        this.setMonto(monto);
        this.setMes(mes);
    }
    public RecibosModel(){

    }

    @Override
    public String toString() {
        return "RecibosModel{" +
                "id=" + id +
                ", Nombre:'" + name + '\'' +
                ", Fecha:'" + fecha + '\'' +
                ", Monto:" + monto +
                ", Mes:'" + mes + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
