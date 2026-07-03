package com.codebridge.studentmanagementsystem.service;

import com.codebridge.studentmanagementsystem.databaseconnection.DatabaseConnection;
import com.codebridge.studentmanagementsystem.model.SignUp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignupServiceImpl implements SignupService {
    PreparedStatement ps = null;

    @Override
    public void SaveCredentials(SignUp signup) {
        String sql = "insert into savecredentials(username,password)values(?,?)";
        try{
            ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setString(1,signup.getUsername());
            ps.setString(2,signup.getPassword());
            ps.executeUpdate();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    @Override
    public boolean checkCredentials(SignUp credentials) {
        String sql = "Select * from savecredentials where username =? and password=?";
        boolean found = false;
        try{
           ps=DatabaseConnection.getConnection().prepareStatement(sql);
           ps.setString(1,credentials.getUsername());
           ps.setString(2,credentials.getPassword());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                found=true;
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
        return found;
    }

    @Override
    public boolean usernameExists(String username) {
        String sql = "Select * from savecredentials where username = ?";
        boolean userExist = false;
        try{
            ps = DatabaseConnection.getConnection().prepareStatement(sql);
           ps.setString(1,username);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               userExist = true;
           }
        }catch (Exception ex){
            System.out.println(ex);
        }
        return userExist;
    }


}
