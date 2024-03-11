package com.quindio.laboratorio.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Producto {
    private StringProperty codigo;
    private StringProperty nombre;
    private StringProperty cantidad;
    private StringProperty valor;
    private Object cliente;


    public Producto(String nombre, String codigo, String cantidad, String valor) {
        this.nombre = new SimpleStringProperty(nombre);
        this.codigo = new SimpleStringProperty(codigo);
        this.cantidad = new SimpleStringProperty(cantidad);
        this.valor = new SimpleStringProperty(valor);
    }

    // Getters y setters para las propiedades
    public String getNombre() {
        return nombre.get();
    }

    public String getCantidad() {
        return cantidad.get();
    }

    public void setCantidad(String cantidad) {
        this.cantidad.set(cantidad);
    }

    public StringProperty cantidadProperty() {
        return cantidad;
    }

    public String getCodigo() {
        return codigo.get();
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    public StringProperty codigoProperty() {
        return codigo;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }


    public StringProperty valorProperty() {
        return valor;
    }
    public String getValor() {
        return valor.get();
    }


    public Object getCliente() {
        return cliente;
    }

    public void setCliente(Object cliente) {
        this.cliente = cliente;
    }
}