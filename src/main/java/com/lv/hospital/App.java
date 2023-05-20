package com.lv.hospital;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.lv.hospital.entities.GlasgowComaScale;
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


        PatientService pService = new PatientService();
        // Testing PatientService

        Patient p1 = new Patient(null, "João", 20);

        pService.save(p1);

        // Testing GlasgowComaScaleService

        GlasgowComaScale g1 = new GlasgowComaScale(null, 4, 5, 6, 2, p1);

        pService.updateGlasgowComaScaleByUserId(p1.getId(), g1);

        System.out.println(pService.findById(p1.getId()).getGlasgowComaScale().getTotal());
        System.out.println(pService.findById(p1.getId()).getGlasgowComaScale().getResult());

        g1.setEyeOpening(6);
        g1.setVerbalResponse(-5);

        pService.updateGlasgowComaScaleByUserId(p1.getId(), g1);

        System.out.println(pService.findById(p1.getId()).getGlasgowComaScale().getTotal());
        System.out.println(pService.findById(p1.getId()).getGlasgowComaScale().getResult());

        System.out.println("OK!");

        pService.close();
        // launch(args);
    }

}