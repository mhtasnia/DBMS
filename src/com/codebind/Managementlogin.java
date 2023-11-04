package com.codebind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Managementlogin extends JFrame {

    Managementlogin(){
        this.setContentPane(this.panel1);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(200, 50, 300, 200);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Username = textField2.getText();
                String pass = new String(passField1.getPassword());

                if (Username == "admin" && pass == "1234"){
                    dispose();
                    //AdminInterface frame = new AdminInterface();
                }
            }
        });
    }
    private JPanel panel1;
    private JTextField textField2;
    private JButton logInButton;
    private JPasswordField passField1;

    public static void main(String[] args) {
        Managementlogin frame = new Managementlogin();

    }

}
