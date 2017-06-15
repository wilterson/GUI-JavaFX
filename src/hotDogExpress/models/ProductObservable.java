/**
 * Created by Wilterson on 14/06/2017.
 */

package hotDogExpress.models;

import javafx.beans.property.*;

public class ProductObservable {

    private IntegerProperty productCod;
    private ProductType type;
    private DoubleProperty price;
    private StringProperty name;

    public ProductObservable(IntegerProperty productCod, ProductType type, DoubleProperty price, StringProperty name) {
        this.productCod = productCod;
        this.type = type;
        this.price = price;
        this.name = name;
    }

    public ProductObservable(Product prod) {
        this.productCod = new SimpleIntegerProperty(prod.getProductCod());
        this.type = prod.getType();
        this.price = new SimpleDoubleProperty(prod.getPrice());
        this.name = new SimpleStringProperty(prod.getName());
    }

    public int getProductCod() {
        return productCod.get();
    }

    public IntegerProperty productCodProperty() {
        return productCod;
    }

    public void setProductCod(int productCod) {
        this.productCod.set(productCod);
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
