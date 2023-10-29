package com.codebind;

import javax.swing.*;

public class Managementlogin extends JFrame{

    Managementlogin(){
        this.setContentPane(this.panel1);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(200, 50, 300, 200);

    }
    private JPanel panel1;
    private JTextField textField2;
    private JButton logInButton;
    private JPasswordField passField1;

    public static void main(String[] args) {
        Managementlogin frame = new Managementlogin();

    }

}
