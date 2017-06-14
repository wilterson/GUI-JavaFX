/**
 * Created by Wilterson on 10/06/2017.
 */

package hotDogExpress.util;

import com.thoughtworks.xstream.io.xml.DomDriver;
import hotDogExpress.models.User;
import hotDogExpress.models.lists.ProductList;
import hotDogExpress.models.lists.UserList;
import hotDogExpress.models.Sell;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Singleton{

    private static Singleton Instance;

    private UserList users;
    private ProductList products;
    private User userActive = new User(0,null, null, null, null, null, null, null);;

    private Singleton() throws FileNotFoundException {

        XStream stream = new XStream(new StaxDriver());

        stream.processAnnotations(UserList.class);
        stream.processAnnotations(ProductList.class);

        stream.registerConverter(new DateConverter());
//        stream.registerConverter(new PriceConverter());

        File fileUsers = new File("xml/users.xml");
        File fileProducts = new File("xml/products.xml");

        users = (UserList) stream.fromXML(new FileInputStream(fileUsers));
        products = (ProductList) stream.fromXML(new FileInputStream(fileProducts));

        userActive = new User(0,null, null, null, null, null, null, null);
    }

    public static Singleton getInstance() throws FileNotFoundException {
        if(Instance == null)
            Instance = new Singleton();

        return Instance;
    }

    public List<User> getUsers() {
        return users.getUsers();
    }

    public void setUsers(UserList users) {
        this.users = users;
    }

    public User getUserActive() {
        return userActive;
    }

    public void setUserActive(User userActive) {
        this.userActive = userActive;
    }

    public ProductList getProducts() {
        return products;
    }

    public void setProducts(ProductList products) {
        this.products = products;
    }
}

