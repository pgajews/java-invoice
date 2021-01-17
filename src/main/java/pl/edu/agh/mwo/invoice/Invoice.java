package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.*;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products;

    public Invoice() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        this.products.put(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity can't be less then 1");
        }
        this.products.put(product, quantity);
    }

    public BigDecimal getSubtotal() {
        return this.products.entrySet().stream()
            .map(p -> p.getKey().getPrice().multiply(new BigDecimal(p.getValue())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTax() {
        return this.products.entrySet().stream()
            .map(p -> p.getKey().getPrice().multiply(p.getKey().getTaxPercent()).multiply(new BigDecimal(p.getValue())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotal() {
        return this.products.entrySet().stream()
            .map(p -> p.getKey().getPriceWithTax().multiply(new BigDecimal(p.getValue())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
