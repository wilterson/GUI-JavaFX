/**
 * Created by Wilterson on 17/06/2017.
 */

package hotDogExpress.models;

import javafx.beans.property.*;

public class StorageObservable {

    private IntegerProperty cod;
    private StringProperty name;
    private IntegerProperty qtd;
    private DoubleProperty price;
    private StringProperty status;

    public StorageObservable(IntegerProperty cod, StringProperty name, IntegerProperty qtd, DoubleProperty price, StringProperty status) {
        this.cod = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.qtd = new SimpleIntegerProperty();
        this.price = new SimpleDoubleProperty();
        this.status = new SimpleStringProperty();
    }

    public StorageObservable(Storage storage) {
        this.cod = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.qtd = new SimpleIntegerProperty();
        this.price = new SimpleDoubleProperty();
        this.status = new SimpleStringProperty();

        this.cod.set(storage.getCod());
        this.name.set(storage.getName());
        this.qtd.set(storage.getQtd());
        this.price.set(storage.getPrice());
        this.status.set(storage.getStatus());
    }

    public StorageObservable() {
        this.cod = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty("");
        this.qtd = new SimpleIntegerProperty();
        this.price = new SimpleDoubleProperty();
        this.status = new SimpleStringProperty("");
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getQtd() {
        return qtd.get();
    }

    public IntegerProperty qtdProperty() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd.set(qtd);
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

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
