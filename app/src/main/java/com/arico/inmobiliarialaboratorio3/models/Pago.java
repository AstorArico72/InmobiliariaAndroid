package com.arico.inmobiliarialaboratorio3.models;

public class Pago implements java.io.Serializable{
    private int id;
    private int numeroPago;
    private int idContrato; //Ése parámetro es redundante.
    private int monto;
    private String fechaPago;
    private byte pagado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroPago() {
        return numeroPago;
    }

    public void setNumeroPago(int numeroPago) {
        this.numeroPago = numeroPago;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public byte getPagado() {
        return pagado;
    }

    public void setPagado(byte pagado) {
        this.pagado = pagado;
    }
}
