import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello, World!");
        CustomerController customers = new CustomerController();
        customers.runMenu();


}}