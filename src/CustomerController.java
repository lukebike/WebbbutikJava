//import java.sql.Array;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class CustomerController {
//    Data.CustomerRepository customerRepository = new Data.CustomerRepository();
//    Data.ProductRepository productRepository = new Data.ProductRepository();
//
//    public void runMenu() throws SQLException {
//
//        Scanner scanner = new Scanner(System.in);
//        while(true){
//            System.out.println("1. Retrieve all customers");
//            System.out.println("2. Retrieve a customer based on ID");
//            System.out.println("3. Update a customer based on ID");
//            System.out.println("4. Add a new customer");
//            System.out.println("5. Delete a customer");
//            System.out.println("6. Show all products");
//            System.out.println("7. Retrieve a product based on ID");
//            System.out.println("0. Quit program" );
//            String select = scanner.nextLine();
//            if(select.equals("0")){
//                break;
//            }
//            switch (select) {
//                case "1":
//                    ArrayList<Data.Customer> customers = customerService.getAllCustomers();
//                    for(Data.Customer c : customers){
//                        System.out.println("CustomerId: " + c.getCustomerId());
//                        System.out.println("Name: " + c.getName());
//                    }
//                    break;
//                case "2":
//                    System.out.println("Give id:");
//                    int id = Integer.valueOf(scanner.nextLine());
//                    Data.Customer customer = customerRepository.getCustomerById(id);
//                    System.out.println(customer);
//                    break;
//                case "3":
//                    System.out.println("Give id: ");
//                    int updateId = Integer.valueOf(scanner.nextLine());
//                    System.out.println("Input new name: ");
//                    String newName = scanner.nextLine();
//                    System.out.println("Input new email: ");
//                    String newEmail = scanner.nextLine();
//                    boolean success = customerRepository.updateCustomer(newName, newEmail, updateId);
//                    System.out.println(success ? "Data.Customer updated successfully!" : "Could not update customer, input a valid customer id.");
//                case "4":
//                    System.out.println("Enter name: ");
//                    String name = scanner.nextLine();
//                    System.out.println("Enter email: ");
//                    String email = scanner.nextLine();
//                    System.out.println("Enter phone: ");
//                    String phone = scanner.nextLine();
//                    System.out.println("Enter address: ");
//                    String address = scanner.nextLine();
//                    System.out.println("Enter password: ");
//                    String password = scanner.nextLine();
//                    boolean addSuccess = customerRepository.addCustomer(name, email, phone, address, password);
//                    System.out.println(addSuccess ? name + " successfully added to our customers list!" : "Data.Customer could not be added, make sure you have added a valid name, email and password.");
//                case "5":
//                    System.out.println("Give id: ");
//                    int deleteId = Integer.valueOf(scanner.nextLine());
//                    boolean deleteSuccess = customerRepository.deleteCustomer(deleteId);
//                    System.out.println(deleteSuccess ? "Data.Customer deleted successfully" : "Data.Customer could not be found, input a valid customer id.");
//                case "6":
//                    ArrayList<Data.Product> products = productRepository.getAll();
//                    for(Data.Product p : products){
//                        System.out.println("Name: " + p.getName() + " Price: " + p.getPrice());
//                    }
//                case "7":
//                    System.out.println("Give id: ");
//                    int productId = Integer.valueOf(scanner.nextLine());
//                    Data.Product product = productRepository.getProductById(productId);
//                    System.out.println(product);
//            }
//        }
//
//
//    }
//}
