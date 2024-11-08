package com.arico.inmobiliarialaboratorio3.models;

import java.util.List;

public class Contrato implements java.io.Serializable {
    private int idContrato;
    private int idInquilino;
    private int idInmueble;
    private String fechaLímite;
    private String fechaContrato;
    private byte vigente;
    private int monto;
    private String nombreInquilino;
    private String direccionInmueble;
    //private List<Pago> listaPagos;

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getFechaLímite() {
        return fechaLímite;
    }

    public void setFechaLímite(String fechaLímite) {
        this.fechaLímite = fechaLímite;
    }

    public String getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(String fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public byte getVigente() {
        return vigente;
    }

    public void setVigente(byte vigente) {
        this.vigente = vigente;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getNombreInquilino() {
        return nombreInquilino;
    }

    public void setNombreInquilino(String nombreInquilino) {
        this.nombreInquilino = nombreInquilino;
    }

    public String getDireccionInmueble() {
        return direccionInmueble;
    }

    public void setDireccionInmueble(String direccionInmueble) {
        this.direccionInmueble = direccionInmueble;
    }

    /*public List<Pago> getListaPagos() {
        return listaPagos;
    }

    public void setListaPagos(List<Pago> listaPagos) {
        this.listaPagos = listaPagos;
    }*/
}
