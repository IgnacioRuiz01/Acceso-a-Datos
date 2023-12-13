package com.example.proyecto1trimestre;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloController {
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private Label mensaje;

    private String nombreUsuario;
    private String contraseña;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }


    private ActionEvent actionEvent;

    @FXML
    protected void onHelloButtonClick(ActionEvent actionEvent) {

        if (userName.getText().isEmpty()||password.getText().isEmpty()){
            String mens="ERROR, algun campo incorrecto o vacio.";
            mensaje.setText(mens);
        }else {
            nombreUsuario=userName.getText();
            contraseña=password.getText();

            Conexion.con=Conexion.conectar(Conexion.url,nombreUsuario,contraseña);

            try {
                if (Conexion.con!=null){
                    mensaje.setText("Conexion Exitosa.");
                    cambiarVentana(actionEvent);
                }else {
                    mensaje.setText("Ha habido algun error.");
                }
            } catch (Exception e) {
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