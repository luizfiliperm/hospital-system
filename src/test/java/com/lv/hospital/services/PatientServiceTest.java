package com.lv.hospital.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.lv.hospital.entities.Doctor;
import com.lv.hospital.entities.GlasgowComaScale;
import com.lv.hospital.entities.Patient;
import com.lv.hospital.entities.enums.BrazilianState;

@TestMethodOrder(OrderAnnotation.class)
public class PatientServiceTest {

    private static PatientService patientService;
    private static Long savedPatientId;

    private static Long doctorId;

    @BeforeAll
    public static void setup() {
        patientService = new PatientService();
    }

    @AfterAll
    public static void cleanup() {
        patientService.close();
    }

    @Test
    @Order(0)
    public void createDoctor(){
        DoctorService doctorService = new DoctorService();
        Doctor doctor = new Doctor(null, "Test", "Test", BrazilianState.PB, "Neurologista", "doctor@gmail.com");
        doctorService.save(doctor);
        doctorService.close();
        doctorId = doctor.getId();

        assertNotNull(doctorId);
    }

    @Test
    @Order(1)
    public void testSave() {
        
        Patient patient = new Patient(null, "Test", 1, "(99) 99999-9999");
        patientService.save(patient, doctorId);
        savedPatientId = patient.getId();
        assertNotNull(savedPatientId);
    }


    @Test
    @Order(2)
    public void testFindAll() {
        List<Patient> patients = patientService.findAll();

        assertNotNull(patients);
        assertFalse(patients.isEmpty());
    }

    @Test
    @Order(3)
    public void testFindById() {
        Patient patient = patientService.findById(savedPatientId);

        assertNotNull(patient);
        assertEquals(savedPatientId, patient.getId());
    }

    @Test
    @Order(4)
    public void testUpdate() {
        Patient patient = patientService.findById(savedPatientId);

        Patient originalPatient = new Patient(patient.getId(), patient.getName(), patient.getAge(), patient.getPhone());
        patient.setName(patient.getName() + " Updated");
        patient.setAge(patient.getAge() + 1);
        patientService.update(patient);

        Patient updatedPatient = patientService.findById(savedPatientId);

        assertNotNull(updatedPatient);
        assertNotEquals(updatedPatient.getName(), originalPatient.getName());
        assertNotEquals(updatedPatient.getAge(), originalPatient.getAge());
    }

    @Test
    @Order(5)
    public void testCreateGlasgowComaScaleByPatientId(){
        GlasgowComaScale gcs = new GlasgowComaScale(null, 1, 2, 3, 1, patientService.findById(savedPatientId));
        patientService.updateGlasgowComaScaleByPatientId(savedPatientId, gcs);

        GlasgowComaScale gcsAfterSave = (patientService.findById(savedPatientId).getGlasgowComaScale());


        assertNotNull(gcsAfterSave.getId());
    }


    @Test
    @Order(6)
    public void testUpdateGlasgowComaScaleByPatientId() {
        Patient patient = patientService.findById(savedPatientId);
        GlasgowComaScale gcsBeforeUpdate = patient.getGlasgowComaScale();

        gcsBeforeUpdate = new GlasgowComaScale(gcsBeforeUpdate.getId(), gcsBeforeUpdate.getEyeOpening(), gcsBeforeUpdate.getVerbalResponse(), gcsBeforeUpdate.getMotorResponse(), gcsBeforeUpdate.getPupilResponse(), gcsBeforeUpdate.getPatient());

        GlasgowComaScale gcsToUpdate = new GlasgowComaScale(null, 4, 5, 6, 2, patient);

        patientService.updateGlasgowComaScaleByPatientId(savedPatientId, gcsToUpdate);

        GlasgowComaScale gcsAfterUpdate = patientService.findById(savedPatientId).getGlasgowComaScale();


        assertNotNull(gcsAfterUpdate);
        assertEquals(gcsBeforeUpdate.getId(), gcsAfterUpdate.getId());
        assertNotEquals(gcsBeforeUpdate.getTotal(), gcsAfterUpdate.getTotal());
    }

    @Test
    @Order(7)
    public void testFindAllByDoctorId() {
        List<Patient> patients = patientService.findAllByDoctorId(doctorId);

        assertNotNull(patients);
        assertFalse(patients.isEmpty());
        assertEquals(doctorId, patients.get(0).getDoctor().getId());
    }

    @Test
    @Order(8)
    public void testDeleteById() {
        patientService.deleteById(savedPatientId);

        Patient patient = patientService.findById(savedPatientId);

        assertNull(patient);
    }

}