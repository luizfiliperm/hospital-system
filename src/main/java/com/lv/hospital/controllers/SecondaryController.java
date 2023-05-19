package com.lv.hospital.controllers;

import java.io.IOException;

import com.lv.hospital.App;

import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}