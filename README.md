# E-Commerce System (Java)

## Overview
This project is a simple e-commerce system implemented in Java. It demonstrates:
- Product management (perishable, shippable, digital)
- Cart and checkout logic
- Shipping service
- Error handling for common e-commerce scenarios

## Features
- Define products with name, price, and quantity
- Support for perishable (expiring) and non-perishable products
- Support for shippable and non-shippable products (with weight for shippable)
- Add products to cart with quantity (not exceeding available stock)
- Checkout with:
  - Subtotal
  - Flat shipping fee (30) if any shippable item is present
  - Total amount
  - Customer balance update
- Error messages for:
  - Empty cart
  - Insufficient balance
  - Out of stock
  - Expired product
- ShippingService prints shipment details for shippable items

## Requirements Covered
- All requirements from the assignment are implemented and tested (see Main.java for test cases)

## How to Run
1. **Compile:**
   ```sh
   javac model/*.java service/*.java Main.java
   ```
2. **Run:**
   ```sh
   java Main
   ```

## Example Output
```
--- Test 1: Normal Checkout ---
** Shipment notice **
2x Cheese 400g
1x Biscuits 700g
Total package weight 1.5kg
** Checkout receipt **
2x Cheese 400g 400
1x Biscuits 700g 150
1x Mobile scratch card 50
----------------------
Subtotal 600
Shipping 30
Amount 630
Remaining balance: 370

--- Test 2: Cart is Empty ---
Cart is empty.

--- Test 3: Insufficient Balance ---
Customer has insufficient balance.

--- Test 4: Product Out of Stock ---
Biscuits 700g is out of stock.

--- Test 5: Product Expired ---
Old Cheese is expired.

--- Test 6: Only Digital Products (No Shipping) ---
** Checkout receipt **
2x Mobile scratch card 100
----------------------
Subtotal 100
Shipping 0
Amount 100
Remaining balance: 270
```

## Assumptions
- Shipping fee is a flat 30 if any shippable item is present
- Product expiration is checked at checkout
- Out of stock and expired products are checked at checkout
- All errors are handled with messages
