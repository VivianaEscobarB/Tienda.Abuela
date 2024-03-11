package com.quindio.laboratorio.Model;

public class Venta {
    private int codigo;
    private Cliente cliente;
    private Producto producto;


    public Venta(int codigo, Cliente cliente, Producto producto) {
        this.producto = producto;
        this.codigo = codigo;
        this.cliente = cliente;
    }
    public Venta() {
    }

    public int getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public Producto venderProducto(Producto producto, String cantidadStr) {
        int cantidad = Integer.parseInt(producto.getCantidad()); // Convert to int
        int nuevaCantidad = cantidad - Integer.parseInt(cantidadStr); // Subtract
        producto.setCantidad(Integer.toString(nuevaCantidad)); // Set new quantity (String)
        return producto;
    }



}