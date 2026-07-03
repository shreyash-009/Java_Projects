package com.codebridge.studentmanagementsystem.view;

import com.codebridge.studentmanagementsystem.model.SignUp;
import com.codebridge.studentmanagementsystem.service.SignupService;
import com.codebridge.studentmanagementsystem.service.SignupServiceImpl;
import com.codebridge.studentmanagementsystem.service.StudentService;
import com.codebridge.studentmanagementsystem.service.StudentServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SignupPage {
    public SignupPage(){
        JFrame frame = new JFrame("Signup Page");
        frame.setSize(500,500);
        frame.setResizable(false);
        frame.setLayout(null);

        JLabel l1 = new JLabel("Username");
        l1.setBounds(110,150,100,50);
        frame.add(l1);

        JTextField t1 = new JTextField();
        t1.setBounds(220,160,200,30);
        frame.add(t1);

        JLabel l2 = new JLabel("Password");
        l2.setBounds(110,220,100,50);
        frame.add(l2);

        JPasswordField p1 = new JPasswordField();
        p1.setBounds(220,220,200,30);
        frame.add(p1);

        JButton b1 = new JButton("Sign UP");
        b1.setBounds(300,300,100,30);
        frame.add(b1);

        JLabel alog = new JLabel("Already have an account?");
        alog.setBounds(100,340,150,30);
        frame.add(alog);

        JButton blog = new JButton("LOGIN");
        blog.setBounds(100,300,100,30);
        frame.add(blog);


        ActionListener a1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = t1.getText();
                String password = p1.getText();

                SignUp signup = new SignUp();
                signup.setUsername(username);
                signup.setPassword(password);

                SignupService signupService = new SignupServiceImpl();
                boolean usernameExist = signupService.usernameExists(username);
                if (usernameExist) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Username Already Exists",
                            "Success",
                            JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    signupService.SaveCredentials(signup);

                    JOptionPane.showMessageDialog(
                            null,
                            "Signup Successful",
                            "Sucess",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    frame.dispose();
                    new StudentDisplay();
                }
            }
        };b1.addActionListener(a1);

        ActionListener logactn = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LoginForm();
            }
        };blog.addActionListener(logactn);

        frame.setVisible(true);
    }
    public static void main(String[] args){
        SignupPage obj = new SignupPage();
    }

}
