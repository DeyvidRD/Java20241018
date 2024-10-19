module org.example.avaliacao {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.avaliacao to javafx.fxml;
    exports org.example.avaliacao.model;
    opens org.example.avaliacao.model to javafx.fxml;
}