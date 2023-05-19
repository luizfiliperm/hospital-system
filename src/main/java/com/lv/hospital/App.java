package com.lv.hospital;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import com.lv.hospital.entities.Patient;
import com.lv.hospital.services.PatientService;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {


        PatientService service = new PatientService();

        Patient p1 = new Patient(null, "Julia Brown", 17);
        Patient p2 = new Patient(null, "Alex Green", 20);
        Patient p3 = new Patient(null, "Bob Grey", 25);

        service.save(p1);
        service.save(p2);
        service.save(p3);

        p1.setAge(18);
        service.update(p1);
        
        ArrayList<Patient> list = (ArrayList<Patient>) service.findAll();
        list.forEach(System.out::println);

        service.deleteById(3L);

        list = (ArrayList<Patient>) service.findAll();

        list.forEach(System.out::println);


        System.out.println("Find by id: " + service.findById(1L));
        System.out.println("OK!");

        service.close();
        // launch(args);
    }

}