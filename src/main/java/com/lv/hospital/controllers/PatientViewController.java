package com.lv.hospital.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.lv.hospital.App;
import com.lv.hospital.entities.GlasgowComaScale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class PatientViewController implements Initializable {

    @FXML
    private AnchorPane apErrorPopUp;

    @FXML
    private AnchorPane apInfoGlasgow;

    @FXML
    private Button btBack;

    @FXML
    private Button btClose;

    @FXML
    private Button btCloseInfo;

    @FXML
    private Button btClosePopUp;

    @FXML
    private Button btInfo;

    @FXML
    private Button btSaveForm;

    @FXML
    private RadioButton eyeOpenNone;

    @FXML
    private RadioButton eyeOpenPain;

    @FXML
    private RadioButton eyeOpenSpontaneous;

    @FXML
    private RadioButton eyeOpenVoice;

    @FXML
    private ToggleGroup eyeOpening;

    @FXML
    private Label lbPatientAge;

    @FXML
    private Label lbPatientMrn;

    @FXML
    private Label lbPatientName;

    @FXML
    private Label lbPatientPhone;

    @FXML
    private Label lbTestPontuation;

    @FXML
    private Label lbTestResult;

    @FXML
    private ToggleGroup motorResponse;

    @FXML
    private RadioButton motorResponseAnormalExtension;

    @FXML
    private RadioButton motorResponseAnormalFlexion;

    @FXML
    private RadioButton motorResponseLocatePain;

    @FXML
    private RadioButton motorResponseNone;

    @FXML
    private RadioButton motorResponseObey;

    @FXML
    private RadioButton motorResponseWithdrawal;

    @FXML
    private ToggleGroup pupilResponse;

    @FXML
    private RadioButton pupilResponseBilateral;

    @FXML
    private RadioButton pupilResponseNone;

    @FXML
    private RadioButton pupilResponseUnilateral;

    @FXML
    private ToggleGroup verbalResponse;

    @FXML
    private RadioButton verbalResponseConfused;

    @FXML
    private RadioButton verbalResponseInappropriate;

    @FXML
    private RadioButton verbalResponseIncomprehensible;

    @FXML
    private RadioButton verbalResponseNone;

    @FXML
    private RadioButton verbalResponseOriented;

    private GlasgowComaScale gcs = new GlasgowComaScale();

    @FXML
    void backToMenu(ActionEvent event) {
        App.setRoot("views/menu");
    }

    @FXML
    void closeApplication(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void openInfo(ActionEvent event) {
        apInfoGlasgow.setVisible(true);
        apInfoGlasgow.setDisable(false);
    }

    @FXML
    void closeInfo(ActionEvent event) {
        apInfoGlasgow.setVisible(false);
        apInfoGlasgow.setDisable(true);
    }

    @FXML
    void setEyeOpening(ActionEvent event) {
    
            if (eyeOpenSpontaneous.isSelected()) {
                gcs.setEyeOpening(4);
            } else if (eyeOpenVoice.isSelected()) {
                gcs.setEyeOpening(3);
            } else if (eyeOpenPain.isSelected()) {
                gcs.setEyeOpening(2);
            } else if (eyeOpenNone.isSelected()) {
                gcs.setEyeOpening(1);
            }
    }

    @FXML
    void setVerbalResponse(ActionEvent event) {

        if (verbalResponseOriented.isSelected()) {
            gcs.setVerbalResponse(5);
        } else if (verbalResponseConfused.isSelected()) {
            gcs.setVerbalResponse(4);
        } else if (verbalResponseInappropriate.isSelected()) {
            gcs.setVerbalResponse(3);
        } else if (verbalResponseIncomprehensible.isSelected()) {
            gcs.setVerbalResponse(2);
        } else if (verbalResponseNone.isSelected()) {
            gcs.setVerbalResponse(1);
        }
    }

    @FXML
    void setMotorResponse(ActionEvent event) {

        if (motorResponseObey.isSelected()) {
            gcs.setMotorResponse(6);
        } else if (motorResponseLocatePain.isSelected()) {
            gcs.setMotorResponse(5);
        } else if (motorResponseWithdrawal.isSelected()) {
            gcs.setMotorResponse(4);
        } else if (motorResponseAnormalFlexion.isSelected()) {
            gcs.setMotorResponse(3);
        } else if (motorResponseAnormalExtension.isSelected()) {
            gcs.setMotorResponse(2);
        } else if (motorResponseNone.isSelected()) {
            gcs.setMotorResponse(1);
        }

    }

    @FXML
    void setPupilResponse(ActionEvent event) {
            
            if (pupilResponseBilateral.isSelected()) {
                gcs.setPupilResponse(2);
            } else if (pupilResponseUnilateral.isSelected()) {
                gcs.setPupilResponse(1);
            } else if (pupilResponseNone.isSelected()) {
                gcs.setPupilResponse(0);
            }

    }

    @FXML
    void saveForm(ActionEvent event) { 
        if(checkIfAllToggleGroupsAreSelected()){
            App.ps.updateGlasgowComaScaleByPatientId(MenuController.selectedPatient.getId(), gcs);
            updateInfo();
        }else{
            openErrorPopUp();
        }
    }

    private Boolean checkIfAllToggleGroupsAreSelected(){
        if(eyeOpening.getSelectedToggle() == null){
            return false;
        }
        if(verbalResponse.getSelectedToggle() == null){
            return false;
        }
        if(motorResponse.getSelectedToggle() == null){
            return false;
        }
        if(pupilResponse.getSelectedToggle() == null){
            return false;
        }
        return true;
    }

    
    @FXML
    void closePopup(ActionEvent event) {
        apErrorPopUp.setVisible(false);
        apErrorPopUp.setDisable(true);
    }

    private void openErrorPopUp(){
        apErrorPopUp.setVisible(true);
        apErrorPopUp.setDisable(false);
    }

    public void updateInfo(){
        if(MenuController.selectedPatient.getGlasgowComaScale() != null){
            gcs = MenuController.selectedPatient.getGlasgowComaScale();
            lbTestPontuation.setText(gcs.getTotal().toString());
            lbTestResult.setText(gcs.getResult());
        }else{
            lbTestPontuation.setText("0");
            lbTestResult.setText("Não realizado");
        }
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lbPatientName.setText(MenuController.selectedPatient.getName());
        lbPatientAge.setText(MenuController.selectedPatient.getAge().toString());
        lbPatientMrn.setText(MenuController.selectedPatient.getMrn());
        lbPatientPhone.setText(MenuController.selectedPatient.getPhone());

        updateInfo();
        selectPatientRadioButtons();
        
    }

    public void selectPatientRadioButtons(){
        if (gcs.getEyeOpening() != null) {
            switch (gcs.getEyeOpening()) {
                case 4:
                    eyeOpenSpontaneous.setSelected(true);
                    break;
                case 3:
                    eyeOpenVoice.setSelected(true);
                    break;
                case 2:
                    eyeOpenPain.setSelected(true);
                    break;
                case 1:
                    eyeOpenNone.setSelected(true);
                    break;
            }
        }

        if(gcs.getVerbalResponse() != null){
            switch (gcs.getVerbalResponse()) {
                case 5:
                    verbalResponseOriented.setSelected(true);
                    break;
                case 4:
                    verbalResponseConfused.setSelected(true);
                    break;
                case 3:
                    verbalResponseInappropriate.setSelected(true);
                    break;
                case 2:
                    verbalResponseIncomprehensible.setSelected(true);
                    break;
                case 1:
                    verbalResponseNone.setSelected(true);
                    break;
            }
        }

        if(gcs.getMotorResponse() != null){
            switch (gcs.getMotorResponse()) {
                case 6:
                    motorResponseObey.setSelected(true);
                    break;
                case 5:
                    motorResponseLocatePain.setSelected(true);
                    break;
                case 4:
                    motorResponseWithdrawal.setSelected(true);
                    break;
                case 3:
                    motorResponseAnormalFlexion.setSelected(true);
                    break;
                case 2:
                    motorResponseAnormalExtension.setSelected(true);
                    break;
                case 1:
                    motorResponseNone.setSelected(true);
                    break;
            }
        }

        if(gcs.getPupilResponse() != null){
            switch (gcs.getPupilResponse()) {
                case 2:
                    pupilResponseBilateral.setSelected(true);
                    break;
                case 1:
                    pupilResponseUnilateral.setSelected(true);
                    break;
                case 0:
                    pupilResponseNone.setSelected(true);
                    break;
            }
        }
    }

}
