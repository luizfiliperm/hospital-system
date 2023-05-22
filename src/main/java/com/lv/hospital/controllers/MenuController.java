package com.lv.hospital.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.lv.hospital.App;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MenuController implements Initializable{

    @FXML
    private Label lbDoctorName;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       lbDoctorName.setText("Bem Vindo, " + App.loggedDoctor.getName());
    }

}
