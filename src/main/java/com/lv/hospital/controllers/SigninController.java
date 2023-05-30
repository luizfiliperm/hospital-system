package com.lv.hospital.controllers;

import java.io.IOException;

import com.lv.hospital.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SigninController {

    @FXML
    private Button btCadastro;

    @FXML
    private ImageView imgVoltar;

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
    void cadastrar(ActionEvent event) {

    }

    @FXML
    void voltar(MouseEvent event) throws IOException {
        App.setRoot("views/logIn");
    }

}


