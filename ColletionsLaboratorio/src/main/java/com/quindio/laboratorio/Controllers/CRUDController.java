package com.quindio.laboratorio.Controllers;

import com.quindio.laboratorio.Main.Main;
import com.quindio.laboratorio.Model.*;
import com.quindio.laboratorio.Recursos.Ventana;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class CRUDController {

    @FXML
    private VBox visual;
    private Gestion gestion;

    private Ventana ventana;

    public CRUDController() {
        ventana = new Ventana();
        gestion = Main.getTienda();
    }

    @FXML
    private TextField nombreField;

    @FXML
    private ListView<String> registrosListView;

    private ObservableList<String> registrosObservableList;

    private ClientesCRUD clientesCRUD = new ClientesCRUD();
    private GestionAlmacenCRUD gestionAlmacenCRUD = new GestionAlmacenCRUD();

    @FXML
    public void initialize() {
        registrosObservableList = FXCollections.observableArrayList();
        registrosListView.setItems(registrosObservableList);
        updateListView(); // Cargar datos existentes al inicio
    }

    @FXML
    public void volver() {
        ventana.abrirVentana((Stage) visual.getScene().getWindow(), "/com/quindio/laboratorio/Vista1.fxml", "Registrar");
    }

    @FXML
    void updateListView() {
        List<Cliente> clientes = clientesCRUD.listarTodos();
        List<Almacen> almacens = gestionAlmacenCRUD.listarTodos();

        registrosObservableList.clear();

        for (Cliente cliente : clientes) {
            registrosObservableList.add("Cliente (ID: " + cliente.getId() + "): " + cliente.getNombre());
        }

        for (Almacen almacen : almacens) {
            registrosObservableList.add("Producto: " + almacen.getNombre());
        }
    }

    @FXML
    void registerClient(ActionEvent event) {
        String nombre = nombreField.getText().trim();
        if (!nombre.isEmpty()) {
            Cliente cliente = new Cliente(0, nombre);
            clientesCRUD.crear(cliente);
            showAlert("Cliente registrado correctamente.", AlertType.INFORMATION);
            updateListView();
        } else {
            showAlert("Por favor, ingresa el nombre del cliente.", AlertType.WARNING);
        }
    }

    @FXML
    void registerProduct(ActionEvent event) {
        String nombre = nombreField.getText().trim();
        if (!nombre.isEmpty()) {
            Almacen almacen = new Almacen(nombre);
            gestionAlmacenCRUD.crear(almacen);
            showAlert("Producto registrado correctamente.", AlertType.INFORMATION);
            updateListView();
        } else {
            showAlert("Por favor, ingresa el nombre del producto.", AlertType.WARNING);
        }
    }

    @FXML
    void eliminarRegistro() {
        int selectedIndex = registrosListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String selectedItem = registrosListView.getSelectionModel().getSelectedItem();
            if (showConfirmationDialog("¿Estás seguro de que deseas eliminar este registro?")) {
                if (selectedItem.startsWith("Cliente (ID: ")) {
                    int clienteId = Integer.parseInt(selectedItem.split(": ")[1].replace(")", ""));
                    clientesCRUD.eliminar(clienteId);
                } else if (selectedItem.startsWith("Producto: ")) {
                    gestionAlmacenCRUD.eliminar(selectedIndex);
                }
                showAlert("Registro eliminado correctamente.", AlertType.INFORMATION);
                updateListView();
            }
        } else {
            showAlert("Por favor, selecciona un registro para eliminar.", AlertType.WARNING);
        }
    }

    @FXML
    void editarRegistro(ActionEvent event) {
        int selectedIndex = registrosListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String selectedItem = registrosListView.getSelectionModel().getSelectedItem();
            if (selectedItem.startsWith("Cliente (ID: ")) {
                int clienteId = Integer.parseInt(selectedItem.split(": ")[1].replace(")", ""));
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Editar Nombre");
                dialog.setHeaderText("Editar Nombre del Cliente");
                dialog.setContentText("Ingrese el nuevo nombre del Cliente:");

                dialog.showAndWait().ifPresent(nuevoNombre -> {
                    if (!nuevoNombre.isEmpty()) {
                        Cliente clienteActualizado = new Cliente(clienteId, nuevoNombre);
                        clientesCRUD.actualizar(clienteId, clienteActualizado);
                        showAlert("Registro de cliente editado correctamente.", AlertType.INFORMATION);
                        updateListView();
                    } else {
                        showAlert("El nombre ingresado no es válido.", AlertType.WARNING);
                    }
                });
            } else if (selectedItem.startsWith("Producto: ")) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Editar Nombre");
                dialog.setHeaderText("Editar Nombre del Producto");
                dialog.setContentText("Ingrese el nuevo nombre del Producto:");
                dialog.showAndWait().ifPresent(nuevoNombre -> {
                Almacen almacenActualizado = new Almacen(nuevoNombre);
                gestionAlmacenCRUD.actualizar(0, almacenActualizado);
                showAlert("Registro de producto editado correctamente.", AlertType.INFORMATION);
                updateListView();
                });
            }
        } else {
            showAlert("Por favor, selecciona un registro para editar.", AlertType.WARNING);
        }
    }

    private boolean showConfirmationDialog(String s) {
        Alert alert = new Alert(AlertType.CONFIRMATION, s, ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }

    private void showAlert(String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Registro");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        nombreField.clear();
    }
}
