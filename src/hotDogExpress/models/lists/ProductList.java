/**
 * Created by Wilterson on 14/06/2017.
 */

package hotDogExpress.models.lists;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import hotDogExpress.models.Product;
import java.util.List;

@XStreamAlias("products")
public class ProductList {

    @XStreamImplicit(itemFieldName = "product")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
