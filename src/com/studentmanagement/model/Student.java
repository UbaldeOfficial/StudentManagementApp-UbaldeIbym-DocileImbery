package com.studentmanagement.model;

import java.time.LocalDate;

public class Student {
    private int id;
    private String studentId;
    private String fullName;
    private String email;
    private String phone;
    private String course;
    private int semester;
    private LocalDate enrollmentDate;
    
    // Constructor
    public Student() {}
    
    public Student(String studentId, String fullName, String email, 
                   String phone, String course, int semester, LocalDate enrollmentDate) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.course = course;
        this.semester = semester;
        this.enrollmentDate = enrollmentDate;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    
    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }
    
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { 
        this.enrollmentDate = enrollmentDate; 
    }
}