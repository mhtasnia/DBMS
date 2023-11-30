package com.codebind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Studensignup extends JFrame{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/signup";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";


    Studensignup(){
        this.setContentPane(this.panel2);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(200, 50, 500, 450);
        this.setTitle("Signup");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField2.getText();
                String email = textfield1.getText();
                String password = new String(passfield1.getPassword());

                if (id.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Field Empty!", "Warning", JOptionPane.WARNING_MESSAGE);
                }else if (signupUser(id, email, password) && !isUsernameTaken(id)) {
                    StudentLoginpage f = new StudentLoginpage();
                    dispose();
                    JOptionPane.showMessageDialog(null, "Signup Successful!");
                } else {
                    dispose();
                    JOptionPane.showMessageDialog(null, "Signup Failed. User with the same ID or Email already exists.");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentLoginpage f = new StudentLoginpage();
                dispose();
            }
        });
    }
    private boolean isUsernameTaken(String username) {
        String query = "SELECT * FROM signupstat WHERE stdent_id = ?";
        try  {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // If a record is found, the username is taken
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Handle the error as needed
        }
    }

    private static boolean signupUser(String id, String email, String password) {
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(null, "Invalid email format");
            return false;
        }
        if (password.length() < 8) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long");
            return false;
        }
        try  {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "INSERT INTO signupstat (student_id, Stdent_mail, account_pass) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, id);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage());
        }
        return false;
    }
    private static boolean isValidEmail(String email) {
        // You can use a regular expression for basic email format validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
    private JPanel panel1;
    private JTextField textField2;
    private JButton signUpButton;
    private JPasswordField passfield1;
    private JTextField textfield1;
    private JButton backButton;
    private JPanel panel2;

    public static void main(String[] args) {
        Studensignup f4 = new Studensignup();
    }
}
