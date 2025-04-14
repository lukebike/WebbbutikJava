package Data;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class ProductRepository {

    public static final String connectionString = "jdbc:sqlite:C:/Users/lukes/IdeaProjects/Webbbutik/webbutiken.db";

    public ArrayList<Product> getAll() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(connectionString);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {
            while (rs.next()) {
                Product product = new Product(rs.getInt("product_id"), rs.getInt("manufacturer_id"),
                        rs.getString("name"), rs.getString("description"),
                        rs.getDouble("price"), rs.getInt("stock_quantity"));
                products.add(product);
            }
        }
        return products;
    }

    public Product getProductByName(String productName) throws SQLException {
        String sql = "SELECT * FROM products WHERE name = ?";
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, productName);
            ResultSet rs = pstmt.executeQuery();
            if (rs != null) {
                return new Product(rs.getInt("product_id"), rs.getInt("manufacturer_id"),
                        rs.getString("name"), rs.getString("description"),
                        rs.getDouble("price"), rs.getInt("stock_quantity"));
            }
        }
        return null;
    }

    public Product getProductByID(int productId) throws SQLException {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            if (rs != null) {
                return new Product(rs.getInt("product_id"), rs.getInt("manufacturer_id"),
                        rs.getString("name"), rs.getString("description"),
                        rs.getDouble("price"), rs.getInt("stock_quantity"));
            }
        }
        return null;
    }

    public void getAllByCategory(int categoryId) throws SQLException{
       String sql = "SELECT categories.category_id, categories.name AS category, products.name, products.description, " +
               "products.price, products.stock_quantity FROM categories JOIN products_categories ON products_categories." +
               "category_id = categories.category_id JOIN products ON products.product_id = products_categories.product_id" +
               " WHERE categories.category_id = ?";
       try(Connection con = DriverManager.getConnection(connectionString);
       PreparedStatement pstmt = con.prepareStatement(sql)){
           pstmt.setInt(1, categoryId);
           ResultSet rs = pstmt.executeQuery();
           if(rs != null){
               while(rs.next()){
                   System.out.println("Category ID: " + rs.getInt("category_id") + ", " + "Category Name: " +  rs.getString("category") + ", "
                           + "Product Name: " +rs.getString("name") + ", " + "Description: " + rs.getString("description") + ", " + "Price: " + rs.getDouble("price") +  ", "
                           + "Stock Quantity: " + rs.getInt("stock_quantity"));
               }
           }
       };


       }
    public boolean addProduct(int manufacturerId, String name, String description, double price, int stockQuantity) throws SQLException {
        String sql = "INSERT INTO products (manufacturer_id, name, description, price, stock_quantity) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, manufacturerId);
            pstmt.setString(2, name);
            pstmt.setString(3, description);
            pstmt.setDouble(4, price);
            pstmt.setInt(5, stockQuantity);
            int rowsChanged = pstmt.executeUpdate();
            return rowsChanged > 0;
        }
    }

    public boolean updateProductPrice(double price, int productId) throws SQLException {
        String sql = "UPDATE products SET price = ? WHERE product_id = ?";
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setDouble(1, price);
            pstmt.setInt(2, productId);
            int rowsChanged = pstmt.executeUpdate();
            return rowsChanged > 0;
        }
    }

    public boolean updateProductQuantity(int stockQuantity, int productId) throws SQLException {
        String sql = "UPDATE products SET stock_quantity = ? WHERE product_id = ?";
        try (Connection con = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setDouble(1, stockQuantity);
            pstmt.setInt(2, productId);
            int rowsChanged = pstmt.executeUpdate();

            return rowsChanged > 0;
        }
    }

}





