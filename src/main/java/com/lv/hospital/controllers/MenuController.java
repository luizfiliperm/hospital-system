package com.lv.hospital.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.lv.hospital.App;
import com.lv.hospital.controllers.util.AnchorPanePatients;
import com.lv.hospital.controllers.util.FormatPatientFields;
import com.lv.hospital.entities.Patient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MenuController implements Initializable {

    @FXML
    private AnchorPane apRegisterPacient;

    @FXML
    private Button btAddPacient;

    @FXML
    private Button btClosePatientRegister;

    @FXML
    private Button btRegisterPatient;

    @FXML
    private Label lbCrm;

    @FXML
    private Label lbDoctorName;

    @FXML
    private Label lbEspeciality;

    @FXML
    private Label lbInfo;

    @FXML
    private ScrollPane spListPatients;

    @FXML
    private TextField tfAge;

    @FXML
    private TextField tfCellphone;

    @FXML
    private TextField tfName;

    @FXML
    void addPacient(ActionEvent event) {
        apRegisterPacient.setVisible(true);
        apRegisterPacient.setDisable(false);
    }

    @FXML
    void closePatientRegister(ActionEvent event) {
        apRegisterPacient.setVisible(false);
        apRegisterPacient.setDisable(true);

        resetFields();

    }

    private void resetFields() {
        tfName.setText("");
        tfAge.setText("");
        tfCellphone.setText("");
    }

    @FXML
    void savePatient(ActionEvent event) {
        if (validateFields()) {
            String name = tfName.getText();
            Integer age = Integer.parseInt(tfAge.getText());
            String cellphone = tfCellphone.getText();
            Patient newPatient = new Patient(null, name, age, cellphone);

            App.ps.save(newPatient, App.loggedDoctor);
            lbInfo.setText("Paciente cadastrado com sucesso!");
        }
    }

    public boolean validateFields() {
        if (tfName.getText().equals("") || tfAge.getText().equals("") || tfCellphone.getText().equals("")) {
            lbInfo.setText("Preencha todos os campos!");
            return false;
        }

        return true;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lbDoctorName.setText("Dr. " + App.loggedDoctor.getName());
        lbCrm.setText("CRM: " + App.loggedDoctor.getCrm());
        lbEspeciality.setText(App.loggedDoctor.getEspeciality());

        configureFields();
        loadPatients();
    }

    private void configureFields() {
        FormatPatientFields.configureAge(tfAge);
        FormatPatientFields.configureCellphone(tfCellphone);
    }

    private void loadPatients(){

        VBox vbPatients = new VBox();
        vbPatients.setStyle("-fx-background-color: #0a4164;");

        List<Patient> patients = App.ps.findAllByDoctorId(App.loggedDoctor.getId());
        
        AnchorPanePatients apPatients = new AnchorPanePatients();

        for (Patient patient : patients) {
            AnchorPane apPatient = apPatients.newPatientAnchorPane(patient);
            vbPatients.getChildren().add(apPatient);
        }

        spListPatients.setContent(vbPatients);
    }

}
