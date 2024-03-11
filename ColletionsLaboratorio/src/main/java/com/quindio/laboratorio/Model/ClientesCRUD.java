package com.quindio.laboratorio.Model;
import com.quindio.laboratorio.Intefaz.CRUD;
import com.quindio.laboratorio.Main.Main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class ClientesCRUD implements CRUD<Cliente> {
    private Set<Cliente> clientes = new HashSet<>();
    private int nextId = (int) (Math.random() * 100000)+100000;
    private Gestion tienda = Main.getTienda();


    @Override
    public void crear(Cliente cliente) {
        cliente.setId(nextId);
        clientes.add(cliente);
        tienda.getClientes().add(cliente);
        nextId=(int) (Math.random() * 100000)+100000;
    }

    @Override
    public Cliente leer(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public void actualizar(int id, Cliente clienteActualizado) {
        Cliente clienteExistente = leer(id);
        if (clienteExistente != null) {
            clienteExistente.setNombre(clienteActualizado.getNombre());
        }
    }

    @Override
    public void eliminar(int id) {
        Cliente cliente = leer(id);
        if (cliente != null) {
            clientes.remove(cliente);
        }
    }

    @Override
    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes); // Convierte el conjunto en una lista
    }

}

