package com.lv.hospital.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.lv.hospital.App;
import com.lv.hospital.controllers.util.FormatPatientFields;
import com.lv.hospital.controllers.util.LoadFonts;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuController implements Initializable {

    @FXML
    private AnchorPane anConfirmLogoutPane;

    @FXML
    private AnchorPane apDeletePatient;

    @FXML
    private AnchorPane apRegisterPacient;

    @FXML
    private Button btAddPacient;

    @FXML
    private Button btCancel;

    @FXML
    private Button btCancelLogout;

    @FXML
    private Button btClose;

    @FXML
    private Button btClosePatientRegister;

    @FXML
    private Button btConfirmLogout;

    @FXML
    private Button btDelete;

    @FXML
    private Button btLogout;

    @FXML
    private Button btRegisterPatient;

    @FXML
    private ImageView idEditPerfil;

    @FXML
    private Label lbAge;

    @FXML
    private Label lbAgendaDay;

    @FXML
    private Label lbCellPhone;

    @FXML
    private Label lbConfirmDeleteMessage;

    @FXML
    private Label lbCrm;

    @FXML
    private Label lbDoctorName;

    @FXML
    private Label lbEspeciality;

    @FXML
    private Label lbHospitalTittle;

    @FXML
    private Label lbInfo;

    @FXML
    private Label lbMrn;

    @FXML
    private Label lbName;

    @FXML
    private Label lbPatientListTittle;

    @FXML
    private Label lbRegisterTitle;

    @FXML
    private Label lbRegistredPatients;

    @FXML
    private Label lbRegistredPatientsTittle;

    @FXML
    private ScrollPane spListPatients;

    @FXML
    private TextField tfAge;

    @FXML
    private TextField tfCellphone;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfSearchPatient;


    public static Patient selectedPatient;
    private Boolean isEditing;

    private List<Patient> patients;

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
        if (isEditing) {
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

            if (isEditing) {
                editPatient(name, age, cellphone);
            } else {
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

    private void createPatient(String name, Integer age, String cellphone) {
        Patient newPatient = new Patient(null, name, age, cellphone);
        App.ps.save(newPatient, App.loggedDoctor);

        lbInfo.setText("Paciente cadastrado com sucesso!");
    }

    private void editPatient(String name, Integer age, String cellphone) {
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
        }else if(App.ps.patientExists(tfName.getText())){
            lbInfo.setText("Este paciente já está cadastrado!");
            return false;
        }

        return true;
    }

    @FXML
    void searchPatient(KeyEvent event) {
        String searchText = tfSearchPatient.getText().toLowerCase();
        
        List<Patient> filteredPatients = new ArrayList<>();

        for (Patient patient : patients) {
            if (patient.getName().toLowerCase().contains(searchText)) {
                filteredPatients.add(patient);
            }
        }
        populatePatients(filteredPatients);

    }

    private void loadPatients() {
        patients = App.ps.findAllByDoctorId(App.loggedDoctor.getId());
        populatePatients(patients);
        lbRegistredPatients.setText("" + patients.size());
    }

    private void populatePatients(List<Patient> patients) {
        VBox vbPatients = new VBox();
        vbPatients.setStyle("-fx-background-color: #0a4164;");

        for (Patient patient : patients) {
            AnchorPane apPatient = newPatientAnchorPane(patient);
            vbPatients.getChildren().add(apPatient);
        }

        spListPatients.setContent(vbPatients);
    }

    public AnchorPane newPatientAnchorPane(Patient patient) {
        AnchorPane apPatient = new AnchorPane();
        configureAnchorPane(apPatient);

        Label lbInfo = new Label(patient.getMrn() + " - " + patient.getName());
        configureInfoLabel(lbInfo, patient);

        Button btnEdit = new Button();
        configureEditButton(btnEdit, patient);

        Button btnDelete = new Button();
        configureDeleteButton(btnDelete, patient);

        apPatient.getChildren().addAll(lbInfo, btnEdit, btnDelete);

        return apPatient;

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lbDoctorName.setText("Dr. " + App.loggedDoctor.getName());
        lbCrm.setText("CRM: " + App.loggedDoctor.getCrm());
        lbEspeciality.setText(App.loggedDoctor.getEspeciality());

        patients = App.ps.findAllByDoctorId(App.loggedDoctor.getId());

        setFonts();
        configureFields();
        loadPatients();
    }

    public void setFonts(){
        lbHospitalTittle.setFont(LoadFonts.tittleFont(40.0));
        
    }

    private void configureFields() {
        FormatPatientFields.configureAge(tfAge);
        FormatPatientFields.configureCellphone(tfCellphone);
    }

    
    private void configureAnchorPane(AnchorPane apPatient) {
        apPatient.setStyle("-fx-background-color: #0a4164;");
        apPatient.setPrefWidth(625);
        apPatient.setPrefHeight(37);
    }

    private void configureInfoLabel(Label lbInfo, Patient patient) {
        lbInfo.setFont(new Font(16));
        lbInfo.setTextFill(Color.WHITE);
        lbInfo.setLayoutX(14);
        lbInfo.setLayoutY(2);

        lbInfo.cursorProperty().set(javafx.scene.Cursor.HAND);
        lbInfo.setOnMouseEntered(event -> {
            lbInfo.setUnderline(true);
        });

        lbInfo.setOnMouseExited(event -> {
            lbInfo.setUnderline(false);
        });

        lbInfo.setOnMouseClicked(event -> {
            selectedPatient = patient;
            App.setRoot("views/patientView");
        });
    }

    private void configureEditButton(Button btnEdit, Patient patient) {
        btnEdit.setStyle("-fx-background-color: none;");
        ImageView editIcon = new ImageView(
                new Image(getClass().getResourceAsStream("/com/lv/hospital/icons/menu/fi-br-pencil.png")));
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
        ImageView deleteIcon = new ImageView(
                new Image(getClass().getResourceAsStream("/com/lv/hospital/icons/menu/fi-br-trash.png")));
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
            lbCellPhone.setText(selectedPatient.getPhone());
            lbMrn.setText("Mrn: " + selectedPatient.getMrn());
            lbAge.setText("Idade: " + selectedPatient.getAge());
            
        });
    }


    @FXML
    void logout(ActionEvent event) {
        anConfirmLogoutPane.setVisible(true);
        anConfirmLogoutPane.setDisable(false);
    }

    
    @FXML
    void cancelLogout(ActionEvent event) {
        anConfirmLogoutPane.setVisible(false);
        anConfirmLogoutPane.setDisable(true);
    }

    
    @FXML
    void confirmLogout(ActionEvent event) {
        App.setRoot("views/login");
        App.loggedDoctor = null;
    }

}
