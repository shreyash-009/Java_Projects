package com.codebridge.studentmanagementsystem.view;

import com.codebridge.studentmanagementsystem.model.SignUp;
import com.codebridge.studentmanagementsystem.service.SignupService;
import com.codebridge.studentmanagementsystem.service.SignupServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {

    public LoginForm(){
        JFrame frame = new JFrame("Login Page");
        frame.setSize(400,400);
        frame.setResizable(false);
        frame.setLayout(null);

        JLabel l1 = new JLabel("Username");
        l1.setBounds(70,90,80,30);
        frame.add(l1);

        JTextField t1 = new JTextField();
        t1.setBounds(160,90,100,30);
        frame.add(t1);

        JLabel l2 = new JLabel("Password");
        l2.setBounds(70,130,80,30);
        frame.add(l2);

        JTextField t2 =  new JTextField();
        t2.setBounds(160,130,100,30);
        frame.add(t2);

        JButton btn1 = new JButton("Signup");
        btn1.setBounds(70,170,80,30);
        frame.add(btn1);

        ActionListener al1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SignupPage();

            }
        };btn1.addActionListener(al1);

        JButton btn2 = new JButton("Login");
        btn2.setBounds(160,170,100,30);
        frame.add(btn2);

        ActionListener al2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = t1.getText();
                String password = t2.getText();

                SignUp credentials = new SignUp();
                credentials.setUsername(username);
                credentials.setPassword(password);

                SignupService signupService =new SignupServiceImpl();

            }
        };btn2.addActionListener(al2);


        frame.setVisible(true);
    }
    public static void main(String[] args){
        LoginForm obj = new LoginForm();
    }
}
