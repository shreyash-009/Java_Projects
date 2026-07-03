package com.codebridge.studentmanagementsystem.view;

import com.codebridge.studentmanagementsystem.model.Student;
import com.codebridge.studentmanagementsystem.service.StudentService;
import com.codebridge.studentmanagementsystem.service.StudentServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class StudentDisplay {
    StudentService studentService = new StudentServiceImpl();
    JTable table;
    public StudentDisplay(){

        JFrame frame = new JFrame("Students Details");
        frame.setSize(750,400);
        frame.setResizable(false);
        frame.setLayout(null);
        String columnName []={"Id","Name","Address","Contact Number","Gender","Faculty","Year"};

        JLabel l1 = new JLabel("Search");
        l1.setBounds(500,10,70,30);
        frame.add(l1);

        JTextField t1 = new JTextField();
        t1.setBounds(610,10,100,30);
        frame.add(t1);

        KeyListener k1 = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                String name = t1.getText();
                searchStudentByName(name);

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        t1.addKeyListener(k1);

        DefaultTableModel tableModel = new DefaultTableModel(columnName,0);
        table = new JTable(tableModel);
        table.setBounds(30,50,700,250);
        frame.add(table);

        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(30,50,700,250);
        frame.add(jScrollPane);

        displayStudentDetails();

        JButton b1 = new JButton("New");
        b1.setBounds(200,320,100,30);
        frame.add(b1);

        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new StudentForm();
            }
        };
        b1.addActionListener(al);

        JButton b2 = new JButton("Delete");
        b2.setBounds(310,320,100,30);
        frame.add(b2);

        ActionListener ald = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete this reccord?",
                        "Delete Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if ((choice==0)){
                    DefaultTableModel tableModel1 = (DefaultTableModel) table.getModel();
                    int selectedRow = table.getSelectedRow();
                    Object id = tableModel1.getValueAt(selectedRow,0);
                    studentService.deleteStudent(Integer.parseInt(id.toString()));
                    displayStudentDetails();
                }

            }
        };b2.addActionListener(ald);



        JButton b3 = new JButton("Edit");
        b3.setBounds(420,320,100,30);
        frame.add(b3);

        ActionListener al3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel1 = (DefaultTableModel) table.getModel();
                int selectedRow = table.getSelectedRow();
                String id = tableModel1.getValueAt(selectedRow,0).toString();
                String name = tableModel1.getValueAt(selectedRow,1).toString();
                String address = tableModel1.getValueAt(selectedRow,2).toString();
                String contact = tableModel1.getValueAt(selectedRow,3).toString();
                String gender = tableModel1.getValueAt(selectedRow,4).toString();
                Object faculty = tableModel1.getValueAt(selectedRow,5);
                Object year = tableModel1.getValueAt(selectedRow,6);

                StudentForm studentForm = new StudentForm();
                studentForm.t1.setText(name);
                studentForm.t2.setText(address);
                studentForm.t3.setText(contact);

                if (gender.equalsIgnoreCase("male")){
                    studentForm.male.setSelected(true);
                } else if (gender.equalsIgnoreCase("female")) {
                    studentForm.female.setSelected(true);
                }
                else {
                    studentForm.others.setSelected(true);
                }

                studentForm.facultty.setSelectedItem(faculty);
                studentForm.yr.setSelectedItem(year);

                studentForm.idlabel.setText(id);
                frame.dispose();


            }
        };b3.addActionListener(al3);

        JButton lout = new JButton("LOGOUT");
        lout.setBounds(580,320,100,30);
        frame.add(lout);

        ActionListener alout = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LoginForm();
            }
        };lout.addActionListener(alout);

        frame.setVisible(true);

    }



    public void displayStudentDetails(){
        List<Student> studentList = studentService.getStudentList();

        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        for (Student s : studentList){
            tableModel.addRow(new Object[]{s.getId(),s.getName(),s.getAddress(),s.getContact(),s.getGender(),s.getFaculty(),s.getYear()});

        }
    }

    public void searchStudentByName(String name){
        List<Student> studentList = studentService.getStudentByName(name);

        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        for(Student s: studentList){
            tableModel.addRow(new Object[]{s.getId(),s.getName(),s.getAddress(),s.getContact(),s.getGender(),s.getFaculty(),s.getYear()
            }
            );

        }
    }

    public static void main(String[] args) {
        StudentDisplay obj = new StudentDisplay();
    }
}
