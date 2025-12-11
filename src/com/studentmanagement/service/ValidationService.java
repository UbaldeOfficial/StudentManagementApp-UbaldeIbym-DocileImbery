package com.studentmanagement.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class ValidationService {
    
    // UPDATED RegEx patterns - LESS STRICT
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    
    private static final Pattern PHONE_PATTERN = 
        Pattern.compile("^\\d{10,15}$");  // Changed: 10-15 digits
    
    private static final Pattern STUDENT_ID_PATTERN = 
        Pattern.compile("^[A-Za-z]{2,}[A-Za-z0-9]*$");  // Changed: letters then alphanumeric
    
    private static final Pattern NAME_PATTERN = 
        Pattern.compile("^[A-Za-z\\s.]{3,100}$");  // Added . and increased length
    
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) return false;
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }
    
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) return false;
        String cleanPhone = phone.trim().replaceAll("[\\s()-]", "");
        return PHONE_PATTERN.matcher(cleanPhone).matches();
    }
    
    public static boolean isValidStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) return false;
        return STUDENT_ID_PATTERN.matcher(studentId.trim()).matches();
    }
    
    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) return false;
        return NAME_PATTERN.matcher(name.trim()).matches();
    }
    
    public static boolean isValidDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) return false;
        try {
            LocalDate.parse(dateStr.trim());
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    public static boolean isValidSemester(String semesterStr) {
        if (semesterStr == null || semesterStr.trim().isEmpty()) return false;
        try {
            int semester = Integer.parseInt(semesterStr.trim());
            return semester >= 1 && semester <= 12;  // Increased to 12
        } catch (NumberFormatException e) {
            return false;
        }
    }
}