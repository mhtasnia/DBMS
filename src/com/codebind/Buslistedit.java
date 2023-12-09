package com.codebind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Buslistedit extends JFrame{
   Connection connection = DatabaseConnection.getConnection();

    Buslistedit(){
        this.setContentPane(this.panel1);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(200, 50,1100, 650);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButton.setEnabled(false);
                String code = textField1.getText();
                String reg = textField2.getText();
                String name = textField3.getText();
                String contact = textField4.getText();

                String insertQuery = "INSERT INTO buslist (buscode, reg_no, drivername, driver_contact) VALUES (?, ?, ?, ?)";

                if (validateForm()) {
                    try  {
                        Connection connection = DatabaseConnection.getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                        preparedStatement.setString(1, code);
                        preparedStatement.setString(2, reg);
                        preparedStatement.setString(3, name);
                        preparedStatement.setString(4, contact);

                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Bus added successfully!");
                            Adminhome f = new Adminhome();
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Data insertion failed!");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Fill up all required field!");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Adminhome f = new Adminhome();
                dispose();
            }
        });
    }

    private boolean validateForm() {
        String code = textField1.getText();
        String reg = textField2.getText();
        String name = textField3.getText();
        String contact = textField4.getText();

        if (code.isEmpty() || reg.isEmpty() || name.isEmpty() || contact.isEmpty()) {
            // At least one required field is empty, show an error message
            JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "warning", 2);
            return false;
        } else {
            return true;
        }
    }
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JButton saveButton;
    private JPanel panel1;
    private JButton backButton;

    public static void main(String[] args) {
        Buslistedit frame = new Buslistedit();
    }

}
