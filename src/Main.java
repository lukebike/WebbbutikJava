import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello, World!");
        CustomerControllers customers = new CustomerControllers();
        customers.runMenu();
}}