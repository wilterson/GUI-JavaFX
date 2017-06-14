/**
 * Created by Wilterson on 11/06/2017.
 */

package hotDogExpress.util;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter{

    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");

    @Override
    public void marshal(Object obj, HierarchicalStreamWriter writer, MarshallingContext mc) {
        Date date = (Date) obj;
        String dateFormatted = formatter.format(date);
        writer.setValue(dateFormatted);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Date date = new Date();
        try {
            date = formatter.parse(reader.getValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    @Override
    public boolean canConvert(Class type) {
        return type.equals(Date.class);
    }
}
