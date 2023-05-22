package com.lv.hospital.controllers;

import java.io.IOException;

import com.lv.hospital.App;
import com.lv.hospital.entities.Doctor;
import com.lv.hospital.services.DoctorService;
import com.lv.hospital.util.PasswordUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button btLogin;

    @FXML
    private Button btSignIn;

    @FXML
    private Label lbCheckFields;

    @FXML
    private Label lbConfirmPassword;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfConfirmPassword;

    private Boolean isSignIn = false;

    private DoctorService ds = new DoctorService();


    @FXML
    void SignIn(ActionEvent event) {
        lbConfirmPassword.setVisible(true);
        tfConfirmPassword.setVisible(true);
        isSignIn = true;
    }

    @FXML
    void logIn(ActionEvent event) throws IOException{
        // New User
        if(isSignIn){
            if(checkIfPasswordIsValid()){
                Doctor doctor = new Doctor(null, tfName.getText(), tfPassword.getText());
                ds.save(doctor);
                instantiateDoctor(doctor);
                ds.close();
                changeToMenu();
            }
        }
        // Existent User
        else{
            Doctor doctor = ds.findByName(tfName.getText());
            if(doctor != null){
                if(PasswordUtils.compare(tfPassword.getText(), doctor.getPassword())){
                    instantiateDoctor(doctor);
                    ds.close();
                    changeToMenu();
                }
                else{
                    lbCheckFields.setText("Senha incorreta");
                }
            }
            else{
                lbCheckFields.setText("Usuário não encontrado");
            }
        }
    }

    public Boolean checkIfPasswordIsValid(){
        String password = tfPassword.getText();   
        String checkPassword = tfConfirmPassword.getText();

        if(tfName.getText().isEmpty() || tfPassword.getText().isEmpty() || tfConfirmPassword.getText().isEmpty()){
            lbCheckFields.setText("Preencha todos os campos");
            return false;
        }

        if(password.length() < 8){
            lbCheckFields.setText("A senha deve ter no mínimo 8 caracteres");
            return false;
        }

        if(!password.equals(checkPassword)){
            lbCheckFields.setText("As senhas não são iguais");
            return false;
        }

        return true;
    }

    public void changeToMenu() throws IOException{
        App.setRoot("views/menu");
    }

    public void instantiateDoctor(Doctor doctor){
        App.loggedDoctor = doctor;
    }

}
