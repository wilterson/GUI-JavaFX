/**
 * Created by Wilterson on 10/06/2017.
 */

package hotDogExpress.util;

import hotDogExpress.models.*;
import hotDogExpress.models.lists.ProductList;
import hotDogExpress.models.lists.StorageList;
import hotDogExpress.models.lists.UserList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.max;

public class Singleton{

    private static Singleton Instance;

    private UserList users;
    private ProductList products;
    private StorageList storageItens;

    private User userActive = new User(0,null, null, null, null, null, null, null, null);;

    private List<User> usersClients;
    private LocalDate dateToday;
    private List<Product> productsFood;

    private Singleton() throws FileNotFoundException {

        XStream stream = getXStream();

        stream.processAnnotations(UserList.class);
        stream.processAnnotations(ProductList.class);
        stream.processAnnotations(StorageList.class);

        stream.registerConverter(new DateConverter());
//        stream.registerConverter(new PriceConverter());

        File fileUsers = new File("xml/users.xml");
        File fileProducts = new File("xml/products.xml");
        File fileStorage = new File("xml/storage.xml");

        users = (UserList) stream.fromXML(new FileInputStream(fileUsers));
        products = (ProductList) stream.fromXML(new FileInputStream(fileProducts));
        storageItens = (StorageList) stream.fromXML(new FileInputStream(fileStorage));

        userActive = new User(0,null, null, null, null, null, null, null, null);
    }

    private XStream getXStream() {
        return new XStream(new StaxDriver());
    }

    public static Singleton getInstance() throws FileNotFoundException {
        if(Instance == null)
            Instance = new Singleton();

        return Instance;
    }

    public List<User> getUsers() {
        return users.getUsers();
    }

    public void setUsers(List<User> users) {
        this.users.setUsers(users);
    }

    public void setStorageItens(List<Storage> storageItens) {
        this.storageItens .setItens(storageItens);
    }

    public User getUserActive() {
        return userActive;
    }

    public void setUserActive(User userActive) {
        this.userActive = userActive;
    }

    public List<Product> getProducts() {
        return products.getProducts();
    }

    public void setProducts(List<Product> products) {
        this.products.setProducts(products);
    }

    public List<User> getUsersClients() {
        List<User> clients = new ArrayList<>();

        for (User user : users.getUsers()){
            if(user.getRole().equals("client")){
                clients.add(user);
            }
        }

        return clients;
    }

    public List<User> getUsersEmployees() {
        List<User> employees = new ArrayList<>();

        for (User user : users.getUsers()){
            if((user.getRole().equals("admin")) || (user.getRole().equals("employee"))){
                employees.add(user);
            }
        }

        return employees;
    }

    public int getLastInsertedId() {
        int id = 0;
        for (int i = 0; i < users.getUsers().size(); i++) {
            id = max(id, users.getUsers().get(i).getId());
        }
        id++;
        return id;
    }

    public List<Product> getProductsFood() {
        List<Product> food = new ArrayList<>();

        for (Product item: products.getProducts()){
            if((item.getType() == ProductType.food)){
                food.add(item);
            }
        }

        return food;
    }

    public List<Product> getProductsDrink() {
        List<Product> drink = new ArrayList<>();

        for (Product item: products.getProducts()){
            if((item.getType() == ProductType.drink)){
                drink.add(item);
            }
        }

        return drink;
    }

    public List<Storage> getItensStorage() {
        return storageItens.getItens();
    }

    public int getLastInsertedIdProduct() {
        int id = 0;
        for (int i = 0; i < products.getProducts().size(); i++) {
            id = max(id, products.getProducts().get(i).getProductCod());
        }
        id++;
        return id;
    }

    public int getLastInsertedIdStorage() {
        int id = 0;
        for (int i = 0; i < storageItens.getItens().size(); i++) {
            id = max(id, storageItens.getItens().get(i).getCod());
        }
        id++;
        return id;
    }

    public void saveUsers(List<User> users) throws FileNotFoundException {
        XStream stream = getXStream();
        stream.alias("user", User.class);
        stream.toXML(users, new FileOutputStream("xml/users.xml"));
    }

    public void saveProducts(List<Product> products) throws FileNotFoundException {
        XStream stream = getXStream();
        stream.alias("product", Product.class);
        stream.toXML(products, new FileOutputStream("xml/products.xml"));
    }

    public void saveStorage(List<Storage> storageItens) throws FileNotFoundException {
        XStream stream = getXStream();
        stream.alias("item", Storage.class);
        stream.toXML(storageItens, new FileOutputStream("xml/storage.xml"));
    }
}

