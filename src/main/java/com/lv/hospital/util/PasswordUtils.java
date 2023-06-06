package com.lv.hospital.util;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class PasswordUtils {
        
        public static String encrypt(String password) {
            BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
            String criptedPassword = passwordEncryptor.encryptPassword(password);
            return criptedPassword;
        }
    
        public static boolean compare(String password, String encryptedPassword) {
            BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
            return passwordEncryptor.checkPassword(password, encryptedPassword);
        }
}
