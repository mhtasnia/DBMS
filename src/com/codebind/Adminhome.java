package com.codebind;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;
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
    private JDateChooser JDatechooser3;

    private JComboBox tbox;
    private JTable table3;
    private JButton button1;
    private JComboBox routebox;
    private JTable table4;
    private JTable table5;
    private JButton addButton;
    private JButton backButton;


    private void createUIComponents() {
        JDateChooser1 = new JDateChooser();
        JDatechooser2 = new JDateChooser();
        JDatechooser3 = new JDateChooser();
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
                new String[] {"Bus Code", "Registration Number", "Driver Name", "Contact Info"}
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
                //rowData.add(resultSet.getString("route"));
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
    private void createTable3(String route, java.util.Date date) {
        DefaultTableModel tableModel3 = new DefaultTableModel(
                null,
                new String[]{"Route", "Date", "Time", "Buscode"}
        );

        tableModel3.setRowCount(0);
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
                    tableModel3.addRow(rowData);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        table3.setModel(tableModel3);
    }
    private void createTable4() {

        DefaultTableModel tableModel4 = new DefaultTableModel(
                null,
                new String[] {"Student ID", "Route", "Date", "Time", "Cancellation Reason"}
        );

        try  {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM cancellation";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Vector<Object> rowData = new Vector<>();
                rowData.add(resultSet.getString("student_id"));
                rowData.add(resultSet.getString("bus_route"));
                rowData.add(resultSet.getDate("booking_date"));
                rowData.add(resultSet.getString("booking_time"));
                rowData.add(resultSet.getString("cancellation_reason"));
                tableModel4.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table4.setModel(tableModel4);
    }
    private void createTable5() {

        DefaultTableModel tableModel5 = new DefaultTableModel(
                null,
                new String[] {"Name", "Username", "Route", "Contact Info", "Password"}
        );

        try  {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM verifier";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Vector<Object> rowData = new Vector<>();
                rowData.add(resultSet.getString("v_name"));
                rowData.add(resultSet.getString("username"));
                rowData.add(resultSet.getString("route"));
                rowData.add(resultSet.getString("contact_info"));
                rowData.add(resultSet.getString("Pass"));
                tableModel5.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table5.setModel(tableModel5);
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
        this.setBounds(200, 50, 1100, 650);
        createTable();
        createTable4();
        createTable5();
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


        allocateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allocateButton.setEnabled(false);
                String route = (String) comboBox3.getSelectedItem();
                Date date = JDatechooser2.getDate();
                String time = (String) comboBox4.getSelectedItem();
                String buscode = (String) combobox5.getSelectedItem();

                String insertquery = "INSERT into allocationdetails (route, date, time, buscode) VALUE (?, ?, ?, ?)";

                try {
                    Connection connection = DatabaseConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(insertquery);
                    preparedStatement.setString(1, route);
                    preparedStatement.setDate(2, new java.sql.Date(date.getTime()));
                    preparedStatement.setString(3, time);
                    preparedStatement.setString(4, buscode);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (validateForm() && rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Data inserted successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Data insertion failed!");
                    }
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String route = (String) routebox.getSelectedItem();
                java.util.Date date = JDatechooser3.getDate();
                createTable3(route, date);
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Verifieradd f = new Verifieradd();
                dispose();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usertypechoose f = new Usertypechoose();
                dispose();
            }
        });
    }
    private boolean validateForm() {
        String route = (String) comboBox3.getSelectedItem();
        Date date = JDatechooser2.getDate();
        String time = (String) comboBox4.getSelectedItem();
        String buscode = (String) combobox5.getSelectedItem();

        assert route != null;
        if (route.isEmpty() || Objects.requireNonNull(time).isEmpty() || date == null || Objects.requireNonNull(buscode).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "warning", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        Adminhome frame = new Adminhome();
    }
}



