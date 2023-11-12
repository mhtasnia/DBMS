package com.codebind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

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

                if (Objects.equals(Username, "admin") && pass.equals("1234")){
                    Adminhome frame = new Adminhome();
                    dispose();
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
