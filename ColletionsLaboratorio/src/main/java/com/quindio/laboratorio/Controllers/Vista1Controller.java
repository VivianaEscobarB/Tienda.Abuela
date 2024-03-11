package com.quindio.laboratorio.Controllers;

import com.quindio.laboratorio.Main.Main;
import com.quindio.laboratorio.Model.Gestion;
import com.quindio.laboratorio.Model.Producto;
import com.quindio.laboratorio.Recursos.Ventana;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.HashSet;


public class Vista1Controller {

    private Ventana ventana;

    public Vista1Controller() {
        ventana = new Ventana();
    }

    @FXML
    private AnchorPane visual;


    @FXML
    private TableColumn<Producto, String> colCodigo;

    @FXML
    private TableColumn<Producto, String> colProducto;

    @FXML
    private TableColumn<Producto, String> colCantidad;



    @FXML
    private TableView<Producto> listaClientes;

    ObservableList<Producto> listaClientesData;

    Gestion gestion = Main.getTienda();

    @FXML
    public void initialize() {
         inicializarVista();
    }

    @FXML
    private void registro()  {
        ventana.abrirVentana((Stage) visual.getScene().getWindow(), "/com/quindio/laboratorio/CRUD.fxml", "Registro");
    }

    @FXML
    private void vender() {
        ventana.abrirVentana((Stage) visual.getScene().getWindow(), "/com/quindio/laboratorio/Comprar.fxml", "Gestionar Tienda");
    }

    private void inicializarVista() {
        this.colCodigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        this.colProducto.setCellValueFactory(new PropertyValueFactory<>("Prodcuto"));
        this.colCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));

        verListaCreada();

        listaClientesData = FXCollections.observableArrayList(gestion.getListaProductos());

        colCodigo.setCellValueFactory(cellData -> cellData.getValue().codigoProperty());
        colProducto.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colCantidad.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty());


        listaClientes.setItems(listaClientesData);

    }

    private void verListaCreada() {
        HashSet<Producto> lista = gestion.getListaProductos();
        for (Producto producto : lista) {
            System.out.println("Producto: " + producto.getNombre());
            System.out.println("Cantidad: " + producto.getCantidad());
            System.out.println("Codigo: " + producto.getCodigo());
            System.out.println("------------------------");
        }
    }


}