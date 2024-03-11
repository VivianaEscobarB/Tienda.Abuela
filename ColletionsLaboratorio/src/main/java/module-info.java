module com.quindio.laboratorio {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.quindio.laboratorio.Main to javafx.fxml;
    opens com.quindio.laboratorio.Controllers to javafx.fxml; // Abre el paquete Controllers
    opens com.quindio.laboratorio.Model to javafx.base; // Abre el paquete Model
    exports com.quindio.laboratorio.Main;
    exports com.quindio.laboratorio.Controllers;
}
