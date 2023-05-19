module com.lv.hospital {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;

    

    opens com.lv.hospital to javafx.fxml;
    opens com.lv.hospital.controllers to javafx.fxml;
    exports com.lv.hospital;
    opens com.lv.hospital.entities;
}
