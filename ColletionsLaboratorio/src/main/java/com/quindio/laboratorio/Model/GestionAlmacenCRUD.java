package com.quindio.laboratorio.Model;

import com.quindio.laboratorio.Intefaz.CRUD;

import java.util.ArrayList;
import java.util.List;

public class GestionAlmacenCRUD implements CRUD<Almacen> {
    private List<Almacen> almacens = new ArrayList<>();

    @Override
    public void crear(Almacen almacen) {
        almacens.add(almacen);
    }

    @Override
    public Almacen leer(int id) {
        if (id >= 0 && id < almacens.size()) {
            return almacens.get(id);
        }
        return null;
    }

    @Override
    public void actualizar(int id, Almacen almacenActualizado) {
        if (id >= 0 && id < almacens.size()) {
            almacens.set(id, almacenActualizado);
        }
    }

    @Override
    public void eliminar(int id) {
        if (id >= 0 && id < almacens.size()) {
            almacens.remove(id);
        }
    }

    @Override
    public List<Almacen> listarTodos() {
        return almacens;
    }
}
