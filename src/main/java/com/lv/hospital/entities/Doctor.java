package com.lv.hospital.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lv.hospital.entities.enums.BrazilianState;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import static com.lv.hospital.util.PasswordUtils.*;

@Entity
@Table(name = "doctor")
public class Doctor implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private BrazilianState state;

    @Column(name = "crm")
    private String crm;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Patient> patients = new ArrayList<>();

    public Doctor() {
    }

    public Doctor(Long id, String name, String password, BrazilianState state) {
        this.id = id;
        this.name = name;
        this.state = state;
        setPassword(password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = encrypt(password);
    }

    public boolean comparePassword(String password){
        return compare(password, this.password);
    }

    public void addPatient(Patient patient){
        patients.add(patient);
    }

    public List<Patient> getPatients(){
        return patients;
    }

    public BrazilianState getState() {
        return state;
    }

    public void setState(BrazilianState state) {
        this.state = state;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = generateCRM();
    }

    public String generateCRM() {
        String idString = String.format("%06d", id);
        String stateAbbreviation = state.getAbbreviation();
        return stateAbbreviation + "-" + idString;
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
        Doctor other = (Doctor) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }



}
