package Data;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderRepository {
    public static final String connectionString = "jdbc:sqlite:C:/Users/lukes/IdeaProjects/Webbbutik/webbutiken.db";

    public ArrayList<Order> getOrderByCustomerID(int customerID) throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT customers.customer_id," +
                "orders.order_id, orders.order_date from customers JOIN orders on orders.customer_id = " +
                "customers.customer_id JOIN orders_products ON orders.order_id" +
                " = orders_products.order_id " +
                " WHERE customers.customer_id = ?";
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, customerID);
            ResultSet rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Order order = new Order(rs.getInt("order_id"), rs.getInt("customer_id"), rs.getDate("order_date").toLocalDate());
                    orders.add(order);
                }
                return orders;
            }
            return null;
        }
    }

    public boolean addOrder(int customerId) throws SQLException {
        String sql = "INSERT INTO orders (customer_id, order_date) VALUES (?, CURRENT_TIMESTAMP)";
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            int rowsChanged = pstmt.executeUpdate();
            return rowsChanged > 0;
        }
    }

    public boolean addOrderProducts(int orderId, int productId, int quantity, double price) throws SQLException {
        String sql = "INSERT INTO orders_products (order_id, product_id, quantity, unit_price) VALUES (?,?,?,?)";
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, quantity);
            pstmt.setDouble(4, price);
            int rowsChanged = pstmt.executeUpdate();
            return rowsChanged > 0;
        }
    }

    public OrdersProducts getOrderProductsByID(int orderProductsId) throws SQLException {
        String sql = "SELECT * from orders_products WHERE order_product_id = ?";
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, orderProductsId);
            ResultSet rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    return new OrdersProducts(rs.getInt("order_product_id"), rs.getInt("order_id"), rs.getInt("product_id"), rs.getInt("quantity"), rs.getDouble("unit_price"));
                }
            }
        }
        return null;
    }

    public boolean reduceProductQuantity(int quantity, int productId) throws SQLException {
        String sql = "UPDATE products SET stock_quantity = ? where product_id = ?";
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, productId);
            int rowsChanged = pstmt.executeUpdate();
            return rowsChanged > 0;
        }
    }
}
