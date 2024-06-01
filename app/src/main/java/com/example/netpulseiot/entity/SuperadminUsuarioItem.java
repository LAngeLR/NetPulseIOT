package com.example.netpulseiot.entity;

public class SuperadminUsuarioItem {
    String idDocumento;
    String nombre;
    String apellido;
    String rol;
    private boolean checked;

    public SuperadminUsuarioItem() {
    }

    public SuperadminUsuarioItem(String nombre, String apellido, String rol, boolean checked) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.checked = checked;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    // SE AÑADIO ESTO PARA LOS ID'S NI IDEA
    public String setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
        return idDocumento;
    }
}
