import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerControllers {
    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
    private boolean success;
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
                customerService.getAllCustomers();
                break;
            }

            if(select.equals("2")){
                System.out.println("Give id:");
                id = Integer.valueOf(scanner.nextLine());
                customerService.getCustomerId(id);
                break;
            }

            if(select.equals("3")){
                System.out.println("Give id: ");
                id = Integer.valueOf(scanner.nextLine());
                System.out.println("Input new name: ");
                name = scanner.nextLine();
                System.out.println("Input new email: ");
                email = scanner.nextLine();

                success = customerService.updateCustomer(name, email, id);
                System.out.println(success ? "Customer updated successfully!" :
                        "Could not update customer, input a valid customer id.");
                break;
            }

            if(select.equals("4")){
                System.out.println("Enter name: ");
                name = scanner.nextLine();
                System.out.println("Enter email: ");
                email = scanner.nextLine();
                System.out.println("Enter phone: ");
                phone = scanner.nextLine();
                System.out.println("Enter address: ");
                address = scanner.nextLine();
                System.out.println("Enter password: ");
                password = scanner.nextLine();

                success = customerService.addCustomer(name, email, phone, address, password);
                System.out.println(success ? name + " successfully added to our customers list!"
                        : "Customer could not be added, make sure you have added a valid name, email and password.");
                break;
            }

            if(select.equals("5")){
                System.out.println("Give id: ");
                id = Integer.valueOf(scanner.nextLine());
                success = customerService.deleteCustomer(id);
                System.out.println(success ? "Customer deleted successfully" : "Customer could not be found, input a valid customer id.");
                break;
        }
        }
    }
}



