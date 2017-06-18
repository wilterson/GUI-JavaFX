/**
 * Created by Wilterson on 17/06/2017.
 */

package hotDogExpress.models.lists;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import hotDogExpress.models.Product;
import hotDogExpress.models.Storage;

import java.util.List;

@XStreamAlias("itens")
public class StorageList {

    @XStreamImplicit(itemFieldName = "item")
    private List<Storage> itens;

    public List<Storage> getItens() {
        return itens;
    }

    public void setItens(List<Storage> itens) {
        this.itens = itens;
    }
}
