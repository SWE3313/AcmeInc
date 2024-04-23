module com.acme.swe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires json.simple;

    opens com.acme.swe3313 to javafx.fxml;
    exports com.acme.swe3313;
    exports com.acme.swe3313.controllers;
    opens com.acme.swe3313.controllers to javafx.fxml;
    exports com.acme.swe3313.controllers.components;
    opens com.acme.swe3313.controllers.components to javafx.fxml;
}