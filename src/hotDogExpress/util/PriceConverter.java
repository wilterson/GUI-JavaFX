/**
 * Created by Wilterson on 19/06/2017.
 */

package hotDogExpress.util;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class PriceConverter implements SingleValueConverter {


    @Override
    public String toString(Object valor) {
        return getFormatter().format(valor);
    }

    @Override
    public Object fromString(String valor) {
        NumberFormat formatter = getFormatter();

        try {
            return formatter.parse(valor);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ConversionException("Erro ao COnverter: "+ valor, e);
        }
    }

    private NumberFormat getFormatter() {
        Locale br = new Locale("pt", "br");
        return NumberFormat.getCurrencyInstance(br);
    }

    @Override
    public boolean canConvert(Class type) {
        return type.isAssignableFrom(Double.class);
    }
}
