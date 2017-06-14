/**
 * Created by Wilterson on 09/06/2017.
 */

package hotDogExpress.models;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class Product {

    private int productCod;
    private ProductType type;
    private double price;
    private String name;

    public Product(int productCod, ProductType type, double price, String name) {
        this.productCod = productCod;
        this.type = type;
        this.price = price;
        this.name = name;
    }

    public int getProductCod() {
        return productCod;
    }

    public void setProductCod(int productCod) {
        this.productCod = productCod;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
