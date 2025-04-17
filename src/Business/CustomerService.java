package Business;

import Data.Customer;
import Data.CustomerRepository;

import java.sql.SQLException;
import java.util.ArrayList;


public class CustomerService {
    CustomerRepository customerRepository = new CustomerRepository();
    String regex = "^(.+)@(\\S+)$";

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
        if(name.isBlank()){
            System.out.println("Name can not be empty, please try again.");
        } else if(email.isBlank()){
            System.out.println("Email can not be empty please try again.");

        } else if(!email.matches(regex)){
            System.out.println("Invalid email format, please try again.");
        } else if(phone.isBlank()){
            System.out.println("Phone number can not be empty, please try again.");
        } else if(address.isBlank()){
            System.out.println("Address can not be empty, please try again.");
        } else if(password.isBlank()){
            System.out.println("Password can not be empty, please try again.");
        } else if(password.length() < 4){
            System.out.println("Password length must be more than 4 characters.");
        } else{
            return customerRepository.addCustomer(name, email, phone, address, password);
        }
       return false;
    }

    public boolean updateCustomer(String name, String email, int customerId) throws SQLException{
        return customerRepository.updateCustomer(name, email, customerId);
    }

    public boolean deleteCustomer(int customerId) throws SQLException{
        return customerRepository.deleteCustomer(customerId);
    }
        }



