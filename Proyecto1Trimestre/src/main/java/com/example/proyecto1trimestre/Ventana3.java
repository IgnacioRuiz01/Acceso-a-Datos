package com.example.proyecto1trimestre;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Ventana3  {

     @FXML
     private TableView<ObservableList<String>> tableView;

     @FXML
    private String tablaSeleccionada;

    private ActionEvent actionEvent;

    public void setTablaSeleccionada(String tablaSeleccionada){
        this.tablaSeleccionada=tablaSeleccionada;
        cargarDatosTabla();
    }



    @FXML
    protected void backButton(ActionEvent actionEvent){

        cambiarVentana(actionEvent);
    }




    private void cargarDatosTabla() {
        try {

            String query = "SELECT * FROM " + tablaSeleccionada;
            PreparedStatement preparedStatement = Conexion.con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Limpiar la tabla antes de cargar nuevos datos
            tableView.getItems().clear();
            tableView.getColumns().clear();

            // Configurar las columnas
            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn<ObservableList<String>, String> col = new TableColumn<>(resultSet.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(j)));
                tableView.getColumns().add(col);
            }

            // Agregar datos a la tabla
            while (resultSet.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.add(resultSet.getString(i));
                }
                tableView.getItems().add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo básico de excepciones, ajusta según tus necesidades
        }
    }

    @FXML
    protected  void ExportarCSV(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos CSV (*.csv)", "*.csv"));
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                // Escribir encabezados
                for (TableColumn<ObservableList<String>, ?> col : tableView.getColumns()) {
                    writer.write(" "+ col.getText()+" " + ";");
                }
                writer.newLine();

                // Escribir datos
                for (ObservableList<String> row : tableView.getItems()) {
                    for (String value : row) {
                        writer.write(" " +value+ " " + ";");
                    }
                    writer.newLine();
                }

                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void ExportarXML(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo XML");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos XML (*.xml)", "*.xml"));
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            try {
                List<RowData> rowDataList = new ArrayList<>();

                // Construir la lista de RowData
                for (ObservableList<String> row : tableView.getItems()) {
                    RowData rowData = new RowData();
                    rowData.setValues(row.subList(0, Math.min(row.size(), tableView.getColumns().size())));
                    rowDataList.add(rowData);
                }

                // Crear el objeto TableData contenedor
                TableData tableData = new TableData();
                tableData.setRows(rowDataList);

                // Crear el contexto JAXB
                JAXBContext context = JAXBContext.newInstance(TableData.class);

                // Crear el marshaller
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                // Escribir los datos en el archivo XML
                marshaller.marshal(tableData, new FileWriter(file));

            } catch (IOException | JAXBException e) {
                e.printStackTrace();
            }
        }
    }
    public void cambiarVentana(ActionEvent event){

        try {
            //Obtiene ubicacion del archivo
            FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("Ventana2.fxml"));

            //Elementos de lainterfaz que deben aparecer y como
            Scene scene=new Scene(fxmlLoader.load(),320, 300);

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
