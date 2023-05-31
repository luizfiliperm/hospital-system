package com.lv.hospital.controllers.util;

import com.lv.hospital.entities.Patient;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AnchorPanePatients {
    
    public AnchorPane newPatientAnchorPane(Patient patient) {
        AnchorPane apPatient = new AnchorPane();
        configureAnchorPane(apPatient);

        Label lbMrn = new Label(patient.getMrn() + " -");
        configureMrnLabel(lbMrn);

        Label lbName = new Label(patient.getName());
        configureNameLabel(lbName);


        Button btnEdit = new Button();
        configureEditButton(btnEdit);

        Button btnDelete = new Button();
        configureDeleteButton(btnDelete);

        apPatient.getChildren().addAll(lbMrn, lbName, btnEdit, btnDelete);

        return apPatient;
        
    }

    private void configureAnchorPane(AnchorPane apPatient) {
        apPatient.setStyle("-fx-background-color: #0a4164;");
        apPatient.setPrefWidth(625);
        apPatient.setPrefHeight(37);
    }

    private void configureMrnLabel(Label lbMrn) {
        lbMrn.setFont(new Font(16));
        lbMrn.setTextFill(Color.WHITE);
        lbMrn.setLayoutX(14);
        lbMrn.setLayoutY(2);
    }

    private void configureNameLabel(Label lbName) {
        lbName.setFont(new Font(16));
        lbName.setTextFill(Color.WHITE);
        lbName.setLayoutX(80);
        lbName.setLayoutY(2);
    }

    private void configureEditButton(Button btnEdit) {
        btnEdit.setStyle("-fx-background-color: none;");
        ImageView editIcon = new ImageView(new Image(getClass().getResourceAsStream("/com/lv/hospital/icons/menu/fi-br-pencil.png")));
        editIcon.setFitHeight(15);
        editIcon.setFitWidth(15);
        btnEdit.setGraphic(editIcon);
        btnEdit.setLayoutX(575);
        btnEdit.setLayoutY(3);
    }

    private void configureDeleteButton(Button btnDelete) {
        btnDelete.setStyle("-fx-background-color: none;");
        ImageView deleteIcon = new ImageView(new Image(getClass().getResourceAsStream("/com/lv/hospital/icons/menu/fi-br-trash.png")));
        deleteIcon.setFitHeight(15);
        deleteIcon.setFitWidth(15);
        btnDelete.setGraphic(deleteIcon);
        btnDelete.setLayoutX(598);
        btnDelete.setLayoutY(3);
    }

    

}
