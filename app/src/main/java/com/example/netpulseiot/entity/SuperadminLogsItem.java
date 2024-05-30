package com.example.netpulseiot.entity;

public class SuperadminLogsItem {

    String codigo;
    String descripcion;
    int image;
    String fecha;
    String hora;

    /** SE AÑADIO PARA PROBAR  **/

    String nombre;
    String apellido;
    String cargo;
    String direccion;

//    public SuperadminLogsItem(String codigo, String descripcion, int image, String fecha, String hora) {
//        this.codigo = codigo;
//        this.descripcion = descripcion;
//        this.image = image;
//        this.fecha = fecha;
//        this.hora = hora;
//    }


    public SuperadminLogsItem() {
    }

    public SuperadminLogsItem(String codigo, String descripcion, int image, String fecha, String hora, String nombre, String apellido, String cargo, String direccion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.image = image;
        this.fecha = fecha;
        this.hora = hora;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.direccion = direccion;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
