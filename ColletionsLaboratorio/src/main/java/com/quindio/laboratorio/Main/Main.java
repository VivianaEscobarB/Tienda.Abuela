package com.quindio.laboratorio.Main;

import com.quindio.laboratorio.Controllers.Vista1Controller;
import com.quindio.laboratorio.Model.Gestion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Gestion gestion;

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quindio/laboratorio/Vista1.fxml"));
            Parent root = loader.load();
            Vista1Controller controlador = loader.getController();

            // Configurar la escena
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Tienda de la Abuela");
            // Mostrar la ventana principal
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        gestion = new Gestion();
        launch(args);

    }

    public static Gestion getTienda() {

        return gestion;
    }

    public static void setTienda(Gestion gestion) {
        Main.gestion = gestion;
    }
}
