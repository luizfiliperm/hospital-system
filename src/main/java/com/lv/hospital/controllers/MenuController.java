package com.lv.hospital.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.lv.hospital.App;
import com.lv.hospital.controllers.util.FormatPatientFields;
import com.lv.hospital.entities.Patient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuController implements Initializable{

    @FXML
    private AnchorPane apDeletePatient;

    @FXML
    private AnchorPane apRegisterPacient;

    @FXML
    private Button btAddPacient;

    @FXML
    private Button btCancel;

    @FXML
    private Button btClose;

    @FXML
    private Button btClosePatientRegister;

    @FXML
    private Button btDelete;

    @FXML
    private Button btRegisterPatient;

    @FXML
    private Label lbAge;

    @FXML
    private Label lbCellPhone;

    @FXML
    private Label lbCrm;

    @FXML
    private Label lbDoctorName;

    @FXML
    private Label lbEspeciality;

    @FXML
    private Label lbInfo;

    @FXML
    private Label lbMrn;

    @FXML
    private Label lbName;

    @FXML
    private Label lbRegisterTitle;

    @FXML
    private Label lbRegistredPatients;

    @FXML
    private ScrollPane spListPatients;

    @FXML
    private TextField tfAge;

    @FXML
    private TextField tfCellphone;

    @FXML
    private TextField tfName;

    private Patient selectedPatient;
    private Boolean isEditing;

    @FXML
    void closeApplication(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void addPacient(ActionEvent event) {
        lbRegisterTitle.setText("Cadastrar paciente");
        btRegisterPatient.setText("Cadastrar");
        apRegisterPacient.setVisible(true);
        apRegisterPacient.setDisable(false);
        isEditing = false;
    }

    @FXML
    void closePatientRegister(ActionEvent event) {
        apRegisterPacient.setVisible(false);
        apRegisterPacient.setDisable(true);

        resetFields();

    }

    private void openPatientRegister() {
        apRegisterPacient.setVisible(true);
        apRegisterPacient.setDisable(false);
        if(isEditing){
            btRegisterPatient.setText("Salvar");
            lbRegisterTitle.setText("Editar paciente");
        }
    }

    private void resetFields() {
        tfName.setText("");
        tfAge.setText("");
        tfCellphone.setText("");
        lbInfo.setText("");
    }

    @FXML
    void savePatient(ActionEvent event) {
        if (validateFields()) {
            String name = tfName.getText();
            Integer age = Integer.parseInt(tfAge.getText());
            String cellphone = tfCellphone.getText();

            if(isEditing){
                editPatient(name, age, cellphone);
            }else{
                createPatient(name, age, cellphone);
            }
            loadPatients();
        }
    }

    @FXML
    void deletePatient(ActionEvent event) {
        App.ps.deleteById(selectedPatient.getId());
        apDeletePatient.setVisible(false);
        apDeletePatient.setDisable(true);
        loadPatients();
    }

    @FXML
    void cancelDelete(ActionEvent event) {
        apDeletePatient.setVisible(false);
        apDeletePatient.setDisable(true);
    }

    private void createPatient(String name, Integer age, String cellphone){
        Patient newPatient = new Patient(null, name, age, cellphone);
        App.ps.save(newPatient, App.loggedDoctor);
        lbInfo.setText("Paciente cadastrado com sucesso!");
    }

    private void editPatient(String name, Integer age, String cellphone){
        selectedPatient.setName(name);
        selectedPatient.setAge(age);
        selectedPatient.setPhone(cellphone);
        App.ps.update(selectedPatient);
        lbInfo.setText("Paciente editado com sucesso!");
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
        

        for (Patient patient : patients) {
            AnchorPane apPatient = newPatientAnchorPane(patient);
            vbPatients.getChildren().add(apPatient);
        }

        spListPatients.setContent(vbPatients);
        lbRegistredPatients.setText("" + patients.size());
    }

    public AnchorPane newPatientAnchorPane(Patient patient) {
        AnchorPane apPatient = new AnchorPane();
        configureAnchorPane(apPatient);

        Label lbMrn = new Label(patient.getMrn() + " -");
        configureMrnLabel(lbMrn);

        Label lbName = new Label(patient.getName());
        configureNameLabel(lbName);


        Button btnEdit = new Button();
        configureEditButton(btnEdit, patient);

        Button btnDelete = new Button();
        configureDeleteButton(btnDelete, patient);

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

    private void configureEditButton(Button btnEdit, Patient patient) {
        btnEdit.setStyle("-fx-background-color: none;");
        ImageView editIcon = new ImageView(new Image(getClass().getResourceAsStream("/com/lv/hospital/icons/menu/fi-br-pencil.png")));
        editIcon.setFitHeight(15);
        editIcon.setFitWidth(15);
        btnEdit.setGraphic(editIcon);
        btnEdit.setLayoutX(575);
        btnEdit.setLayoutY(3);
        btnEdit.cursorProperty().set(javafx.scene.Cursor.HAND);

        btnEdit.setOnAction(event -> {
            selectedPatient = patient;
            tfName.setText(selectedPatient.getName());
            tfAge.setText(selectedPatient.getAge().toString()); 
            tfCellphone.setText(selectedPatient.getPhone());
            isEditing = true;
            openPatientRegister();

        });
    }

    private void configureDeleteButton(Button btnDelete, Patient patient) {
        btnDelete.setStyle("-fx-background-color: none;");
        ImageView deleteIcon = new ImageView(new Image(getClass().getResourceAsStream("/com/lv/hospital/icons/menu/fi-br-trash.png")));
        deleteIcon.setFitHeight(15);
        deleteIcon.setFitWidth(15);
        btnDelete.setGraphic(deleteIcon);
        btnDelete.setLayoutX(598);
        btnDelete.setLayoutY(3);
        btnDelete.cursorProperty().set(javafx.scene.Cursor.HAND);

        btnDelete.setOnAction(event -> {
            selectedPatient = patient;
            apDeletePatient.setVisible(true);
            apDeletePatient.setDisable(false);

            lbName.setText(selectedPatient.getName());
            lbAge.setText(selectedPatient.getAge().toString());
            lbCellPhone.setText(selectedPatient.getPhone());
            lbMrn.setText(selectedPatient.getMrn());
        });
    }


}
