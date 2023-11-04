package com.codebind;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class StudentDashboard extends JFrame {

    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton confirmButton;
    private JComboBox comboBox2;
    private JButton submitButton;
    private JPanel panel_ab;
    private JPanel panel_home;
    private JPanel panel_cs;
    private JPanel panel_nb;
    private JComboBox combobox2;
    private JDateChooser JDateChooser1;
    private JTextField textField1;
    private JTextField textField2;


    private void createUIComponents() {
        JDateChooser1 = new JDateChooser();
    }
    Connection connection = DatabaseConnection.getConnection();

    public StudentDashboard() {
        this.setContentPane(this.panel1);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(200, 50, 600, 400);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButton.setEnabled(false);
                String id = textField1.getText();
                String selectedRoute = (String) comboBox1.getSelectedItem();
                String selectedtime = (String) combobox2.getSelectedItem();
                Date selecteddate = JDateChooser1.getDate();

                String insertQuery = "INSERT INTO busbookinginfo (student_id, bus_route, booking_date, booking_time) VALUES (?, ?, ?, ?)";

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, id);
                    preparedStatement.setString(2, selectedRoute);
                    preparedStatement.setDate(3, new java.sql.Date(selecteddate.getTime()));
                    preparedStatement.setString(4, selectedtime);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (validateForm() && rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Data inserted successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Data insertion failed!");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private boolean validateForm() {
        String selectedRoute = (String) comboBox1.getSelectedItem();
        String selectedtime = (String) combobox2.getSelectedItem();
        Date selecteddate = JDateChooser1.getDate();
        String id = textField1.getText();

        if (selectedRoute.isEmpty() || selectedtime.isEmpty() || selecteddate == null || id.isEmpty()) {
            // At least one required field is empty, show an error message
            JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "warning", 2);
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        StudentDashboard frame = new StudentDashboard();

    }
}
