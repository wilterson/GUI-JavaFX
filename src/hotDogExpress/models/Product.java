/**
 * Created by Wilterson on 09/06/2017.
 */

package hotDogExpress.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

@XStreamAlias("product")
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

    public Product() {
        this.productCod = 0;
        this.type = ProductType.undefined;
        this.price = 0;
        this.name = "";
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

    @Override
    public String toString() {
        return "Product{" + "productCod=" + productCod + ", type=" + type + ", price=" + price + ", name='" + name + '\'' + '}';
    }
}
