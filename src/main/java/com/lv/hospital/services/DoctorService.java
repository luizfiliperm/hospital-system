package com.lv.hospital.services;

import java.util.List;

import com.lv.hospital.entities.Doctor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class DoctorService {
    
    private EntityManagerFactory emf;
    private EntityManager em;

    public DoctorService() {
        emf = jakarta.persistence.Persistence.createEntityManagerFactory("hospital");
        em = emf.createEntityManager();
    }

    public void save(Doctor obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

    public List<Doctor> findAll() {
        return em.createQuery("SELECT d FROM Doctor d", Doctor.class).getResultList();
    }

    public Doctor findById(Long id) {
        return em.find(Doctor.class, id);
    }

    public void deleteById(Long id) {
        em.getTransaction().begin();
        Doctor obj = findById(id);
        em.remove(obj);
        em.getTransaction().commit();
    }

    public void update(Doctor obj) {
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }


    public void close() {
        em.close();
        emf.close();
    }

}
