package com.quindio.laboratorio.Model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Gestion {
    private HashMap <Integer, Venta> ventas;
    private HashMap <Integer, DetalleVenta> detalleVentas;
    private  HashSet<Producto> listaProductos;
    private Set<Cliente> clientes;
    private Almacen almacen;



    public Gestion(){
        ventas = new HashMap<>();
        detalleVentas = new HashMap<>();
        listaProductos = new HashSet<>();
        clientes = new HashSet<>();
        almacen = new Almacen("Abuela");

        listaProductos.add(new Producto("Leche bolsa,","111", "5","10000"));
        listaProductos.add(new Producto("Cubeta huevos", "222", "6","5000"));
        listaProductos.add(new Producto("Tomate", "445y", "7","60000"));
        listaProductos.add(new Producto("Salchichon", "P468", "8","4000"));
        listaProductos.add(new Producto("Cereal", "864", "5","2000"));
        listaProductos.add(new Producto("Pollo", "5468", "3","60000"));
    }
    public Producto buscarProducto(String nombre){
        for (Producto producto : listaProductos) {
            if (producto.getNombre().equals(nombre)){
                return producto;
            }
        }
        return null;
    }
    public Cliente buscarCliente(String idCliente) {
        int id = Integer.parseInt(idCliente);
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id){
                System.out.println("Cliente encontrado");
                return cliente;
            }
        }
        return null;
    }

    public HashSet<Producto> getListaProductos() {
        return listaProductos;
    }


    public HashMap<Integer, Venta> getVentas() {
        return ventas;
    }

    public HashMap<Integer, DetalleVenta> getDetalleVentas() {
        return detalleVentas;
    }
    public Almacen getTienda() {
        return almacen;
    }

    public HashSet<Producto> addProducto(Producto producto) {
        listaProductos.add(producto);
        return listaProductos;
    }
    public Set<Cliente> getClientes() {
        return clientes;
    }



}