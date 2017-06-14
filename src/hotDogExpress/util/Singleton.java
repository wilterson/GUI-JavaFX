/**
 * Created by Wilterson on 10/06/2017.
 */

package hotDogExpress.util;

import hotDogExpress.models.User;
import hotDogExpress.models.lists.UserList;
import hotDogExpress.models.Sell;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class Singleton{

    private static Singleton Instance;

    private UserList users;
    private User userActive;

    private Singleton() throws FileNotFoundException {

        XStream stream = new XStream(new StaxDriver());

        stream.processAnnotations(UserList.class);

        stream.registerConverter(new DateConverter());

        File fileUsers = new File("xml/clients.xml");

        users = (UserList) stream.fromXML(new FileInputStream(fileUsers));

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
}

