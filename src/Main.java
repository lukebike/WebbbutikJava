import Data.Order;
import Presentation.CustomerControllers;
import Presentation.OrderController;
import Presentation.ProductController;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
//        System.out.println("Hello, World!");
        OrderController orderController = new OrderController();
        orderController.runMenu();

}}