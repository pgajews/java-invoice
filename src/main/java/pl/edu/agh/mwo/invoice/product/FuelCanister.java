package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FuelCanister extends OtherProduct {

  private final String eventDate = "2604";
  private final String excise = "5.56";
  private final Date date;

  public FuelCanister(String name, BigDecimal price, Date date) {
    super(name, price);
    this.date = date;
  }

  @Override
  public BigDecimal getPriceWithTax() {
    SimpleDateFormat formatter = new SimpleDateFormat("ddMM");
    if (formatter.format(date).equals(eventDate)) {
      return getPrice().add(new BigDecimal(excise));
    }
    return super.getPriceWithTax().add(new BigDecimal(excise));
  }

}
