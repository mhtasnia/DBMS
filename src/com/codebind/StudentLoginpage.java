package com.codebind;

import javax.swing.*;

public class StudentLoginpage extends JFrame{

    StudentLoginpage(){
        this.setContentPane(this.panel1);
        this.setVisible(true);
        this.setTitle("Student Login");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(200, 200, 300, 200);
    }
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton logInButton;
    private JButton signUpButton;

    public static void main(String[] args) {
        StudentLoginpage f1 = new StudentLoginpage();

    }

}
