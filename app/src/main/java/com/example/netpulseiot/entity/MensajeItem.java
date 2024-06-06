package com.example.netpulseiot.entity;

public class MensajeItem {
    String name;
    String mensaje;
    int image;
    String contador;

    public MensajeItem(String name, String mensaje, int image, String contador) {
        this.name = name;
        this.mensaje = mensaje;
        this.image = image;
        this.contador = contador;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getContador() {
        return contador;
    }

    public void setContador(String contador) {
        this.contador = contador;
    }
}
