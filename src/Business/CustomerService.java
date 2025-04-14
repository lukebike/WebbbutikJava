package Business;

import Data.Customer;
import Data.CustomerRepository;

import java.sql.SQLException;
import java.util.ArrayList;


public class CustomerService {
    CustomerRepository customerRepository = new CustomerRepository();

    public ArrayList<Customer> getAllCustomers() throws SQLException{
        return customerRepository.getAll();
    }

    public Customer getCustomerById(int customerId) throws SQLException{
        System.out.println("Retrieving customer...");
        Customer customer = customerRepository.getCustomerById(customerId);
        if(customer == null || customer.getCustomerId() == 0){
            System.out.println("Customer with ID: " + customerId + " not found.");
        } else {
            return customer;
        }
    return null;
    }

    public boolean addCustomer(String name, String email, String phone, String address, String password) throws SQLException{
        return customerRepository.addCustomer(name, email, phone, address, password);
    }

    public boolean updateCustomer(String name, String email, int customerId) throws SQLException{
        return customerRepository.updateCustomer(name, email, customerId);
    }

    public boolean deleteCustomer(int customerId) throws SQLException{
        return customerRepository.deleteCustomer(customerId);
    }
        }



