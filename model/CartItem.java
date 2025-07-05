package model;

public class CartItem {
    public Product product;
    public int quantity;

    public CartItem(Product product, int quantity) {
        if (quantity > product.getQuantity())
            throw new IllegalArgumentException("Quantity exceeds stock.");
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return quantity * product.getPrice();
    }
} 