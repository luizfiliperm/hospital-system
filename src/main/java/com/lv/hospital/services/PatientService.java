package com.lv.hospital.services;

import java.util.List;

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
    
    public void save(Patient obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
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

    public void updateGlasgowComaScaleByUserId(Long userId, GlasgowComaScale gcs){
        gcs.updateData();
        em.getTransaction().begin();
        Patient p = findById(userId);
        p.setGlasgowComaScale(gcs);
        em.merge(p);
        em.getTransaction().commit();
    }

    public void close() {
        em.close();
        emf.close();
    }
}
