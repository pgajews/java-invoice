package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products;

    public Invoice() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity can't be less then 1");
        }
        int i=0;
        while (i < quantity) {
            addProduct(product);
            i++;
        }
    }

    public BigDecimal getSubtotal() {
        return this.products.stream()
            .map(p -> p.getPrice())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTax() {
        return this.products.stream()
            .map(p -> p.getPrice().multiply(p.getTaxPercent()))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotal() {
        return this.products.stream()
            .map(p -> p.getPriceWithTax())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
