package com.codebind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Usertypechoose extends JFrame{
    private JPanel panel1;
    private JButton StudentloginButton;
    private JButton AdminloginButton;
    private JButton VerifierloginButton;


    public Usertypechoose() {
        this.setContentPane(this.panel1);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(200, 50, 1100, 650);
        this.setTitle("Login");

        StudentloginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentLoginpage f2 = new StudentLoginpage();
                dispose();
                f2.setVisible(true);

            }
        });
        AdminloginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Managementlogin f = new Managementlogin();
                dispose();
            }
        });
        VerifierloginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Will be available soon", "Sorry!", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        Usertypechoose frame = new Usertypechoose();
        
    }
}
