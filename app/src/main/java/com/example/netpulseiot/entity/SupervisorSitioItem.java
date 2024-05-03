package com.example.netpulseiot.entity;

public class SupervisorSitioItem {
    String nombre;
    String provincia;
    String distrito;
    String tipo;
    int image;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public SupervisorSitioItem(String nombre, String provincia, String distrito, String tipo, int image) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.distrito = distrito;
        this.tipo = tipo;
        this.image = image;
    }
}
