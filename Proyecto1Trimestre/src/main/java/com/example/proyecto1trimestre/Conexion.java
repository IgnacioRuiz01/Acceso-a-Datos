package com.example.proyecto1trimestre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static String url= "jdbc:mysql://localhost/northwind?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false";

    public static Connection con=null;

    public static Connection conectar(String url,String usuario, String contraseña) {
        try {
            // Establecer la conexión
            con = DriverManager.getConnection(url, usuario, contraseña);

            if (con != null) {
                System.out.println("¡Conexión exitosa!");
            } else {
                System.out.println("¡No se pudo establecer la conexión!");
            }

        } catch (SQLException e) {
            System.out.println("Usuario o Contraseña incorrectos");
        }

        return con;
    }


    public static void cerrarConexion(){
        try {
            if (con!= null && !con.isClosed()){
                con.close();
                System.out.println("Conexion Cerrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
