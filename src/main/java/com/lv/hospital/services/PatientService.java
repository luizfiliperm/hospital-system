package com.lv.hospital.services;

import java.util.List;

import com.lv.hospital.entities.Doctor;
import com.lv.hospital.entities.GlasgowComaScale;
import com.lv.hospital.entities.Patient;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PatientService {
    
    private EntityManagerFactory emf;
    private EntityManager em;

    public PatientService() {
        emf = Persistence.createEntityManagerFactory("hospital");
        em = emf.createEntityManager();
    }
    
    public void save(Patient patient, Doctor doctor) {
        
        if(doctor != null){
            patient.setDoctor(doctor);

            em.getTransaction().begin();
            em.persist(patient);
            patient.setMrn(patient.generateMRN());
            em.getTransaction().commit();
        }
    }

    public List<Patient> findAll() {
        return em.createQuery("SELECT p FROM Patient p", Patient.class).getResultList();
    }

    public Patient findById(Long id) {
        return em.find(Patient.class, id);
    }

    public void deleteById(Long id) {
        em.getTransaction().begin();
        Patient obj = findById(id);
        em.remove(obj);
        em.getTransaction().commit();
    }
    
    public void update(Patient obj) {
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    public void updateGlasgowComaScaleByPatientId(Long patientId, GlasgowComaScale gcs){
        em.getTransaction().begin();
        Patient p = findById(patientId);

        GlasgowComaScale existingGcs = p.getGlasgowComaScale();


        if(existingGcs == null){
            gcs.updateData();
            p.setGlasgowComaScale(gcs);
        }else{
            existingGcs.setEyeOpening(gcs.getEyeOpening());
            existingGcs.setVerbalResponse(gcs.getVerbalResponse());
            existingGcs.setMotorResponse(gcs.getMotorResponse());
            existingGcs.setPupilResponse(gcs.getPupilResponse());
            existingGcs.updateData();
        }
        em.merge(p);
        em.getTransaction().commit();
    }

    public List<Patient> findAllByDoctorId(Long doctorId) {
        return em.createQuery("SELECT p FROM Patient p WHERE p.doctor.id = :doctorId", Patient.class)
            .setParameter("doctorId", doctorId)
            .getResultList();
    }

    public void close() {
        em.close();
        emf.close();
    }
}
