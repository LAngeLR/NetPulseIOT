package com.example.netpulseiot.entity;

import com.google.firebase.firestore.GeoPoint;

public class SitioItem {

    String id;
    String nombre;
    String departamento;
    String provincia;
    String distrito;
    String tipoSitio;
    String tipoZona;
    String ubigeo;
    GeoPoint geolocalizacion;
    String foto;
    String supervisor;

    public SitioItem() {
    }

    public SitioItem(String id, String nombre, String departamento, String provincia, String distrito, String tipoSitio, String tipoZona, String ubigeo, GeoPoint geolocalizacion, String foto, String supervisor) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.tipoSitio = tipoSitio;
        this.tipoZona = tipoZona;
        this.ubigeo = ubigeo;
        this.geolocalizacion = geolocalizacion;
        this.foto = foto;
        this.supervisor = supervisor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
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

    public String getTipoSitio() {
        return tipoSitio;
    }

    public void setTipoSitio(String tipoSitio) {
        this.tipoSitio = tipoSitio;
    }

    public String getTipoZona() {
        return tipoZona;
    }

    public void setTipoZona(String tipoZona) {
        this.tipoZona = tipoZona;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public GeoPoint getGeolocalizacion() {
        return geolocalizacion;
    }

    public void setGeolocalizacion(GeoPoint geolocalizacion) {
        this.geolocalizacion = geolocalizacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
}
