/**
 * Created by Wilterson on 19/06/2017.
 */

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import hotDogExpress.models.Product;
import hotDogExpress.models.Sell;
import hotDogExpress.models.lists.ProductList;
import hotDogExpress.models.lists.SellList;
import hotDogExpress.models.lists.StorageList;
import hotDogExpress.models.lists.UserList;
import hotDogExpress.util.DateConverter;
import hotDogExpress.util.Singleton;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

public class SellListTest {

    @Test
    public void geraXmlCompra() throws FileNotFoundException {
        XStream stream = new XStream(new StaxDriver());

        stream.processAnnotations(SellList.class);

        stream.registerConverter(new DateConverter());

        File fileSells = new File("xml/sells.xml");

        SellList sells = (SellList) stream.fromXML(new FileInputStream(fileSells));

        Sell sellExpected = new Sell();

        assertEquals(sellExpected, sells);
    }
}