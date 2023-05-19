module com.lv.hospital {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.sql;

    opens com.lv.hospital to javafx.fxml;
    opens com.lv.hospital.controllers to javafx.fxml;
    exports com.lv.hospital;
}
