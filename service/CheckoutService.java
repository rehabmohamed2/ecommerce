package service;

import model.*;
import java.util.*;

public class CheckoutService {
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) throw new RuntimeException("Cart is empty.");
        double subtotal = 0;
        double shippingFee = 0;
        List<Shippable> toShip = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            Product product = item.product;
            if (product.isExpired()) throw new RuntimeException(product.getName() + " is expired.");
            if (item.quantity > product.getQuantity())
                throw new RuntimeException(product.getName() + " is out of stock.");
            subtotal += item.getTotalPrice();
            if (product instanceof Shippable) {
                for (int i = 0; i < item.quantity; i++) {
                    toShip.add((Shippable) product);
                }
            }
        }
        if (!toShip.isEmpty()) shippingFee = 30;
        double total = subtotal + shippingFee;
        if (customer.getBalance() < total)
            throw new RuntimeException("Customer has insufficient balance.");

        for (CartItem item : cart.getItems()) {
            item.product.reduceQuantity(item.quantity);
        }
        customer.deduct(total);

        ShippingService.shipItems(toShip);

        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s %.0f\n", item.quantity, item.product.getName(), item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f\n", subtotal);
        System.out.printf("Shipping %.0f\n", shippingFee);
        System.out.printf("Amount %.0f\n", total);
        System.out.printf("Remaining balance: %.0f\n", customer.getBalance());
    }
} 