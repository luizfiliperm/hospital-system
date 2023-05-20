package com.lv.hospital.services;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.lv.hospital.entities.Doctor;

@TestMethodOrder(OrderAnnotation.class)
public class DoctorServiceTest {
    
    private static DoctorService doctorService;
    private static Long savedDoctorId;

    @BeforeAll
    public static void setup() {
        doctorService = new DoctorService();
    }

    @AfterAll
    public static void cleanup() {
        doctorService.close();
    }

    @Test
    @Order(1)
    public void testSave() {
        Doctor doctor = new Doctor(null, "Test", "Test");
        doctorService.save(doctor);
        savedDoctorId = doctor.getId();
        assertNotNull(savedDoctorId);
    }

    @Test
    @Order(2)
    public void testFindAll() {
        assertNotNull(doctorService.findAll());
    }

    @Test
    @Order(3)
    public void testFindById() {
        assertNotNull(doctorService.findById(savedDoctorId));
    }

    @Test
    @Order(4)
    public void testUpdate() {
        Doctor doctor = doctorService.findById(savedDoctorId);

        Doctor originalDoctor = new Doctor(doctor.getId(), doctor.getName(), doctor.getPassword());

        doctor.setName(doctor.getName() + " Updated");
        
        doctorService.update(doctor);

        Doctor updatedDoctor = doctorService.findById(savedDoctorId);

        assertNotNull(updatedDoctor);
        assertNotEquals(updatedDoctor.getName(), originalDoctor.getName());

    }

    // @Test
    // @Order(5)
    // public void testDeleteById() {
    //     doctorService.deleteById(savedDoctorId);
    //     Doctor doctor = doctorService.findById(savedDoctorId);

    //     assertNull(doctor);
    // }

}
