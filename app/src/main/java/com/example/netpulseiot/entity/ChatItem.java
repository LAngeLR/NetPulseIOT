package com.example.netpulseiot.entity;

import com.google.firebase.Timestamp;

import java.util.List;

public class ChatItem {
    private String idChat;
    private List<String> participantes;
    private String mensajeUltimo;
    private Timestamp tiempoMensaje;
    private String idUsuarioUltimo;

    // Constructor vacío requerido para Firestore
    public ChatItem() {
    }

    public ChatItem(String idChat, List<String> participantes, String mensajeUltimo, Timestamp tiempoMensaje, String idUsuarioUltimo) {
        this.idChat = idChat;
        this.participantes = participantes;
        this.mensajeUltimo = mensajeUltimo;
        this.tiempoMensaje = tiempoMensaje;
        this.idUsuarioUltimo = idUsuarioUltimo;
    }

    public String getIdChat() {
        return idChat;
    }

    public void setIdChat(String idChat) {
        this.idChat = idChat;
    }

    public List<String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<String> participantes) {
        this.participantes = participantes;
    }

    public String getMensajeUltimo() {
        return mensajeUltimo;
    }

    public void setMensajeUltimo(String mensajeUltimo) {
        this.mensajeUltimo = mensajeUltimo;
    }

    public Timestamp getTiempoMensaje() {
        return tiempoMensaje;
    }

    public void setTiempoMensaje(Timestamp tiempoMensaje) {
        this.tiempoMensaje = tiempoMensaje;
    }

    public String getIdUsuarioUltimo() {
        return idUsuarioUltimo;
    }

    public void setIdUsuarioUltimo(String idUsuarioUltimo) {
        this.idUsuarioUltimo = idUsuarioUltimo;
    }
}
