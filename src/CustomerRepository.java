import java.sql.*;
import java.util.ArrayList;

public class CustomerRepository {

    public static final String URL = "jdbc:sqlite:C:/Users/lukes/IdeaProjects/Webbbutik/webbutiken.db";
    ArrayList<Customer> customers = new ArrayList<>();

    public ArrayList<Customer> getAll() throws SQLException {

        try(Connection con = DriverManager.getConnection(URL);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers")){
            while(rs.next()){
                Customer customer = new Customer(rs.getInt("customer_id"), rs.getString("name"),
                                    rs.getString("email"), rs.getString("phone"),
                                    rs.getString("address"), rs.getString("password"));

                customers.add(customer);
            }
        }
        return customers;
    }

    public Customer getCustomerById(int customerId) throws SQLException{
        String sql = "SELECT * FROM customers where customer_id = ?";
        getAll();
        Customer specificCustomer = new Customer(9999, "Customer Does Not Exist", "Null", "Null");
        try(Connection con = DriverManager.getConnection(URL);
            PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            for(Customer customer : customers){
                if(customer.getCustomerId() == customerId){
                    specificCustomer = customer;
                    return specificCustomer;
                }

            }
        }
        return specificCustomer;
    }

    public boolean addCustomer(String name, String email, String phone, String address, String password) throws SQLException{
        String sql = "INSERT INTO customers (name, email, phone, address, password) VALUES (?, ?, ?, ?, ?)";
        try(Connection con = DriverManager.getConnection(URL);
            PreparedStatement pstmt = con.prepareStatement(sql)){
            if(!(name.isBlank() && email.isBlank() && password.isBlank())){
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, phone);
                pstmt.setString(4, address);
                pstmt.setString(5, password);
                if(phone.isEmpty()){
                    pstmt.setString(3, "No phone number found");
                }
                if(address.isEmpty()){
                    pstmt.setString(4, "No address found");
                }
            }
            int insertCount = pstmt.executeUpdate();

            return insertCount > 0;
        }

    }

    public boolean updateCustomer(String name, String email, int customerId) throws SQLException{
        String sql = "UPDATE customers SET name = ?, email = ? WHERE customer_id = ?";
        try(Connection con = DriverManager.getConnection(URL);
            PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, customerId);
            int insertCount = pstmt.executeUpdate();

            return insertCount > 0;

        }
    }

    public boolean deleteCustomer(int customerId) throws SQLException{
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        try(Connection con = DriverManager.getConnection(URL);
            PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, customerId);
            int insertCount = pstmt.executeUpdate();

            return insertCount > 0;
        }
    }
}
