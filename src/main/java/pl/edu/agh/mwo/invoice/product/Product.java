package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;

    protected Product(String name, BigDecimal price, BigDecimal tax) {
        verifyName(name);
        verifyPrice(price);
        this.name = name;
        this.price = price;
        this.taxPercent = tax;
    }

    private void verifyName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name can't be null or empty");
        }
    }

    private void verifyPrice(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("Price can't be null");
        }
        if (price.signum() < 0) {
            throw new IllegalArgumentException("Price can't be negative");
        }
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getTaxPercent() {
        return this.taxPercent;
    }

    public BigDecimal getPriceWithTax() {
        return this.price.add(this.price.multiply(this.taxPercent));
    }
}
