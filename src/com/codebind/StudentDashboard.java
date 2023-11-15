package com.codebind;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public class StudentDashboard extends JFrame {

    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton confirmButton;
    private JComboBox comboBox2;
    private JButton submitButton;
    private JComboBox combobox2;
    private JDateChooser JDateChooser1;
    private JTextField textField1;
    private JTextField textField2;
    private JDateChooser Datechooser3;
    private JLabel Datechooser;
    private JComboBox route_com;
    private JComboBox time_com;
    private JComboBox comboBox3;
    private JButton viewButton;
    private JTable table1;
    private JDateChooser JDateChooser2;


    private void createUIComponents() {
        JDateChooser1 = new JDateChooser();
        Datechooser3 = new JDateChooser();
        JDateChooser2 = new JDateChooser();
    }

    private void createTable(String route, java.util.Date date) {
        DefaultTableModel tableModel = new DefaultTableModel(
                null,
                new String[]{"Route", "Date", "Time", "Bus"}
        );

        tableModel.setRowCount(0);
        Connection connection = DatabaseConnection.getConnection();

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM allocationdetails WHERE 1=1");

        if (date != null) {
            queryBuilder.append(" AND date = ?");
        }

        if (route != null && !route.isEmpty()) {
            queryBuilder.append(" AND route = ?");
        }

        queryBuilder.append(" ORDER BY route");

        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString());
            int parameterIndex = 1;

            if (date != null) {
                preparedStatement.setDate(parameterIndex, new java.sql.Date(date.getTime()));
                parameterIndex++;
            }

            if (route != null && !route.isEmpty()) {
                preparedStatement.setString(parameterIndex, route);
                parameterIndex++;
            }


            try  {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Vector<Object> rowData = new Vector<>();
                    rowData.add(resultSet.getString("route"));
                    rowData.add(resultSet.getDate("date"));
                    rowData.add(resultSet.getString("time"));
                    rowData.add(resultSet.getString("buscode"));
                    tableModel.addRow(rowData);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        table1.setModel(tableModel);
    }



    Connection connection = DatabaseConnection.getConnection();

    public StudentDashboard() {
        this.setContentPane(this.panel1);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(200, 50, 1000, 300);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmButton.setEnabled(false);
                String id = textField1.getText();
                String selectedRoute = (String) comboBox1.getSelectedItem();
                String selectedtime = (String) combobox2.getSelectedItem();
                Date selecteddate = JDateChooser1.getDate();

                String insertQuery = "INSERT INTO busbookinginfo (student_id, bus_route, booking_date, booking_time) VALUES (?, ?, ?, ?)";

                try {
                    Connection connection = DatabaseConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, id);
                    preparedStatement.setString(2, selectedRoute);
                    preparedStatement.setDate(3, new java.sql.Date(selecteddate.getTime()));
                    preparedStatement.setString(4, selectedtime);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (validateForm() && rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Seat booked!", "Confirmation", 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Data insertion failed!", "Warning", 2);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButton.setEnabled(false);
                String student_id = textField2.getText();
                String bus_route = (String) route_com.getSelectedItem();
                Date booking_date = Datechooser3.getDate();
                String booking_time = (String) time_com.getSelectedItem();
                String reason = (String) comboBox2.getSelectedItem();
                String insertQuery = "INSERT INTO cancellation (student_id, bus_route, booking_date, booking_time, cancellation_reason) VALUES (?, ?, ?, ?, ?)";
                try {
                    Connection connection = DatabaseConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, student_id);
                    preparedStatement.setString(2, bus_route);
                    preparedStatement.setDate(3, new java.sql.Date(booking_date.getTime()));
                    preparedStatement.setString(4, booking_time);
                    preparedStatement.setString(5, reason);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (validateForm2() && rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Cancellation Successful!", "confirmation", 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Data insertion failed!", "Warning", 2);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String route = (String) comboBox3.getSelectedItem();
                java.util.Date date = JDateChooser2.getDate();
                createTable(route, date);

            }
        });
    }



    private boolean validateForm() {
        String selectedRoute = (String) comboBox1.getSelectedItem();
        String selectedtime = (String) combobox2.getSelectedItem();
        Date selecteddate = JDateChooser1.getDate();
        String id = textField1.getText();

        assert selectedRoute != null;
        if (selectedRoute.isEmpty() || Objects.requireNonNull(selectedtime).isEmpty() || selecteddate == null || id.isEmpty()) {
            // At least one required field is empty, show an error message
            JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "warning", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    private boolean validateForm2() {
        String student_id = textField2.getText();
        String bus_route = (String) route_com.getSelectedItem();
        Date booking_date = Datechooser3.getDate();
        String booking_time = (String) time_com.getSelectedItem();
        String reason = (String) combobox2.getSelectedItem();

        assert bus_route != null;
        if (bus_route.isEmpty() || Objects.requireNonNull(booking_time).isEmpty() || booking_date == null || student_id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "warning", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        StudentDashboard frame = new StudentDashboard();

    }
}
