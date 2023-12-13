package com.example.proyecto1trimestre;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class Ventana2 implements Initializable {

    @FXML
    private ComboBox<String> tableComboBox;

    @FXML
    private ObservableList<String> listaTablas = FXCollections.observableArrayList();

    @FXML
    private Label label;

    private String tablaSeleccionada= null;


    private ActionEvent actionEvent;

    public Ventana2() {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listarTablas(Conexion.con);
        tableComboBox.setItems(listaTablas);
    }

    @FXML
    protected void nextButtonClicked(ActionEvent actionEvent) {

        tablaSeleccionada= tableComboBox.getValue();
        if (tablaSeleccionada != null && !tablaSeleccionada.isEmpty()) {
            // Aquí puedes pasar a la siguiente pantalla con la tabla seleccionada
            System.out.println("Tabla seleccionada: " + tablaSeleccionada);
            label.setText("Tabla seleccionada: " + tablaSeleccionada);

            cambiarVentana(actionEvent);

        } else {
            System.out.println("Por favor, selecciona una tabla antes de continuar.");
        }

    }

    private List<String> listarTablas(Connection conexion) {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet tipos = dbmd.getTables("northwind", null, null, new String[]{"TABLE"});

            while (tipos.next()) {
                String tabla = tipos.getString("TABLE_NAME");
                listaTablas.add(tabla);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaTablas;
    }


    public void cambiarVentana(ActionEvent event){
        try {
            //Obtiene ubicacion del archivo
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Ventana3.fxml"));

            //Elementos de lainterfaz que deben aparecer y como
            Scene scene=new Scene(fxmlLoader.load(),600, 300);

            Ventana3 tablecontroller =fxmlLoader.getController();
            tablecontroller.setTablaSeleccionada(tablaSeleccionada);


                //Creamos y mostramos la nueva ventana
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();

            //obtiene la fuente del evento
            Node source=(Node) event.getSource();

            //obtiene la ventana actual
            stage=(Stage) source.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





}
