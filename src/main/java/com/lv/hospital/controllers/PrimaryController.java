package com.lv.hospital.controllers;

import java.io.IOException;

import com.lv.hospital.App;

import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
