package com.lv.hospital.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "glasgow_coma_scale")
public class GlasgowComaScale implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id;

    @Column(name = "eye_opening")
    private Integer eyeOpening;

    @Column(name = "verbal_response")
    private Integer verbalResponse;

    @Column(name = "motor_response")
    private Integer motorResponse;

    @Column(name = "pupil_response")
    private Integer pupilResponse;

    @Column(name = "total")
    private Integer total;

    @Column(name = "result")
    private String result;

    @OneToOne(mappedBy = "glasgowComaScale")
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public GlasgowComaScale() {
    }

    public GlasgowComaScale(Long id, Integer eyeOpening, Integer verbalResponse, Integer motorResponse, Integer pupilResponse, Patient patient) {
        this.id = id;
        this.eyeOpening = eyeOpening;
        this.verbalResponse = verbalResponse;
        this.motorResponse = motorResponse;
        this.pupilResponse = pupilResponse;
        this.patient = patient;
        this.total = calculateTotal();
        this.result = calculateResult();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEyeOpening() {
        return eyeOpening;
    }

    public void setEyeOpening(Integer eyeOpening) {
        this.eyeOpening = eyeOpening;
    }

    public Integer getVerbalResponse() {
        return verbalResponse;
    }

    public void setVerbalResponse(Integer verbalResponse) {
        this.verbalResponse = verbalResponse;
    }

    public Integer getMotorResponse() {
        return motorResponse;
    }

    public void setMotorResponse(Integer motorResponse) {
        this.motorResponse = motorResponse;
    }

    public void setPupilResponse(Integer pupilResponse) {
        this.pupilResponse = pupilResponse;
    }

    public Integer getPupilResponse() {
        return pupilResponse;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getResult() {
        return result;
    }
    
    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GlasgowComaScale other = (GlasgowComaScale) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    public Integer calculateTotal() {
        return eyeOpening + verbalResponse + motorResponse - pupilResponse;
    }

    public String calculateResult() {
        if (total <= 3) {
            return "Deep coma";
        } else if (total <= 4) {
            return "Severe coma";
        } else if (total <= 7) {
            return "Moderate coma";
        } else if (total <= 11) {
            return "Superficial coma";
        } else {
            return "Normality";
        }
    }

    public void updateData(){
        this.total = calculateTotal();
        this.result = calculateResult();
    }
}
