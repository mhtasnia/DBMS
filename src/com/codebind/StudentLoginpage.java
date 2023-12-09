package com.codebind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.*;

public class StudentLoginpage extends JFrame{
    public static String email;



    private static boolean validateAccount(String email, String password) {
        try  {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT account_pass FROM signupstat WHERE Stdent_mail = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("account_pass");
                return password.equals(storedPassword);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage());
        }
        return false;
    }
    StudentLoginpage(){
        this.setContentPane(this.panel1);
        this.setVisible(true);
        this.setTitle("Student Login");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(200, 50, 700, 450);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Studensignup c = new Studensignup();
                dispose();
            }
        });
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = textField2.getText();
                String password = new String(passField1.getPassword());

                if (validateAccount(email, password)) {
                    StudentDashboard f = new StudentDashboard();
                    dispose();
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Login Failed. Invalid email or password.");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usertypechoose f = new Usertypechoose();
                dispose();
            }
        });
    }
    private JPanel panel1;
    private JPasswordField passField1;
    private JTextField textField2;
    private JButton logInButton;
    private JButton signUpButton;
    private JButton backButton;

    public static void main(String[] args) {
        StudentLoginpage f1 = new StudentLoginpage();

    }


}
