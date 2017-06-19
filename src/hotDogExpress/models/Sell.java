/**
 * Created by Wilterson on 10/06/2017.
 */

package hotDogExpress.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import hotDogExpress.util.Singleton;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@XStreamAlias("sell")
public class Sell {

    private int cod;
    private User buyer;
    private User seller;
    private List<Product> products;
    private LocalDate date;
    private double total;

    public Sell(int cod, User buyer, List<Product> products, double price, User seller, LocalDate date) throws FileNotFoundException {
        this.cod = cod;
        this.buyer = buyer;
        this.products = products;
        this.seller = seller;
        this.date = date;

        this.total = 0;
        for (Product product: products) {
            this.total += product.getPrice();
        }
    }

    public Sell() {
        this.cod = 0;
        this.buyer = null;
        this.products = null;
        this.seller = null;
        this.date = null;
        this.total = 0;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "Sell{" + "cod=" + cod + ", buyer=" + buyer + ", seller=" + seller + ", products=" + products + ", date=" + date + ", total=" + total + '}';
    }
}
