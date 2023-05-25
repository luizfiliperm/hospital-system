package com.lv.hospital.services;

import java.util.List;

import com.lv.hospital.entities.Doctor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;

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
        obj.setCrm(obj.generateCRM());
        em.getTransaction().commit();
    }

    public List<Doctor> findAll() {
        return em.createQuery("SELECT d FROM Doctor d", Doctor.class).getResultList();
    }

    public Doctor findById(Long id) {
        return em.find(Doctor.class, id);
    }

    public Doctor findByName(String name){
        try{
            return em.createQuery("SELECT d FROM Doctor d WHERE d.name = :name", Doctor.class)
            .setParameter("name", name)
            .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

    public Doctor findByCrm(String crm){
        try{
            return em.createQuery("SELECT d FROM Doctor d WHERE d.crm = :crm", Doctor.class)
            .setParameter("crm", crm)
            .getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

    public Boolean emailExists(String email){
        try{
            em.createQuery("SELECT d FROM Doctor d WHERE d.email = :email", Doctor.class)
            .setParameter("email", email)
            .getSingleResult();
            return true;
        }catch(NoResultException e){
            return false;
        }
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
