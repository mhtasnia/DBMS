package com.codebind;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class Adminhome extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel panel6;
    private JTable table1;
    private JButton addBusButton;
    private JPanel panel4;
    private JTable table2;
    private JComboBox comboBox1;
    private JDateChooser JDateChooser1;
    private JButton viewButton;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JComboBox comboBox2;
    private JPanel panel3;
    private JPanel panel7;
    private JComboBox comboBox3;
    private JDateChooser JDatechooser2;
    private JComboBox comboBox4;
    private JComboBox combobox5;
    private JPanel panel5;
    private JPanel panel1;
    private JButton allocateButton;


    private void createUIComponents() {
        JDateChooser1 = new JDateChooser();
        JDatechooser2 = new JDateChooser();
    }
    public void buslistcombo() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT buscode FROM buslist");

            while (rs.next()) {
                combobox5.addItem(rs.getString("buscode"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void createTable() {

        DefaultTableModel tableModel = new DefaultTableModel(
                null,
                new String[] {"Bus Code", "Registration Number", "Driver Name", "Contact Info", "Route"}
        );

        try  {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM buslist";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Vector<Object> rowData = new Vector<>();
                rowData.add(resultSet.getString("buscode"));
                rowData.add(resultSet.getString("reg_no"));
                rowData.add(resultSet.getString("drivername"));
                rowData.add(resultSet.getString("driver_contact"));
                rowData.add(resultSet.getString("route"));
                tableModel.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table1.setModel(tableModel);
    }
    private void createTable2(String route, java.util.Date date, String time) {
        DefaultTableModel tableModel2 = new DefaultTableModel(
                null,
                new String[]{"Student id", "Route", "Date", "Time"}
        );

        tableModel2.setRowCount(0);
        Connection connection = DatabaseConnection.getConnection();

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM busbookinginfo WHERE 1=1");

        if (date != null) {
            queryBuilder.append(" AND booking_date = ?");
        }

        if (route != null && !route.isEmpty()) {
            queryBuilder.append(" AND bus_route = ?");
        }

        if (time != null && !time.isEmpty()) {
            queryBuilder.append(" AND booking_time = ?");
        }

        queryBuilder.append(" ORDER BY bus_route");

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

            if (time != null && !time.isEmpty()) {
                preparedStatement.setString(parameterIndex, time);
            }

            try  {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Vector<Object> rowData = new Vector<>();
                    rowData.add(resultSet.getString("student_id"));
                    rowData.add(resultSet.getString("bus_route"));
                    rowData.add(resultSet.getDate("booking_date"));
                    rowData.add(resultSet.getString("booking_time"));
                    tableModel2.addRow(rowData);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        table2.setModel(tableModel2);
    }
    private int countFilteredEntries(String route, java.util.Date date) {
        int count = 0;
        Connection connection = DatabaseConnection.getConnection();

        StringBuilder queryBuilder = new StringBuilder("SELECT COUNT(*) AS entry_count FROM busbookinginfo WHERE 1=1");

        if (date != null) {
            queryBuilder.append(" AND booking_date = ?");
        }

        if (route != null && !route.isEmpty()) {
            queryBuilder.append(" AND bus_route = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            if (date != null) {
                preparedStatement.setDate(parameterIndex, new java.sql.Date(date.getTime()));
                parameterIndex++;
            }

            if (route != null && !route.isEmpty()) {
                preparedStatement.setString(parameterIndex, route);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt("entry_count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
    private int countBus(int count) {
        int buscount = (count / 30) + ((count % 30 != 0) ? 1 : 0);
        return buscount;
    }


    public Adminhome() {
        this.setContentPane(this.panel1);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(200, 50, 850, 500);
        createTable();
        buslistcombo();

        addBusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Buslistedit frame = new Buslistedit();
                dispose();
            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String route = (String) comboBox1.getSelectedItem();
                java.util.Date date = JDateChooser1.getDate();
                String time = (String) comboBox2.getSelectedItem();
                createTable2(route, date, time);
                int count = countFilteredEntries(route, date);
                textArea1.setText(" " + count + " ");
                int buscount = countBus(count);
                textArea2.setText(" " + buscount + " ");
            }

        });


    }

    public static void main(String[] args) {
        Adminhome frame = new Adminhome();
    }
}



