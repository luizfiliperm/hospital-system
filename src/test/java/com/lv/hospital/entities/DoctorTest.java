package com.lv.hospital.entities;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.lv.hospital.entities.enums.BrazilianState;
import com.lv.hospital.util.PasswordUtils;

public class DoctorTest {
    
    @Test
    public void EqualPasswords() {
        Doctor doctor = new Doctor(null, "Test", "Test", BrazilianState.PB, "Neurologista", "doctor@email.com");
        String encryptedPassword = doctor.getPassword();
        assertTrue(PasswordUtils.compare("Test", encryptedPassword));
    }

    @Test
    public void NotEqualPasswords() {
        Doctor doctor = new Doctor(null, "Test", "Test", BrazilianState.PB, "Neurologista", "doctor@email.com");
        String encryptedPassword = doctor.getPassword();
        assertFalse(PasswordUtils.compare("Test1", encryptedPassword));
    }
}
