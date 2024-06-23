package com.example.netpulseiot.entity;

import com.google.firebase.Timestamp;

public class MessageItem {
    private String idMensaje;
    private String userId;
    private String contenido;
    private Timestamp hora;
    private String tipo;


    public MessageItem() {
    }

    public MessageItem(String idMensaje, String userId, String contenido, Timestamp hora, String tipo) {
        this.idMensaje = idMensaje;
        this.userId = userId;
        this.contenido = contenido;
        this.hora = hora;
        this.tipo = tipo;
    }

    public String getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(String idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Timestamp getHora() {
        return hora;
    }

    public void setHora(Timestamp hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
