package Presentation;

import Business.OrderService;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Scanner;

public class OrderController {
    private final Scanner scanner = new Scanner(System.in);
    private final OrderService orderService = new OrderService();

    public void runMenu() throws SQLException {
        while (true) {
            System.out.println("-- WELCOME TO THE ORDERS MENU! -- ");
            System.out.println("Choose an option:");
            System.out.println("1. Retrieve customer orders");
            System.out.println("2. Add a new order");
            System.out.println("3. Add products to your order");
            System.out.println("4. Get order value");
            System.out.println("0. Back to main menu");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                System.out.println("Returning to main menu...");
                break;
            } else if (input.equals("1")) {
                try {
                    System.out.println("Give customer ID");
                    int id = Integer.valueOf(scanner.nextLine());
                    System.out.println(orderService.getCustomerOrdersByID(id));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, please input a number.");
                }
            } else if (input.equals("2")) {
                try {
                    System.out.println("Give customer ID");
                    int id = Integer.valueOf(scanner.nextLine());
                    System.out.println(orderService.addOrder(id));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, please input a number.");
                } catch (NullPointerException e) {
                    System.out.println("Invalid Customer ID, please input a valid customer ID.");
                }
            } else if (input.equals("3")) {
                try {
                    System.out.println("Give order ID");
                    int orderId = Integer.valueOf(scanner.nextLine());
                    System.out.println("Give product ID");
                    int productId = Integer.valueOf(scanner.nextLine());
                    System.out.println("Give product quantity");
                    int quantity = Integer.valueOf(scanner.nextLine());
                    System.out.println("Give price");
                    double price = Double.valueOf(scanner.nextLine());
                    boolean success = orderService.addOrderProducts(orderId, productId, quantity, price);
                    System.out.println(success ? "Order products added successfully" : "Order products could not be added");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, please input a number.");
                }
            } else if (input.equals("4")) {
                try {
                    NumberFormat formatter = NumberFormat.getCurrencyInstance();
                    System.out.println("Give orders products id");
                    int id = Integer.valueOf(scanner.nextLine());
                    double value = orderService.getOrderProductsValue(id);
                    System.out.println("Orders products value is " + formatter.format(value));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, please input a number.");
                }
            } else {
                System.out.println("Invalid option, please enter a valid option.");
            }
        }

    }
}
