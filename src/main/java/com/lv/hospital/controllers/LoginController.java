package com.lv.hospital.controllers;

import java.io.IOException;

import com.lv.hospital.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

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

    @FXML
    void login(ActionEvent event) {

    }

    @FXML
    void signIn(ActionEvent event) throws IOException {
        App.setRoot("views/signIn");
    }

}
