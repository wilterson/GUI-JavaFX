/**
 * Created by Wilterson on 10/06/2017.
 */

package hotDogExpress.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Sell {

    private static final AtomicInteger count = new AtomicInteger(0);

    private int cod;
    private User buyer;
    private List<Product> products;
    private Date date;
    private double price;
    private User seller;

    LocalDate localDate = LocalDate.now();
    int day = localDate.getDayOfMonth();
    int month = localDate.getMonthValue();
    int year = localDate.getYear();

    public Sell(User buyer, List<Product> products, double price, User seller) {
        this.buyer = buyer;
        this.products = products;
        this.price = price;
        this.seller = seller;
    }
}
