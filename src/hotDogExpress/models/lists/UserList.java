/**
 * Created by Wilterson on 10/06/2017.
 */

package hotDogExpress.models.lists;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import hotDogExpress.models.User;
import java.util.List;

@XStreamAlias("list")
public class UserList {

    @XStreamImplicit(itemFieldName = "user")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
