package com.lv.hospital.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.lv.hospital.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class PatientViewController implements Initializable{

    @FXML
    private Button btBack;

    @FXML
    private Button btClose;

    @FXML
    private Button btInfo;

    @FXML
    private Button btSaveForm;

    @FXML
    private RadioButton eyeOpenNone;

    @FXML
    private RadioButton eyeOpenPain;

    @FXML
    private RadioButton eyeOpenSpontaneous;

    @FXML
    private RadioButton eyeOpenVoice;

    @FXML
    private ToggleGroup eyeOpening;

    @FXML
    private ToggleGroup eyeResponse;

    @FXML
    private RadioButton eyeResponseBilateral;

    @FXML
    private RadioButton eyeResponseNone;

    @FXML
    private RadioButton eyeResponseUnilateral;

    @FXML
    private Label lbPatientAge;

    @FXML
    private Label lbPatientMrn;

    @FXML
    private Label lbPatientName;

    @FXML
    private Label lbPatientPhone;

    @FXML
    private Label lbTestPontuation;

    @FXML
    private Label lbTestResult;

    @FXML
    private ToggleGroup motorResponse;

    @FXML
    private RadioButton motorResponseAbnormalExtension;

    @FXML
    private RadioButton motorResponseAbnormalFlexion;

    @FXML
    private RadioButton motorResponseLocate;

    @FXML
    private RadioButton motorResponseNone;

    @FXML
    private RadioButton motorResponseObey;

    @FXML
    private RadioButton motorResponseWithdrawal;

    @FXML
    private ToggleGroup verbalResponse;

    @FXML
    private RadioButton verbalResponseConfused;

    @FXML
    private RadioButton verbalResponseInappropriate;

    @FXML
    private RadioButton verbalResponseIncomprehensible;

    @FXML
    private RadioButton verbalResponseNone;

    @FXML
    private RadioButton verbalResponseOriented;

    @FXML
    void backToMenu(ActionEvent event) {
        App.setRoot("views/menu");
    }

    @FXML
    void closeApplication(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void openInfo(ActionEvent event) {

    }

    @FXML
    void saveForm(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lbPatientName.setText(MenuController.selectedPatient.getName());
        lbPatientAge.setText(MenuController.selectedPatient.getAge().toString());
        lbPatientMrn.setText(MenuController.selectedPatient.getMrn());
        lbPatientPhone.setText(MenuController.selectedPatient.getPhone());
    }

}
