package com.codebind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Verifieradd extends  JFrame{

    private JPanel panel1;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JTextField textField3;
    private JButton addButton;
    private JTextField textfield2;
    private JTextField textField4;


    public Verifieradd() {
        this.setContentPane(panel1);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(200,50, 500, 300);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addButton.setEnabled(false);
                String name = textField1.getText();
                String username = textfield2.getText();
                String bus_route = (String) comboBox1.getSelectedItem();
                String contact_info = textField4.getText();
                String pass = textField3.getText();

                String insertQuery = "INSERT INTO verifier (v_name, username, route, contact_info, pass) VALUES (?, ?, ?, ?, ?)";

                try {
                    Connection connection = DatabaseConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, username);
                    preparedStatement.setString(3, bus_route);
                    preparedStatement.setString(4, contact_info);
                    preparedStatement.setString(5, pass);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (validateForm2() && rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Verifier Added", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                        Adminhome f =new Adminhome();
                        dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "Couldn't add verifier", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    private boolean validateForm2() {
        String name = textField1.getText();
        String username = textfield2.getText();
        String bus_route = (String) comboBox1.getSelectedItem();
        String contact_info = textField4.getText();
        String pass = textField3.getText();

        assert bus_route != null;
        if (name.isEmpty() || pass.isEmpty() || username.isEmpty() || contact_info.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "warning", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        Verifieradd f = new Verifieradd();
    }


}
