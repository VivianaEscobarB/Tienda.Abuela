package com.quindio.laboratorio.Model;

import com.quindio.laboratorio.Main.Main;

import java.util.ArrayList;

public class Almacen {
    private String nombre;
    private ArrayList<Integer> codigosCompras;
    private Gestion gestion;

    public Almacen(String nombre) {
        this.nombre = nombre;
        this.gestion = Main.getTienda();
        this.codigosCompras = new ArrayList<>();
    }

    public int crearCompra(String nProducto,  String idCliente, String fechaRegistro){
        if (gestion == null){
            gestion = Main.getTienda();
        }
        Cliente e = gestion.buscarCliente(idCliente);
        if (e == null){
            System.out.println("Cliente no encontrado");
            return -1;
        }
        Producto l = gestion.buscarProducto(nProducto);
        if (l == null){
            System.out.println("Producto no encontrado");
            return -1;
        }

        int codigo = generarCodigo();
        Venta venta = new Venta(codigo, e, l);
        DetalleVenta detalleVenta = new DetalleVenta();
        gestion.getVentas().put(codigo, venta);
        gestion.getDetalleVentas().put(codigo, detalleVenta);
        if (codigosCompras.add(codigo)){
            System.out.println("Compra creada con exito");
            return codigo;
        }
        return -1;

    }
    // metodo para generar codigo sin repetir
    public int generarCodigo(){
        int codigo = (int) (Math.random() * 1000) + 1;
        while (codigosCompras.contains(codigo)){
            codigo = (int) (Math.random() * 1000) + 1;
        }
        return codigo;
    }
    public void eliminarCompra(int codigo){
        gestion.getVentas().remove(codigo);
        gestion.getDetalleVentas().remove(codigo);
        codigosCompras.remove(codigo);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
