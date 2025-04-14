package Presentation;

import Business.OrderService;
import Data.Order;
import Data.OrderRepository;
import Data.OrdersProducts;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Scanner;

public class OrderController {
    private Scanner scanner = new Scanner(System.in);
    OrderService orderService = new OrderService();
    private OrderRepository orderRepository = new OrderRepository();

    public void runMenu() throws SQLException {
        while(true){
            System.out.println("Welcome to the orders menu!");
            System.out.println("Choose an option:");
            System.out.println("1. Retrieve customer orders");
            System.out.println("2. Add a new order.");
            System.out.println("3. Add products to your order.");
            System.out.println("4. Get order value.");
            System.out.println("0. Quit the program");
            String input = scanner.nextLine();
            if(input.equals("0")){
                break;
            }

            if(input.equals("1")){
                System.out.println("Give customer ID");
                int id = Integer.valueOf(scanner.nextLine());
                System.out.println(orderService.getCustomerOrdersByID(id));
            }

            if(input.equals("2")){
                System.out.println("Give customer ID");
                int id = Integer.valueOf(scanner.nextLine());

                System.out.println(orderService.addOrder(id));
            }

            if(input.equals("3")){
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
            }

            if(input.equals("4")){
                System.out.println("Give orders products id");
                int id = Integer.valueOf(scanner.nextLine());
                double value = orderService.getOrderProductsValue(id);
                System.out.println("Orders products value is " + value);
            }
        }
    }
}
