package Presentation;

import Business.CustomerService;
import Data.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerControllers {

    CustomerService customerService = new CustomerService();

    public void runMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Retrieve all customers");
            System.out.println("2. Retrieve a customer based on ID");
            System.out.println("3. Update a customer based on ID");
            System.out.println("4. Add a new customer");
            System.out.println("5. Delete a customer");
            System.out.println("0. Quit program");
            String select = scanner.nextLine();

            if (select.equals("0")) {
                System.out.println("Quitting program...");
                break;
            }

            if (select.equals("1")) {
               ArrayList<Customer> customers = customerService.getAllCustomers();
                for(Customer c : customers) {
                    System.out.println("CustomerId: " + c.getCustomerId());
                    System.out.println("Name: " + c.getName());
                }
            }


            if (select.equals("2")) {
                System.out.println("Give id:");
                    int id = Integer.valueOf(scanner.nextLine());
                    customerService.getCustomerById(id);
            }

            if (select.equals("3")) {
                System.out.println("Give id: ");
                int id = Integer.valueOf(scanner.nextLine());
                System.out.println("Input new name: ");
                String name = scanner.nextLine();
                System.out.println("Input new email: ");
                String email = scanner.nextLine();
                System.out.println("Updating customer...");
                boolean success = customerService.updateCustomer(name, email, id);
                System.out.println(success ? "Customer updated successfully!" :
                        "Could not update customer, input a valid customer id.");
                continue;
            }

            if (select.equals("4")) {
                System.out.println("Enter name: ");
                String name = scanner.nextLine();
                System.out.println("Enter email: ");
                String email = scanner.nextLine();
                System.out.println("Enter phone: ");
                String phone = scanner.nextLine();
                System.out.println("Enter address: ");
                String address = scanner.nextLine();
                System.out.println("Enter password: ");
                String password = scanner.nextLine();
                System.out.println("Adding customer...");
                boolean success = customerService.addCustomer(name, email, phone, address, password);
                System.out.println(success ? name + " successfully added to our customers list!"
                        : "Customer could not be added, make sure you have added a valid name, email and password.");
                continue;
            }

            if (select.equals("5")) {
                System.out.println("Give id: ");
                int id = Integer.valueOf(scanner.nextLine());
                System.out.println("Removing customer..." );
                boolean success = customerService.deleteCustomer(id);
                System.out.println(success ? "Customer deleted successfully" : "Customer could not be found, input a valid customer id.");
            }
        }
    }
}



