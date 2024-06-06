package com.example.netpulseiot.entity;

public class EquipoItem {
    String nombre;
    int image;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public EquipoItem(String nombre, int image) {
        this.nombre = nombre;
        this.image = image;
    }
}
