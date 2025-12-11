package com.studentmanagement.service;

import com.studentmanagement.db.StudentDAO;
import com.studentmanagement.model.Student;
import java.util.List;

public class StudentService {
    private StudentDAO studentDAO;
    
    public StudentService() {
        this.studentDAO = new StudentDAO();
    }
    
    public boolean addStudent(Student student) {
        // Validate all fields before adding
        if (!ValidationService.isValidStudentId(student.getStudentId()) ||
            !ValidationService.isValidName(student.getFullName()) ||
            !ValidationService.isValidEmail(student.getEmail()) ||
            !ValidationService.isValidPhone(student.getPhone())) {
            return false;
        }
        return studentDAO.addStudent(student);
    }
    
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }
    
    public boolean updateStudent(Student student) {
        // Validate all fields before updating
        if (!ValidationService.isValidStudentId(student.getStudentId()) ||
            !ValidationService.isValidName(student.getFullName()) ||
            !ValidationService.isValidEmail(student.getEmail()) ||
            !ValidationService.isValidPhone(student.getPhone())) {
            return false;
        }
        return studentDAO.updateStudent(student);
    }
    
    public boolean deleteStudent(int id) {
        return studentDAO.deleteStudent(id);
    }
    
    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id);
    }
}