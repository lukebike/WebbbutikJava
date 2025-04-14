package Data;

public class OrdersProducts {
    private int orderProductsID;
    private int orderID;
    private int productID;
    private int quantity;
    private double price;

    public OrdersProducts(int orderProductsID, int orderID, int productID, int quantity, double price) {
        this.orderProductsID = orderProductsID;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderProductsID() {
        return orderProductsID;
    }

    public void setOrderProductsID(int orderProductsID) {
        this.orderProductsID = orderProductsID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double totalPrice(){
        return this.quantity * this.price;
    }

    @Override
    public String toString() {
        return "OrdersProducts{" +
                "orderProductsID=" + orderProductsID +
                ", orderID=" + orderID +
                ", productID=" + productID +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
