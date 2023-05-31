package com.lv.hospital.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import com.lv.hospital.App;
import com.lv.hospital.entities.Patient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class MenuController implements Initializable{

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

    private void resetFields(){
        tfName.setText("");
        tfAge.setText("");
        tfCellphone.setText("");
    }

    @FXML
    void savePatient(ActionEvent event) {
        if(validateFields()){
            String name = tfName.getText();
            Integer age = Integer.parseInt(tfAge.getText());
            String cellphone = tfCellphone.getText();
            Patient newPatient = new Patient(null, name, age, cellphone);

            App.ps.save(newPatient, App.loggedDoctor);
            lbInfo.setText("Paciente cadastrado com sucesso!");
        }
    }

    public boolean validateFields(){
        if(tfName.getText().equals("") || tfAge.getText().equals("") || tfCellphone.getText().equals("")){
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
    }

    private void configureFields(){
        configureAge();
        configureCellphone();
    }

    private void configureAge(){
        tfAge.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), null, getNumericFilter()));
    }

    private UnaryOperator<TextFormatter.Change> getNumericFilter() {
        return change -> {
            String newText = change.getControlNewText();
            if (Pattern.matches("\\d*", newText)) {
                return change;
            }
            return null;
        };
    }

    private void configureCellphone() {
        final int MAX_DIGITS = 15;

        tfCellphone.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String newText = tfCellphone.getText() + event.getCharacter();
    
            if (!isNumericCharacter(event.getCharacter()) || newText.length() > MAX_DIGITS) {
                event.consume();
            } else {
                String formattedText = formatPhoneText(newText);
                tfCellphone.setText(formattedText);
                tfCellphone.positionCaret(formattedText.length());
                event.consume();
            }
        });
    }
    
    private boolean isNumericCharacter(String character) {
        return character.matches("[0-9]");
    }


    private String formatPhoneText(String text) {
        String digitsOnly = text.replaceAll("\\D", "");

        StringBuilder formattedText = new StringBuilder();
        int i = 0;
        for (char c : digitsOnly.toCharArray()) {
            if (i == 0) {
                formattedText.append("(");
            } else if (i == 2) {
                formattedText.append(") ");
            } else if (i == 7) {
                formattedText.append("-");
            }
            formattedText.append(c);
            i++;
        }

        return formattedText.toString();
    }



}
