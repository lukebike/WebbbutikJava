import Presentation.CustomerController;
import Presentation.OrderController;
import Presentation.ProductController;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
//        System.out.println("Hello, World!");
        runMenu();


    }

    public static void runMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        OrderController orderController = new OrderController();
        CustomerController customerController = new CustomerController();
        ProductController productController = new ProductController();
        while (true) {

            System.out.println("Enter a valid menu:");
            System.out.println("1. Customers Menu");
            System.out.println("2. Orders Menu");
            System.out.println("3. Products Menu");
            System.out.println("0. Quit program");
            String menuOption = scanner.nextLine();
            if (menuOption.equals("0")) {
                System.out.println("Quitting program...");
                break;
            } else if (menuOption.equals("1")) {
                customerController.runMenu();
            } else if (menuOption.equals("2")) {
                orderController.runMenu();
            } else if (menuOption.equals("3")) {
                productController.runMenu();
            } else {
                System.out.println("Invalid option, please enter a valid option.");
            }
        }

    }
}