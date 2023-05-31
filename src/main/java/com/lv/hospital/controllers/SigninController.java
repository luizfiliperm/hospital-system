package com.lv.hospital.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.lv.hospital.App;
import com.lv.hospital.entities.Doctor;
import com.lv.hospital.entities.enums.BrazilianState;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class SigninController implements Initializable{

    @FXML
    private Button btBack;

    @FXML
    private Button btSignIn;

    @FXML
    private Label lbInfo;

    @FXML
    private PasswordField tfConfirmPassword;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfName;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private ComboBox<BrazilianState> cbState;


    @FXML
    void backToLogin(ActionEvent event) throws IOException {
        App.setRoot("views/logIn");
    }

    @FXML
    void signIn(ActionEvent event) throws IOException {

        if (validateFields()) {

            String name = tfName.getText();
            String email = tfEmail.getText();
            String password = tfPassword.getText();
            BrazilianState state = cbState.getValue();

            Doctor newDoctor = new Doctor(null, name, password, state, "Neurologista", email);

            App.ds.save(newDoctor);
            App.loggedDoctor = newDoctor;

            App.setRoot("views/menu");
        }

    }

    private Boolean validateFields() {


        if (!checkFields()) {
            lbInfo.setText("Preencha todos os campos!");
            return false;
        }

        if (App.ds.emailExists(tfEmail.getText())) {
            lbInfo.setText("Email já cadastrado!");
            return false;
        }
        if (tfPassword.getText().length() < 8 ){
            lbInfo.setText("A senha precisa ter mais de 8 digitos!");
            return false;
        }
        if (!tfPassword.getText().equals(tfConfirmPassword.getText())){
            lbInfo.setText("As senhas não coincidem!");
            return false;
        }

        return true;

    }


    private Boolean checkFields() {

        if(tfName.getText().equals("")){
            return false;
        }
        if(tfPassword.getText().equals("")){
            return false;
        }
        if(tfConfirmPassword.getText().equals("")){
            return false;
        }
        if(tfEmail.getText().equals("")){
            return false;
        }
        if(cbState.getValue() == null){
            return false;
        }
        

        return true;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cbState.getItems().addAll(BrazilianState.values());

        cbState.setConverter(new StringConverter<BrazilianState>() {
            @Override
            public String toString(BrazilianState state) {
                return state.getName();
            }

            @Override
            public BrazilianState fromString(String string) {
                return null;
            }
        });
    }


}
