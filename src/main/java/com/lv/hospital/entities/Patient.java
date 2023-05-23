package com.lv.hospital.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mrn")
    private String mrn;

    @OneToOne(optional = true, cascade = jakarta.persistence.CascadeType.ALL)
    @JoinColumn(name = "glasgow_coma_scale_id")
    private GlasgowComaScale glasgowComaScale;
     
    public Patient() {
    }

    public Patient(Long id, String name, Integer age, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.mrn = generateMRN();
    }
    
    public Patient(Long id, String name, Integer age, String phone, GlasgowComaScale glasgowComaScale) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.mrn = generateMRN();
        this.glasgowComaScale = glasgowComaScale;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public GlasgowComaScale getGlasgowComaScale() {
        return glasgowComaScale;
    }

    public void setGlasgowComaScale(GlasgowComaScale glasgowComaScale) {
        this.glasgowComaScale = glasgowComaScale;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMrn() {
        return mrn;
    }

    public void setMrn(String mrn) {
        this.mrn = mrn;
    }

    public String generateMRN(){
        String idString = String.valueOf(id);
        String zeros = "000000";
        String mrn = zeros.substring(idString.length()) + idString;
        return mrn;
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
        Patient other = (Patient) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Patient [id=" + id + ", name=" + name + ", age=" + age + "]";
    }


    

    

}
