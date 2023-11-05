package com.codebind;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminInterface extends JFrame{

    private JButton addBusButton;
    private JTable table1;
    private JTabbedPane panel1;
    private JTable table2;
    private JComboBox comboBox1;
    private JDateChooser JDateChooser1;
    private JButton viewButton;

    private void createUIComponents() {
        JDateChooser1 = new JDateChooser();
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





    private void createTable2( String route, Date date) {
        DefaultTableModel tableModel2 = new DefaultTableModel(
                null,
                new String[] {"Student id", "Route", "Date", "Time"}
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

        queryBuilder.append(" ORDER BY bus_route");

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            if (date != null ) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = dateFormat.format(date);
                queryBuilder.append(" AND booking_date = ?");
                preparedStatement.setString(parameterIndex, formattedDate);
                parameterIndex++;
            }

            if (route != null && !route.isEmpty()) {
                preparedStatement.setString(parameterIndex, route);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vector<Object> rowData = new Vector<>();
                    rowData.add(resultSet.getInt("student_id"));
                    rowData.add(resultSet.getString("bus_route"));
                    rowData.add(resultSet.getDate("booking_date"));
                    rowData.add(resultSet.getString("booking_time"));
                    tableModel2.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table2.setModel(tableModel2);
    }



    public AdminInterface(){
        this.setContentPane(this.panel1);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(200, 50, 600, 400);
        createTable();
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
                Date date = JDateChooser1.getDate();
                createTable2(route, date);
            }
        });
    }



    public static void main(String[] args) {
        AdminInterface frame = new AdminInterface();
    }
}
