package Business;

import Data.Product;
import Data.ProductRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductService {
    ProductRepository productRepository = new ProductRepository();

    public ArrayList<Product> getAllProducts() throws SQLException{
        return productRepository.getAll();
    }

    public Product getProductByName(String productName) throws SQLException{
        if(productName.isBlank()){
            System.out.println("Product name can not be empty, please enter a valid product name.");
        }
        if(productRepository.getProductByName(productName) == null){
            System.out.println("Product does not exist, please try again.");
        } else {
            return productRepository.getProductByName(productName);
        }
        return null;
    }

    public void getProductByCategory(int categoryId) throws SQLException{
        if(categoryId <= 0 || categoryId > 10){
            System.out.println("Category ID must be between 1 and 10, please try again.");
        } else {
            productRepository.getAllByCategory(categoryId);
        }
    }

    public boolean addProduct(int manufacturerId, String name, String description, double price, int stockQuantity) throws SQLException{
        if(manufacturerId <= 0 || manufacturerId > 5){
            System.out.println("Manufacturer ID must be between 1 and 5, please try again.");
        }
        else if(name.isBlank()){
            System.out.println("Name cannot be empty, please try again.");
        }
        else if(price <= 0){
            System.out.println("Price can not be negative or zero, please enter a valid price.");
        }
        else if(stockQuantity <= 0){
            System.out.println("Stock quantity can not be negative or zero, please enter a valid number.");
        }
        else {
            productRepository.addProduct(manufacturerId, name, description, price, stockQuantity);
            return true;
        }
    return false;
    }

    public boolean updatePrice(double price, int productId) throws SQLException{
        if(price <= 0){
            System.out.println("Price can not be negative or zero, please enter a valid price.");
        } else if(productId <= 0){
            System.out.println("Product ID can not be negative or zero, please enter a valid product ID");
        } else {
            productRepository.updateProductPrice(price, productId);
            return true;
        }
        return false;
    }

    public boolean updateQuantity(int stockQuantity, int productId) throws SQLException{
        if(stockQuantity <= 0){
            System.out.println("Stock quantity can not be negative or zero, please enter a valid quantity.");
        } else if(productId <= 0){
                System.out.println("Product ID can not be negative or zero, please enter a valid product ID");
        } else {
            productRepository.updateProductQuantity(stockQuantity, productId);
            return true;
        }
        return false;
    }
}
