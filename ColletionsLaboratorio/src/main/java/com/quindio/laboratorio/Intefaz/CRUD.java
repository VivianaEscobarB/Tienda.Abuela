package com.quindio.laboratorio.Intefaz;

import java.util.List;

public interface CRUD<T> {
    void crear(T entidad);
    T leer(int id); // Lee un estudiante por su ID
    void actualizar(int id, T entidad);
    void eliminar(int id);
    List<T> listarTodos(); // Devuelve una lista de todos los registros
}
