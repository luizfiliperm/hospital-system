package com.lv.hospital.controllers;

import java.io.IOException;

import com.lv.hospital.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SigninController {

    @FXML
    private Button btBack;

    @FXML
    private Button btCadastro;

    @FXML
    private TextField tfConfirnPassword;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfState;

    @FXML
    void backToLogin(ActionEvent event) throws IOException {
        App.setRoot("views/logIn");
    }

    @FXML
    void cadastrar(ActionEvent event) {

    }

}
