package com.example.netpulseiot.entity;

public class SuperadminUsuarioItem {

    String name;
    String cargo;
    int image;

    public SuperadminUsuarioItem(String name, String cargo, int image) {
        this.name = name;
        this.cargo = cargo;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
