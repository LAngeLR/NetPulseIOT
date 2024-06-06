package com.example.netpulseiot.entity;

public class EquipoItem {
    //estos 2 luego se borrarán

    //-----
    String descripcion;
    String foto;
    String marca;
    String modelo;
    String numSerie;
    String sku;
    String tipoEquipo;

    public EquipoItem() {
    }

    public EquipoItem(String descripcion, String foto, String marca, String modelo, String numSerie, String sku, String tipoEquipo) {
        this.descripcion = descripcion;
        this.foto = foto;
        this.marca = marca;
        this.modelo = modelo;
        this.numSerie = numSerie;
        this.sku = sku;
        this.tipoEquipo = tipoEquipo;
    }



    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }
}
