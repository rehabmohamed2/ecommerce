import model.*;
import service.*;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 5); // not expired
        Product cheese = new PerishableProduct("Cheese 400g", 200, 5, cal.getTime(), 0.4);
        Product biscuits = new PerishableProduct("Biscuits 700g", 150, 2, cal.getTime(), 0.7);
        Product tv = new NonPerishableShippableProduct("TV", 300, 3, 3.0);
        Product scratchCard = new DigitalProduct("Mobile scratch card", 50, 10);

        // 1. Normal Checkout
        System.out.println("\n--- Test 1: Normal Checkout ---");
        Customer customer = new Customer("Salma", 1000);
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);
        CheckoutService.checkout(customer, cart);

        // 2. Cart is Empty
        System.out.println("\n--- Test 2: Cart is Empty ---");
        Cart emptyCart = new Cart();
        try {
            CheckoutService.checkout(customer, emptyCart);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        // 3. Insufficient Balance
        System.out.println("\n--- Test 3: Insufficient Balance ---");
        Customer poorCustomer = new Customer("Ali", 100);
        Cart cart2 = new Cart();
        cart2.add(tv, 1);
        try {
            CheckoutService.checkout(poorCustomer, cart2);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        // 4. Product Out of Stock
        System.out.println("\n--- Test 4: Product Out of Stock ---");
        Cart cart3 = new Cart();
        try {
            cart3.add(biscuits, 10); // Only 1 left after previous purchase
            CheckoutService.checkout(customer, cart3);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        // 5. Product Expired
        System.out.println("\n--- Test 5: Product Expired ---");
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -1); // expired
        Product expiredCheese = new PerishableProduct("Old Cheese", 80, 1, cal2.getTime(), 0.3);
        Cart cart4 = new Cart();
        cart4.add(expiredCheese, 1);
        try {
            CheckoutService.checkout(customer, cart4);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        // 6. Only Digital Products (No Shipping)
        System.out.println("\n--- Test 6: Only Digital Products (No Shipping) ---");
        Cart cart5 = new Cart();
        cart5.add(scratchCard, 2);
        CheckoutService.checkout(customer, cart5);
    }
} 