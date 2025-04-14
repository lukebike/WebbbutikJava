package Business;

import Data.*;

import java.sql.SQLException;

public class OrderService {
    ProductRepository productRepository = new ProductRepository();
    OrderRepository orderRepository = new OrderRepository();
    CustomerService customerService = new CustomerService();
    public boolean getCustomerOrdersByID(int customerId) throws SQLException {
        Customer customer = customerService.getCustomerById(customerId);
        if(customerId <= 0){
            System.out.println("Customer ID can not be negative or zero, please enter a valid ID.");
        } else if(customer == null){
            System.out.println("Customer does not exist, please enter a valid id.");
        } else {
            System.out.println(customer);
            System.out.println(orderRepository.getOrderByCustomerID(customerId));
            return true;
        }
        return false;
    }

    public boolean addOrder(int customerId) throws SQLException {
        Customer customer = customerService.getCustomerById(customerId);
        if(customerId <= 0){
            System.out.println("Customer ID can not be negative or zero, please enter a valid ID.");
        } {
            if(customer.getCustomerId() == customerId){
                System.out.println("Order added under the name of " + customer.getName());
            }
        }
        return false;
    }

    public boolean addOrderProducts(int orderId, int productId, int quantity, double price) throws SQLException{
        Product product = productRepository.getProductByID(productId);
        if(orderId <= 0){
            System.out.println("Order ID can not be negative or zero, please enter a valid number.");
        }
        else if(product == null){
            System.out.println("Product does not exist, please enter a valid ID.");
        }
        else if(quantity > product.getStockQuantity()){
            System.out.println("Order quantity is larger than stock quantity" + "(" + product.getStockQuantity() + ")");
        }
        else if(price <= 0){
            System.out.println("Price can not be negative or zero, please enter a valid number.");
        }
        else {
            orderRepository.addOrderProducts(orderId, productId, quantity, price);
            return true;
        }
        return false;
    }

    public double getOrderProductsValue(int orderProductsId) throws SQLException {
        if(orderProductsId <= 0){
            System.out.println("Order products ID can not be negative or zero, please enter a valid number.");
        }
        OrdersProducts ordersProducts = orderRepository.getOrderProductsByID(orderProductsId);
        if(ordersProducts == null){
            System.out.println("Order products does not exist, please enter a valid ID.");
        } else {
            return ordersProducts.totalPrice();
        }
        return -1;
    }
}
