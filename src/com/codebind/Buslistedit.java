package com.codebind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Buslistedit extends JFrame{
   // Connection connection = DatabaseConnection.getConnection();

    Buslistedit(){
        this.setContentPane(this.panel1);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(200, 50, 400, 330);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButton.setEnabled(false);
                String code = textField1.getText();
                String reg = textField2.getText();
                String name = textField3.getText();
                String contact = textField4.getText();
                String route = (String) comboBox1.getSelectedItem();

                String insertQuery = "INSERT INTO buslist (buscode, reg_no, drivername, driver_contact, route) VALUES (?, ?, ?, ?, ?)";

                if (validateForm()) {
                    try (Connection connection = DatabaseConnection.getConnection()) {
                        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                        preparedStatement.setString(1, code);
                        preparedStatement.setString(2, reg);
                        preparedStatement.setString(3, name);
                        preparedStatement.setString(4, contact);
                        preparedStatement.setString(5, route);

                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Data inserted successfully!");
                            AdminInterface f = new AdminInterface();
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
    }

    private boolean validateForm() {
        String code = textField1.getText();
        String reg = textField2.getText();
        String name = textField3.getText();
        String contact = textField4.getText();
        String route = (String) comboBox1.getSelectedItem();

        if (code.isEmpty() || reg.isEmpty() || name.isEmpty() || contact.isEmpty() || route == null) {
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

    public static void main(String[] args) {
        Buslistedit frame = new Buslistedit();
    }

}
