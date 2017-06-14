/**
 * Created by Wilterson on 09/06/2017.
 */

package hotDogExpress.models;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class Product {

    private static final AtomicInteger count = new AtomicInteger(0);

    private int produtoCod;
    private ProductType type;
    private BigDecimal price;
    private String name;

    public Product(String name, BigDecimal price, ProductType type) {
        this.produtoCod = count.incrementAndGet();

        this.type = type;
        this.price = price;
        this.name = name;
    }

    public static AtomicInteger getCount() {
        return count;
    }

    public int getProdutoCod() {
        return produtoCod;
    }

    public ProductType getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
