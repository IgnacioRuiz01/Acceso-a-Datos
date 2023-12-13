module com.example.proyecto1trimestre {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.xml.bind;





    opens com.example.proyecto1trimestre to javafx.fxml, java.xml.bind;
    exports com.example.proyecto1trimestre;

}