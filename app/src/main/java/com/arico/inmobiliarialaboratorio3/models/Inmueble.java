package com.arico.inmobiliarialaboratorio3.models;

import android.net.Uri;

public class Inmueble implements java.io.Serializable {
    private int id;
    private String direccion;
    private short superficie;
    private int precio;
    private int propietario;
    private String tipo;
    private String uso;
    private byte ambientes;
    private byte disponible;
    private float coordenadasX;
    private float coordenadasY;
    private String urlFoto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDirección() {
        return direccion;
    }

    public void setDirección(String dirección) {
        this.direccion = dirección;
    }

    public short getSuperficie() {
        return superficie;
    }

    public void setSuperficie(short superficie) {
        this.superficie = superficie;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getPropietario() {
        return propietario;
    }

    public void setPropietario(int propietario) {
        this.propietario = propietario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public byte getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(byte ambientes) {
        this.ambientes = ambientes;
    }

    public byte getDisponible() {
        return disponible;
    }

    public void setDisponible(byte disponible) {
        this.disponible = disponible;
    }

    public float getCoordenadasX() {
        return coordenadasX;
    }

    public void setCoordenadasX(float coordenadasX) {
        this.coordenadasX = coordenadasX;
    }

    public float getCoordenadasY() {
        return coordenadasY;
    }

    public void setCoordenadasY(float coordenadasY) {
        this.coordenadasY = coordenadasY;
    }

    public String getUriFoto() {
        return urlFoto;
    }

    public void setUriFoto(String uriFoto) {
        this.urlFoto = uriFoto;
    }

    public Inmueble () {
        //Constructor vacío.
    }

    public Inmueble(int id, String dirección, short superficie, int precio, int propietario, String tipo, String uso, byte ambientes, byte disponible, float coordenadasX, float coordenadasY, String uriImagen) {
        this.id = id;
        this.direccion = dirección;
        this.superficie = superficie;
        this.precio = precio;
        this.propietario = propietario;
        this.tipo = tipo;
        this.uso = uso;
        this.ambientes = ambientes;
        this.disponible = disponible;
        this.coordenadasX = coordenadasX;
        this.coordenadasY = coordenadasY;
        this.urlFoto = uriImagen;
    }
}
