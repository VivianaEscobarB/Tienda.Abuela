package com.quindio.laboratorio.Recursos;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Ventana {
    public void abrirVentana(Stage ventanaActual, String m,String titulo) {
        try {
            // Cargar la nueva vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(m));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            // Crear una nueva ventana
            Stage stage = new Stage();
            //Titulo ventana
            stage.setTitle(titulo);
            // Configurar la nueva escena en la nueva ventana
            stage.setScene(scene);
            // Mostrar la nueva ventana
            stage.show();
            // Cerrar la ventana actual
            ((Stage) ventanaActual).close();

        } catch (IOException e) {
            e.printStackTrace();
            // Manejar errores de carga de la vista
        }
    }

}
