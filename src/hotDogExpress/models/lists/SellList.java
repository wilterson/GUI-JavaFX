/**
 * Created by Wilterson on 19/06/2017.
 */

package hotDogExpress.models.lists;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import hotDogExpress.models.Product;
import hotDogExpress.models.Sell;
import hotDogExpress.models.User;

import java.util.List;

@XStreamAlias("sells")
public class SellList{

    @XStreamImplicit(itemFieldName = "sell")
    private List<Sell> sells;

    public List<Sell> getSells() {
        return sells;
    }

    public void setSells(List<Sell> sells) {
        this.sells = sells;
    }
}
