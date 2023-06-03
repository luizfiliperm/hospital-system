package com.lv.hospital.controllers;


import java.net.URL;
import java.util.ResourceBundle;

import com.lv.hospital.App;
import com.lv.hospital.controllers.util.LoadFonts;
import com.lv.hospital.entities.Doctor;
import com.lv.hospital.util.PasswordUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable{

    @FXML
    private Button btLogin;

    @FXML
    private Button btSignin;

    @FXML
    private Label lbHospitalTittle;

    @FXML
    private Button btClose;

    @FXML
    private Label lbInfo;

    @FXML
    private Label lbLogin;

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setFonts();
    }

    public void setFonts(){
        lbHospitalTittle.setFont(LoadFonts.tittleFont(50.0));
        btLogin.setFont(LoadFonts.commonFont(18.0));
        btSignin.setFont(LoadFonts.commonFont(13.0));
        lbLogin.setFont(LoadFonts.commonFont(36.0));
        lbInfo.setFont(LoadFonts.commonFont(14.0));

        tfLogin.setFont(LoadFonts.commonFont(18.0));
        tfPassword.setFont(LoadFonts.commonFont(18.0));
    }

}
