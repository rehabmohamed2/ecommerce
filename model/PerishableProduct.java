package model;

import java.util.Date;

public class PerishableProduct extends Product implements Shippable {
    private Date expiryDate;
    private double weight;

    public PerishableProduct(String name, double price, int quantity, Date expiryDate, double weight) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    @Override
    public boolean isExpired() {
        return new Date().after(expiryDate);
    }

    @Override
    public boolean requiresShipping() { return true; }

    @Override
    public double getWeight() { return weight; }
} 