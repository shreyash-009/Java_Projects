package com.codebridge.studentmanagementsystem.service;

import com.codebridge.studentmanagementsystem.model.Student;

import java.util.List;

public interface StudentService {
    public void SaveStudent(Student student);
    public List<Student> getStudentList();
    public List<Student> getStudentByName(String name);
    public void deleteStudent(int id);
    public void updateStudent(Student student);
}

