package com.codebind;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminInterface extends JFrame{
    private JButton addBusButton;
    private JTable table1;
    private JTabbedPane panel1;

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
    }


    public static void main(String[] args) {
        AdminInterface frame = new AdminInterface();
    }
}
