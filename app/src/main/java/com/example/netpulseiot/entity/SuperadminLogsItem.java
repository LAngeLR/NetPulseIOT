package com.example.netpulseiot.entity;

public class SuperadminLogsItem {

    String codigo;
    String descripcion;
    int image;
    String fecha;
    String hora;

    public SuperadminLogsItem(String codigo, String descripcion, int image, String fecha, String hora) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.image = image;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
