package com.quindio.laboratorio.Controllers;

import com.quindio.laboratorio.Main.Main;
import com.quindio.laboratorio.Model.Gestion;
import com.quindio.laboratorio.Model.DetalleVenta;
import com.quindio.laboratorio.Model.Producto;
import com.quindio.laboratorio.Model.Venta;
import com.quindio.laboratorio.Recursos.Ventana;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.*;
import java.time.LocalDate;

import static javafx.application.Platform.exit;
import static javax.swing.JOptionPane.showInputDialog;

public class VentasController {
    Venta venta = new Venta();
    @FXML
    private TableColumn<Producto, String> colProducto;
    @FXML
    private TableColumn<Producto, String> colCantidad;
    @FXML
    private TableColumn<Producto, String> colCodigo;
    @FXML
    private TableColumn<Producto, String> colValor;

    ObservableList<Producto> listaTienda;


    @FXML
    private TableView<Producto> tblVentas;

    @FXML
    private AnchorPane visual;

    private Ventana ventana;
    private Gestion gestion = Main.getTienda();

    public VentasController() {
        ventana = new Ventana();
    }

    @FXML
    private TextField codigo;

    @FXML
    private TextField producto;

    @FXML
    private TextField valor;

    @FXML
    private void initialize() {
        inicializarVista();
    }

    @FXML
    private void vender() {
        try{
        // Obtener el producto seleccionado
        Producto ventaSeleccionado = tblVentas.getSelectionModel().getSelectedItem();
        DetalleVenta destalle = new DetalleVenta();
        if (ventaSeleccionado != null) {
            String cliente= JOptionPane.showInputDialog("Ingrese el nombre del cliente");
            String cc= JOptionPane.showInputDialog("Documento cliente");
            String cantidad= (JOptionPane.showInputDialog("Cuantas "+" "+ventaSeleccionado.getNombre()+" "+"desea vender?"));

            if(Integer.parseInt(cantidad)>Integer.parseInt(ventaSeleccionado.getCantidad())){
                showAlert("Error", "No hay suficientes productos para vender.", Alert.AlertType.ERROR);
            }
            // Reducir
            else if(Integer.parseInt(cantidad)<Integer.parseInt(ventaSeleccionado.getCantidad())){
                venta.venderProducto(ventaSeleccionado, cantidad);
                String mensaje = ("PRODUCTO: " + ventaSeleccionado.getNombre() + " " +
                        "\nVENDIDO A : " + cliente + " C.C: " +
                        cc + "\nDIA: " + destalle.getFechaVenta());
                showAlert("Venta", mensaje, Alert.AlertType.INFORMATION);
                if(Integer.parseInt(ventaSeleccionado.getCantidad())<0){
                    listaTienda.remove(ventaSeleccionado);
                }
            }
        }

        }catch (Exception e){
            showAlert("Error", "Por favor, selecciona un producto para eliminar.", Alert.AlertType.ERROR);

    }
    }

    @FXML
    private void detalles() {
        // Obtener la venta
        Producto ventaSeleccionado = tblVentas.getSelectionModel().getSelectedItem();
       // showAlert ( "", String.valueOf(ventaSeleccionado.getSeletionModel()), Alert.AlertType.INFORMATION);

        if (ventaSeleccionado != null && ventaSeleccionado.getCliente() != null) {
            // Mostrar los detalles de la venta en un cuadro de diálogo
            String mensaje = "Nombre del producto: " + ventaSeleccionado.getNombre() + "\nQuién lo compro " + venta.getCliente().getNombre() + "\n" +
                    "Codigo de producto: " + ventaSeleccionado.getCodigo();
            showAlert("Detalles de la compra", mensaje, Alert.AlertType.INFORMATION);
        } else {
            // Mostrar un mensaje de error si no se seleccionó ningún elemento
            showAlert("Error", "Por favor, selecciona un producto para ver los detalles.", Alert.AlertType.ERROR);
        }
    }

    private void inicializarVista() {
        this.colCodigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        this.colProducto.setCellValueFactory(new PropertyValueFactory<>("Prodcuto"));
        this.colCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        this.colValor.setCellValueFactory(new PropertyValueFactory<>("Valores"));

        listaTienda = FXCollections.observableArrayList(gestion.getListaProductos());

        colCodigo.setCellValueFactory(cellData -> cellData.getValue().codigoProperty());
        colProducto.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colCantidad.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty());
        colValor.setCellValueFactory(cellData -> cellData.getValue().valorProperty());



        tblVentas.setItems(listaTienda);
    }

    @FXML
    void agregar () {
        String idProducto = codigo.getText();
        String nombre = producto.getText();
        String precio = valor.getText();
        String cantidad = showInputDialog("Ingrese la cantidad de productos");
        if (!listaTienda.contains(idProducto)) {
            Producto producto = new Producto(nombre, idProducto, cantidad, precio);
            listaTienda.add(producto);
            showAlert("Producto", "Producto agregado con exito", Alert.AlertType.INFORMATION);
        }
        else {
            showAlert("Error", "El producto ya existe", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void volver() {
        ventana.abrirVentana((Stage) visual.getScene().getWindow(), "/com/quindio/laboratorio/Vista1.fxml", "Registrar");
    }
}
