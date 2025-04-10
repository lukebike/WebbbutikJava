import java.sql.*;
import java.util.ArrayList;

public class ProductRepository {

    public static final String URL = "jdbc:sqlite:C:/Users/lukes/IdeaProjects/Webbbutik/webbutiken.db";
    ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getAll() throws SQLException{

        try(Connection con = DriverManager.getConnection(URL);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM products")){
            while(rs.next()){
                Product product = new Product(rs.getInt("product_id"),  rs.getInt("manufacturer_id"),
                                      rs.getString("name"), rs.getString("description"),
                                      rs.getDouble("price"), rs.getInt("stock_quantity"));
                products.add(product);
            }
        }
        return products;
    }

    public Product getProductById(int productId) throws SQLException{
        String sql = "SELECT * FROM products WHERE name = ?";
        getAll();
        Product specificProduct = new Product(9999, 9999, "Product Does Not Exist", "Null", 0, 0);
        try(Connection con = DriverManager.getConnection(URL);
            PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            for(Product product : products){
                if(product.getProductId() == productId){
                    specificProduct = product;
                    return specificProduct;
                }
            }
        }
        return specificProduct;
    }

}
