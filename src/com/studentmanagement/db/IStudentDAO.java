package com.studentmanagement.db;

import com.studentmanagement.model.Student;
import java.util.List;

public interface IStudentDAO {
    boolean addStudent(Student student);
    List<Student> getAllStudents();
    boolean updateStudent(Student student);
    boolean deleteStudent(int id);
    Student getStudentById(int id);
}