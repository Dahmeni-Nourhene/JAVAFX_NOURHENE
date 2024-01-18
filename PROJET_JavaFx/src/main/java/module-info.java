module com.example.projet_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    // Export the necessary packages
    exports com.example.projet_javafx;
    exports com.example.projet_javafx.Controllers; // Add this line

    opens com.example.projet_javafx to javafx.fxml;
    opens com.example.projet_javafx.GUI to javafx.fxml;
    opens com.example.projet_javafx.Controllers to javafx.fxml;
}
