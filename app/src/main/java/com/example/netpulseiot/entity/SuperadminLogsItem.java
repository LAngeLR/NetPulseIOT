package com.example.netpulseiot.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class SuperadminLogsItem {

    String usuario;
    String accion;
    String fecha;
    String hora;

    java.util.Date fechaCreacion;

    public SuperadminLogsItem() {
    }

    public SuperadminLogsItem(String usuario, String accion, String fecha, String hora, Date fechaCreacion) {
        this.usuario = usuario;
        this.accion = accion;
        this.fecha = fecha;
        this.hora = hora;
        this.fechaCreacion = fechaCreacion;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
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

    public java.util.Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(java.util.Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
