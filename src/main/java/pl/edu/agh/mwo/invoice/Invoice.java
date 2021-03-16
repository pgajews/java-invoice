package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private final long number = getNextInvoiceNumber();

    private Map<Product, Integer> products = new HashMap<Product, Integer>();

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public long getNumber() {
        return number;
    }

    private long getNextInvoiceNumber() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        String number = formatter.format(date) + Math.abs(new Random().nextInt());
        return Long.parseLong(number);
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public List<String> printInvoice() {
        List<String> printList = new ArrayList<>();
        printList.add(String.valueOf(number));
        for (Product product: products.keySet()) {
            printList.add(product.getName() + "\t" + products.get(product) + "\t" + product.getPrice());
        }
        printList.add("Liczba pozycji: " + products.keySet().size());
        return printList;
    }
}
