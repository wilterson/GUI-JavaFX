/**
 * Created by Wilterson on 17/06/2017.
 */

package hotDogExpress.models;

public class Storage {

    private int cod;
    private String name;
    private int qtd;
    private double price;
    private String status;

    public Storage(int cod, String name, int qtd, double price, String status) {
        this.cod = cod;
        this.name = name;
        this.qtd = qtd;
        this.price = price;
        this.status = status;
    }

    public Storage(Storage item) {
        this.cod = item.getCod();
        this.name = item.getName();
        this.qtd = item.getQtd();
        this.price = item.getPrice();
        this.status = item.getStatus();
    }

    public Storage() {
        this.cod = 0;
        this.name = "";
        this.qtd = 0;
        this.price = 0;
        this.status = "";
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
