package com.lv.hospital.controllers;


import com.lv.hospital.App;
import com.lv.hospital.entities.Doctor;
import com.lv.hospital.util.PasswordUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController{

    @FXML
    private Button btLogin;

    @FXML
    private Button btSignin;

    @FXML
    private Button btClose;

    @FXML
    private Label lbInfo;

    @FXML
    private TextField tfLogin;

    @FXML
    private PasswordField tfPassword;


    private Doctor auxDoctor;

    @FXML
    void closeApplication(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void login(ActionEvent event){
        if(validateFields()){
            App.loggedDoctor = auxDoctor;
            App.setRoot("views/menu");
        }
    }

    private Boolean validateFields(){

        String login = tfLogin.getText();
        String password = tfPassword.getText();

        if(login.equals("") || password.equals("")){
            lbInfo.setText("Preencha todos os campos!");
            return false;
        }

        auxDoctor = findDoctor();
        if(auxDoctor == null){
            lbInfo.setText("Usuário ou senha incorretos");
            return false;
        }
        
        if(!PasswordUtils.compare(password, auxDoctor.getPassword())){
            lbInfo.setText("Usuário ou senha incorretos");
            return false;
        }

        return true;
    }

    private Doctor findDoctor(){
        Doctor auxDoctor = App.ds.findByCrm(tfLogin.getText());
        if(auxDoctor == null){
            auxDoctor = App.ds.findByEmail(tfLogin.getText());
        }

        return auxDoctor;
    }

    @FXML
    void signIn(ActionEvent event) {
        App.setRoot("views/signIn");
    }


}
