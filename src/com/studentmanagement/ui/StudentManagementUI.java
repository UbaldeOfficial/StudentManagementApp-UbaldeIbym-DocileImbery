package com.studentmanagement.ui;

import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentService;
import com.studentmanagement.service.ValidationService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;

public class StudentManagementUI extends JFrame {
    private StudentService studentService;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JTextField txtStudentId, txtFullName, txtEmail, txtPhone, txtCourse, txtSemester, txtEnrollmentDate;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnLoad;
    private JComboBox<String> courseComboBox;
    
    public StudentManagementUI() {
        studentService = new StudentService();
        initializeUI();
        loadStudents();
    }
    
    private void initializeUI() {
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Input Form Panel (North)
        JPanel inputPanel = createInputPanel();
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        
        // Table Panel (Center)
        JPanel tablePanel = createTablePanel();
        mainPanel.add(new JScrollPane(tablePanel), BorderLayout.CENTER);
        
        // Button Panel (South)
        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 4, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Student Information"));
        
        // Initialize text fields
        txtStudentId = new JTextField();
        txtFullName = new JTextField();
        txtEmail = new JTextField();
        txtPhone = new JTextField();
        txtCourse = new JTextField();
        txtSemester = new JTextField();
        txtEnrollmentDate = new JTextField();
        
        // Course combo box
        String[] courses = {"Computer Science", "Information Technology", 
                          "Software Engineering", "Data Science", "Cybersecurity"};
        courseComboBox = new JComboBox<>(courses);
        
        // Add components to panel
        panel.add(new JLabel("Student ID:"));
        panel.add(txtStudentId);
        panel.add(new JLabel("Full Name:"));
        panel.add(txtFullName);
        
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Phone:"));
        panel.add(txtPhone);
        
        panel.add(new JLabel("Course:"));
        panel.add(courseComboBox);
        panel.add(new JLabel("Semester:"));
        panel.add(txtSemester);
        
        panel.add(new JLabel("Enrollment Date (YYYY-MM-DD):"));
        panel.add(txtEnrollmentDate);
        panel.add(new JLabel("")); // Empty label for spacing
        panel.add(new JLabel(""));
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Table model
        String[] columns = {"ID", "Student ID", "Full Name", "Email", "Phone", 
                          "Course", "Semester", "Enrollment Date"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        
        studentTable = new JTable(tableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Add mouse listener for row selection
        studentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadSelectedStudentToForm();
            }
        });
        
        panel.add(new JScrollPane(studentTable), BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        btnAdd = new JButton("Add Student");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        btnLoad = new JButton("Load/Refresh");
        
        // Set button colors
        btnAdd.setBackground(new Color(76, 175, 80));
        btnAdd.setForeground(Color.WHITE);
        btnUpdate.setBackground(new Color(33, 150, 243));
        btnUpdate.setForeground(Color.WHITE);
        btnDelete.setBackground(new Color(244, 67, 54));
        btnDelete.setForeground(Color.WHITE);
        btnClear.setBackground(new Color(255, 152, 0));
        btnClear.setForeground(Color.WHITE);
        btnLoad.setBackground(new Color(156, 39, 176));
        btnLoad.setForeground(Color.WHITE);
        
        // Add action listeners
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });
        
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadStudents();
            }
        });
        
        panel.add(btnAdd);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        panel.add(btnClear);
        panel.add(btnLoad);
        
        return panel;
    }
    
    private void addStudent() {
        try {
            if (!validateInput()) return;
            
            Student student = createStudentFromForm();
            
            if (studentService.addStudent(student)) {
                JOptionPane.showMessageDialog(this, "Student added successfully!");
                clearForm();
                loadStudents();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add student. Check validation!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateStudent() {
        try {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a student to update!");
                return;
            }
            
            if (!validateInput()) return;
            
            Student student = createStudentFromForm();
            // Get the ID from the selected row
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            student.setId(id);
            
            if (studentService.updateStudent(student)) {
                JOptionPane.showMessageDialog(this, "Student updated successfully!");
                clearForm();
                loadStudents();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update student!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void deleteStudent() {
        try {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a student to delete!");
                return;
            }
            
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete this student?", 
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (studentService.deleteStudent(id)) {
                    JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                    clearForm();
                    loadStudents();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete student!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void loadStudents() {
        try {
            tableModel.setRowCount(0); // Clear table
            
            List<Student> students = studentService.getAllStudents();
            for (Student student : students) {
                Object[] row = {
                    student.getId(),
                    student.getStudentId(),
                    student.getFullName(),
                    student.getEmail(),
                    student.getPhone(),
                    student.getCourse(),
                    student.getSemester(),
                    student.getEnrollmentDate().toString()
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading students: " + e.getMessage());
        }
    }
    
    private void loadSelectedStudentToForm() {
        try {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow >= 0) {
                txtStudentId.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtFullName.setText(tableModel.getValueAt(selectedRow, 2).toString());
                txtEmail.setText(tableModel.getValueAt(selectedRow, 3).toString());
                txtPhone.setText(tableModel.getValueAt(selectedRow, 4).toString());
                courseComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 5).toString());
                txtSemester.setText(tableModel.getValueAt(selectedRow, 6).toString());
                txtEnrollmentDate.setText(tableModel.getValueAt(selectedRow, 7).toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading student data!");
        }
    }
    
    private void clearForm() {
        txtStudentId.setText("");
        txtFullName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        courseComboBox.setSelectedIndex(0);
        txtSemester.setText("");
        txtEnrollmentDate.setText("");
        studentTable.clearSelection();
    }
    
    private boolean validateInput() {
        StringBuilder errors = new StringBuilder();
        
        // Validate Student ID
        if (!ValidationService.isValidStudentId(txtStudentId.getText())) {
            errors.append("Student ID must be in format 'AB123' (2 letters + numbers)\n");
        }
        
        // Validate Full Name
        if (!ValidationService.isValidName(txtFullName.getText())) {
            errors.append("Full name must be 3-50 letters only\n");
        }
        
        // Validate Email
        if (!ValidationService.isValidEmail(txtEmail.getText())) {
            errors.append("Invalid email format\n");
        }
        
        // Validate Phone
        if (!ValidationService.isValidPhone(txtPhone.getText())) {
            errors.append("Phone must be 10 digits\n");
        }
        
        // Validate Semester
        if (!ValidationService.isValidSemester(txtSemester.getText())) {
            errors.append("Semester must be between 1 and 8\n");
        }
        
        // Validate Date
        if (!ValidationService.isValidDate(txtEnrollmentDate.getText())) {
            errors.append("Date must be in YYYY-MM-DD format\n");
        }
        
        if (errors.length() > 0) {
            JOptionPane.showMessageDialog(this, errors.toString(), 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private Student createStudentFromForm() {
        Student student = new Student();
        student.setStudentId(txtStudentId.getText());
        student.setFullName(txtFullName.getText());
        student.setEmail(txtEmail.getText());
        student.setPhone(txtPhone.getText());
        student.setCourse(courseComboBox.getSelectedItem().toString());
        student.setSemester(Integer.parseInt(txtSemester.getText()));
        student.setEnrollmentDate(LocalDate.parse(txtEnrollmentDate.getText()));
        
        return student;
    }
    
    // UPDATED MAIN METHOD - Java 7+ compatible
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Set look and feel
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    
                    StudentManagementUI app = new StudentManagementUI();
                    app.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}