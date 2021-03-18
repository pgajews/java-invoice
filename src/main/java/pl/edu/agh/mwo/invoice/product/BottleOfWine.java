package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class BottleOfWine extends OtherProduct {

  public static final String excise = "5.56";

  public BottleOfWine(String name, BigDecimal price) {
    super(name, price);
  }

  @Override
  public BigDecimal getPriceWithTax() {
    return super.getPriceWithTax().add(new BigDecimal(excise));
  }
}
