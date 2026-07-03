package com.codebridge.studentmanagementsystem.view;

import com.codebridge.studentmanagementsystem.model.Student;
import com.codebridge.studentmanagementsystem.service.StudentService;
import com.codebridge.studentmanagementsystem.service.StudentServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentForm {
    JTextField t1,t2,t3;
    JRadioButton male,female,others;
    JComboBox facultty,yr;
    JLabel idlabel;
    public StudentForm(){
        JFrame frame  = new JFrame("Student");
        frame.setSize(500,500);
        frame.setResizable(false);
        frame.setLayout(null);

        JLabel l1 = new JLabel("Name :");
        l1.setBounds(10,20,100,30);
        frame.add(l1);

         t1 =new JTextField();
        t1.setBounds(120,20,150,30);
        frame.add(t1);

        idlabel = new JLabel();
        idlabel.setBounds(300,10,100,30);
        frame.add(idlabel);



        JLabel l2 = new JLabel("Address :");
        l2.setBounds(10,60,100,30);
        frame.add(l2);

         t2 = new JTextField();
        t2.setBounds(120,60,150,30);
        frame.add(t2);

        JLabel l3 = new JLabel("Contact number :");
        l3.setBounds(10,100,100,30);
        frame.add(l3);

         t3 = new JTextField();
        t3.setBounds(120,100,150,30);
        frame.add(t3);

        JLabel l4 = new JLabel("Gender :");
        l4.setBounds(10,140,100,30);
        frame.add(l4);

        male = new JRadioButton("male");
        male.setBounds(120,140,100,30);
        frame.add(male);

         female = new JRadioButton("female");
        female.setBounds(230,140,100,30);
        frame.add(female);

        others = new JRadioButton("others");
        others.setBounds(340,140,150,30);
        frame.add(others);

        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);
        bg.add(others);

        JLabel faculty = new JLabel("Faculty :");
        faculty.setBounds(40,190,100,30);
        frame.add(faculty);

        String faculttyList[] = {"BCA","BIM","BSC.CSIT"};
       facultty = new JComboBox<>(faculttyList);
        facultty.setBounds(150,190,100,30);
        frame.add(facultty);

        JLabel yl = new JLabel("Year :");
        yl.setBounds(40,240,100,30);
        frame.add(yl);

        String Year[]={"1st Year","2nd Year","3rd Year","4th Year"};
         yr = new JComboBox<>(Year);
        yr.setBounds(150,240,100,30);
        frame.add(yr);

        JButton clr = new JButton("Clear");
        clr.setBounds(20,300,100,30);
        frame.add(clr);

        JButton del = new JButton("Cancel");
        del.setBounds(170,300,100,30);
        frame.add(del);

        ActionListener de1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
              new  StudentDisplay();
            }
        };del.addActionListener(de1);


        ActionListener a1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               t1.setText("  ");
               t2.setText(" ");
               t3.setText("  ");
            }
        };clr.addActionListener(a1);

        JButton btnn = new JButton("Submit");
        btnn.setBounds(300,300,100,30);
        frame.add(btnn);

        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idlabel.getText();
                String name = t1.getText();
                String address = t2.getText();
                Long contact = Long.parseLong(t3.getText());

                String gender;
                if(male.isSelected()){
                    gender = "male";
                } else if (female.isSelected()) {
                    gender = "female";
                }else{
                    gender = "others";
                }

                String facu = (String) facultty.getSelectedItem();
                String years = (String) yr.getSelectedItem();

                Student student = new Student();
                student.setName(name);
                student.setAddress(address);
                student.setContact(contact);
                student.setGender(gender);
                student.setFaculty(facu);
                student.setYear(years);

                StudentService studentService = new StudentServiceImpl();
                if (id.isEmpty() || id == null){
                    studentService.SaveStudent(student);
                }else{
                    student.setId(Integer.parseInt(id));
                    studentService.updateStudent(student);
                }




                JOptionPane.showMessageDialog(
                        null,
                        id.isEmpty()?"Data Saved Sucessfully":"User updated sucessfully",
                        "Sucess",
                        JOptionPane.INFORMATION_MESSAGE
                );
                frame.dispose();
                new StudentDisplay();



            }
        };btnn.addActionListener(a);


        frame.setVisible(true);
    }
    public static void main(String[] args){
        StudentForm obj = new StudentForm();

    }
}
