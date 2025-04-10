import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerService {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Customer> customers;
    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
    private boolean success;
    CustomerRepository customerRepository = new CustomerRepository();

    public ArrayList<Customer> getAllCustomers() throws SQLException{
        System.out.println("Detta är vårt logiska lager");
        System.out.println("Här ordnar vi med uträkningar och sånt kul");
        System.out.println("Returning all customers...");
        this.customers = customerRepository.getAll();
        for(Customer c : customers) {
            System.out.println("CustomerId: " + c.getCustomerId());
            System.out.println("Name: " + c.getName());
        }
        return customers;
    }

    public Customer getCustomerId(int customerId) throws SQLException{
        Customer customer = customerRepository.getCustomerById(customerId);
        System.out.println("Retrieving customer...");
        System.out.println(customer);
        return customer;
    }

    public boolean addCustomer(String name, String email, String phone, String address, String password) throws SQLException{

        System.out.println("Adding customer...");
        return customerRepository.addCustomer(name, email, phone, address, password);
    }

    public boolean updateCustomer(String name, String email, int customerId) throws SQLException{
        System.out.println("Updating customer...");
        return customerRepository.updateCustomer(name, email, customerId);
    }

    public boolean deleteCustomer(int customerId) throws SQLException{
        System.out.println("Removing customer..." );
        return customerRepository.deleteCustomer(customerId);
    }
        }



