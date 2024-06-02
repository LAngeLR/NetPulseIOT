package com.example.netpulseiot.entity;

public class SuperadminUsuarioItem {
    String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
