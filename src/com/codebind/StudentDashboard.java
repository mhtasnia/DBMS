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
    private JPanel panel_date;
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
        this.setBounds(200, 50, 500, 300);
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

                    if (rowsAffected > 0) {
                        System.out.println("Data inserted successfully!");
                        // You can show a confirmation message to the user here
                    } else {
                        System.out.println("Data insertion failed.");
                        // You can show an error message to the user here
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Handle any exceptions or errors that may occur during the database operation
                }
            }
        });
    }

    public static void main(String[] args) {
        StudentDashboard frame = new StudentDashboard();

    }
}
