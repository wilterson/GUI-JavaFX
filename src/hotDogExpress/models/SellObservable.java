/**
 * Created by Wilterson on 18/06/2017.
 */

package hotDogExpress.models;

import javafx.beans.property.*;
import java.time.LocalDate;
import java.util.List;

public class SellObservable {

    private IntegerProperty cod;
    private ObjectProperty<User> buyer;
    private ObjectProperty<User> seller;
    private ObjectProperty<List> products;
    private ObjectProperty<LocalDate> date;
    private DoubleProperty total;

    public SellObservable(IntegerProperty cod, User buyer, User seller, List<Product> products, LocalDate date, DoubleProperty total) {
        this.cod = cod;
        this.buyer.set(buyer);
        this.seller.set(seller);
        this.products.set(products);
        this.date.set(date);
        this.total = total;
    }

    public SellObservable() {
        this.cod = new SimpleIntegerProperty();
        this.buyer = null;
        this.seller = null;
        this.products = null;
        this.date = null;
        this.total = new SimpleDoubleProperty();
    }

    public SellObservable(Sell sell) {
        this.cod = new SimpleIntegerProperty();
        this.buyer = new SimpleObjectProperty<>();
        this.seller = new SimpleObjectProperty<>();
        this.products = new SimpleObjectProperty<>();
        this.date = new SimpleObjectProperty<>();
        this.total = new SimpleDoubleProperty();

        this.cod.set(sell.getCod());
        this.buyer.set(sell.getBuyer());
        this.seller.set(sell.getSeller());
        this.products.set(sell.getProducts());
        this.date.set(sell.getDate());
        this.total.set(sell.getTotal());
    }

    public int getCod() {
        return cod.get();
    }

    public IntegerProperty codProperty() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod.set(cod);
    }

    public User getBuyer() {
        return buyer.get();
    }

    public ObjectProperty<User> buyerProperty() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer.set(buyer);
    }

    public User getSeller() {
        return seller.get();
    }

    public ObjectProperty<User> sellerProperty() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller.set(seller);
    }

    public List getProducts() {
        return products.get();
    }

    public ObjectProperty<List> productsProperty() {
        return products;
    }

    public void setProducts(List products) {
        this.products.set(products);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public double getTotal() {
        return total.get();
    }

    public DoubleProperty totalProperty() {
        return total;
    }

    public void setTotal(double total) {
        this.total.set(total);
    }

    @Override
    public String toString() {
        return "SellObservable{" + "cod=" + cod + ", buyer=" + buyer + ", seller=" + seller + ", products=" + products + ", date=" + date + ", total=" + total + '}';
    }
}
