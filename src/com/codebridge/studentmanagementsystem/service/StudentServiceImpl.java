package com.codebridge.studentmanagementsystem.service;

import com.codebridge.studentmanagementsystem.databaseconnection.DatabaseConnection;
import com.codebridge.studentmanagementsystem.model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    PreparedStatement ps = null;

    @Override
    public void SaveStudent(Student student) {
        String sql = "insert into students(name,address,contact,gender,faculty,years)values(?,?,?,?,?,?)";
        try{
        ps= DatabaseConnection.getConnection().prepareStatement(sql);

                ps.setString(1, student.getName());
                ps.setString(2, student.getAddress());
                ps.setLong(3, student.getContact());
                ps.setString(4, student.getGender());
                ps.setString(5, student.getFaculty());
                ps.setString(6, student.getYear());
                ps.executeUpdate();




    }catch (Exception ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Student> getStudentList() {
        List<Student> studentList = new ArrayList<>();
        String sql = "select * from students";
         try{
             ps = DatabaseConnection.getConnection().prepareStatement(sql);
                 ResultSet rs = ps.executeQuery();

                 while(rs.next()){
                     int id = rs.getInt("id");
                     String name = rs.getString("name");
                     String address = rs.getString("address");
                     Long contact = rs.getLong("contact");
                     String gender = rs.getString("gender");
                     String faculty = rs.getString("faculty");
                     String years = rs.getString("years");

                     Student s1 = new Student();
                     s1.setId(id);
                     s1.setName(name);
                     s1.setAddress(address);
                     s1.setContact(contact);
                     s1.setGender(gender);
                     s1.setFaculty(faculty);
                     s1.setYear(years);

                     studentList.add(s1);


                 }
             } catch (Exception e) {
                 System.out.println(e);
             }



        return studentList;

    }

    @Override
    public List<Student> getStudentByName(String name) {
        List <Student> studentList = new ArrayList<>();
        String sql = "select * from students where name LIKE ?";
        try{
          ps = DatabaseConnection.getConnection().prepareStatement(sql);
          ps.setString(1,"%"+name+"%");

          ResultSet rs = ps.executeQuery();
          while(rs.next()){
              Student s1 = new Student();

              s1.setId(rs.getInt("id"));
              s1.setName(rs.getString("name"));
              s1.setAddress(rs.getString("address"));
              s1.setContact(rs.getLong("contact"));
              s1.setGender(rs.getString("gender"));
              s1.setFaculty(rs.getString("faculty"));
              s1.setYear(rs.getString("years"));

              studentList.add(s1);
          }
        }catch (Exception ex){
            System.out.println(ex);
        }
        return studentList;
    }

    @Override
    public void deleteStudent(int id) {
        String sql = "Delete From students where id =?";
        try{
            ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();

        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "update students set name = ? , address = ? , contact = ? , gender = ? , faculty = ? , years = ? where id = ?";
        try{
            ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setString(2, student.getAddress());
            ps.setLong(3,student.getContact());
            ps.setString(4,student.getGender());
            ps.setString(5, student.getFaculty());
            ps.setString(6,student.getYear());
            ps.setInt(7,student.getId());
            ps.executeUpdate();

        }catch (Exception ex){
            System.out.println(ex);
        }

    }
}