package com.lv.hospital.controllers;

import java.io.IOException;

import com.lv.hospital.App;
import com.lv.hospital.entities.Doctor;
import com.lv.hospital.util.PasswordUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController{

    @FXML
    private Button btLogin;

    @FXML
    private Button btSignin;

    @FXML
    private Label lbInfo;

    @FXML
    private TextField tfLogin;

    @FXML
    private TextField tfPassword;


    private Doctor auxDoctor;

    @FXML
    void login(ActionEvent event) throws IOException{
        if(validateFields()){
            App.loggedDoctor = auxDoctor;
            App.setRoot("views/menu");
        }
    }

    private Boolean validateFields(){

        String crm = tfLogin.getText();
        String password = tfPassword.getText();

        if(crm.equals("") || password.equals("")){
            lbInfo.setText("Preencha todos os campos!");
            return false;
        }

        Doctor auxDoctor = App.ds.findByCrm(crm);
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

    @FXML
    void signIn(ActionEvent event) throws IOException {
        App.setRoot("views/signIn");
    }


}
