package com.arico.inmobiliarialaboratorio3.models;

import androidx.annotation.Nullable;

public class Propietario implements java.io.Serializable {
    private int id;
    private String correo;
    private String nombre;
    @Nullable
    private String contacto;
    private String clave;
    private String dni;
    private String rol;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Nullable
    public String getContacto() {
        return contacto;
    }

    public void setContacto(@Nullable String contacto) {
        this.contacto = contacto;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
