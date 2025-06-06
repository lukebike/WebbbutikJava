package Presentation;

import Business.ProductService;
import Data.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductController {
    private final Scanner scanner = new Scanner(System.in);
    private final ProductService productService = new ProductService();

    public void runMenu() throws SQLException {
        while (true) {
            System.out.println("-- WELCOME TO THE PRODUCTS MENU! -- ");
            System.out.println("Choose an option:");
            System.out.println("1. Retrieve all products");
            System.out.println("2. Retrieve product by name");
            System.out.println("3. Retrieve product by category");
            System.out.println("4. Add product");
            System.out.println("5. Update product price");
            System.out.println("6. Update product quantity");
            System.out.println("0. Back to main menu");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                System.out.println("Returning to main menu...");
                break;
            } else if (input.equals("1")) {
                ArrayList<Product> products = productService.getAllProducts();
                for (Product p : products) {
                    System.out.println(p.toString());
                }
            } else if (input.equals("2")) {
                System.out.println("Enter product name: ");
                String name = scanner.nextLine();
                System.out.println(productService.getProductByName(name));
            } else if (input.equals("3")) {
                System.out.println("Give id: ");
                try {
                    int id = Integer.valueOf(scanner.nextLine());
                    productService.getProductByCategory(id);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number, please input a valid number");
                }

            } else if (input.equals("4")) {
                try {
                    System.out.println("Enter manufacturer id");
                    int id = Integer.valueOf(scanner.nextLine());
                    System.out.println("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.println("Enter price: ");
                    double price = Double.valueOf(scanner.nextLine());
                    System.out.println("Enter stock quantity: ");
                    int stockQuantity = Integer.valueOf(scanner.nextLine());
                    System.out.println("Adding product...");
                    boolean success = productService.addProduct(id, name, description, price, stockQuantity);
                    System.out.println(success ? name + " successfully added to our products list!"
                            : "Product could not be added, make sure you have added a valid name, price and quantity.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, please input a number.");
                }
            } else if (input.equals("5")) {
                try {
                    System.out.println("Enter product id");
                    int id = Integer.valueOf(scanner.nextLine());
                    System.out.println("Enter price");
                    double price = Double.valueOf(scanner.nextLine());
                    boolean success = productService.updatePrice(price, id);
                    System.out.println(success ? price + " is now the new price of the product." : "Price could not be updated, please check your product ID or price.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, please input a number.");
                }
            } else if (input.equals("6")) {
                try {
                    System.out.println("Enter product id");
                    int id = Integer.valueOf(scanner.nextLine());
                    System.out.println("Enter quantity");
                    int quantity = Integer.valueOf(scanner.nextLine());
                    boolean success = productService.updateQuantity(quantity, id);
                    System.out.println(success ? quantity + " is now the new amount of the product." : "Quantity could not be updated, please check your product ID or quantity.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, please input a number.");
                }
            } else {
                System.out.println("Invalid option, please enter a valid option.");
            }
        }
    }

}
