package service;

import model.Shippable;
import java.util.*;

public class ShippingService {
    public static void shipItems(List<Shippable> items) {
        if (items.isEmpty()) return;
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        Map<String, Integer> itemCount = new HashMap<>();
        for (Shippable s : items) {
            itemCount.put(s.getName(), itemCount.getOrDefault(s.getName(), 0) + 1);
            totalWeight += s.getWeight();
        }
        itemCount.forEach((name, count) -> System.out.println(count + "x " + name));
        System.out.printf("Total package weight %.1fkg\n", totalWeight);
    }
} 