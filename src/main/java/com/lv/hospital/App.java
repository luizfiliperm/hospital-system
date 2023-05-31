package com.lv.hospital;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import com.lv.hospital.entities.Doctor;
import com.lv.hospital.services.DoctorService;
import com.lv.hospital.services.PatientService;


public class App extends Application {

    public static DoctorService ds = new DoctorService();

    public static PatientService ps = new PatientService();

    public static Doctor loggedDoctor;

    private static Scene scene;

    private double xOffset;
    private double yOffset;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = loadFXML("views/logIn");
        scene = new Scene(root);

        setDraggable(root, stage);

        stage.initStyle(javafx.stage.StageStyle.UNDECORATED);

        Image icon = new Image(getClass().getResourceAsStream("/com/lv/hospital/icons/logo/umbrella-logo.png")); 
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.show();
    }

    public static void setDraggable(Node node, Stage stage) {
        double[] xOffset = new double[1];
        double[] yOffset = new double[1];

        node.setOnMousePressed(event -> {
            xOffset[0] = event.getSceneX();
            yOffset[0] = event.getSceneY();
        });

        node.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset[0]);
            stage.setY(event.getScreenY() - yOffset[0]);
        });
    }

    public void handleMousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    public void handleMouseDragged(MouseEvent event) {
        Stage stage = (Stage) scene.getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    public static void setRoot(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        setDraggable(root, (Stage) scene.getWindow());
        scene.setRoot(root);
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}