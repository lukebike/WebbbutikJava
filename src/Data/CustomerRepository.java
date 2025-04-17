package Data;

import java.sql.*;
import java.util.ArrayList;

public class CustomerRepository {

    private static final String connectionString = "jdbc:sqlite:C:/Users/lukes/IdeaProjects/Webbbutik/webbutiken.db";


    public ArrayList<Customer> getAll() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(connectionString);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM customers")) {
            while (rs.next()) {
                Customer customer = new Customer(rs.getInt("customer_id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("phone"),
                        rs.getString("address"), rs.getString("password"));

                customers.add(customer);
            }
        }
        return customers;
    }

    public Customer getCustomerById(int customerId) throws SQLException {
        String sql = "SELECT * FROM customers where customer_id = ?";
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs != null) {
                return new Customer(rs.getInt("customer_id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("phone"),
                        rs.getString("address"), rs.getString("password"));
            }
        }
        return null;
    }

    public boolean addCustomer(String name, String email, String phone, String address, String password) throws SQLException {
        String sql = "INSERT INTO customers (name, email, phone, address, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            if (!(name.isBlank() && email.isBlank() && password.isBlank())) {
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                if (phone.isBlank()) {
                    pstmt.setString(3, null);
                } else {
                    pstmt.setString(3, phone);
                }
                if (address.isBlank()) {
                    pstmt.setString(4, null);
                } else {
                    pstmt.setString(4, address);
                }
                pstmt.setString(5, password);
            }
            int rowsChanged = pstmt.executeUpdate();
            return rowsChanged > 0;
        }

    }

    public boolean updateCustomer(String name, String email, int customerId) throws SQLException {
        String sql = "UPDATE customers SET name = ?, email = ? WHERE customer_id = ?";
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, customerId);
            int rowsChanged = pstmt.executeUpdate();

            return rowsChanged > 0;

        }
    }

    public boolean deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            int rowsChanged = pstmt.executeUpdate();

            return rowsChanged > 0;
        }
    }
}
