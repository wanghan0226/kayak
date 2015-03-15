package util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by pianobean on 3/15/15.
 */
public class AirportNames {
    public static Map<String, String> airportNames = new HashMap<String, String>();
    private static Document airports;
    static {
        SAXReader reader = new SAXReader();
        Class clazz = AirportNames.class;
        URL url =clazz.getClassLoader().getResource("Xml/airports.xml");
        try {
            airports = reader.read(url);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        Element root = airports.getRootElement();
        for ( Iterator i = root.elementIterator( "Airport" ); i.hasNext(); ) {
            Element node = (Element) i.next();
            String code = node.attribute("Code").getValue();
            String name = node.attribute("Name").getValue();
            airportNames.put(code,name);
        }
    }

    public static void main(String[] args) {
        Iterator it = airportNames.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println(entry.getKey()+"----"+entry.getValue());
        }
    }
}
