package daoImpl;

import beans.Flight;
import dao.AirportFunctions;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import util.QueryFactory;
import util.XmlConnection;

import java.net.URL;
import java.util.*;

/**
 * Created by pianobean on 3/8/15.
 */
public class AirportInfo implements AirportFunctions {

    public Map<String, String> findCodesAndNames() {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        Document document = null;
        Class clazz = AirportInfo.class;
        URL url =clazz.getClassLoader().getResource("Xml/airports.xml");
        try {
            document = reader.read(url);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        Element root = document.getRootElement();
        for ( Iterator i = root.elementIterator( "Airport" ); i.hasNext(); ) {
            Element foo = (Element) i.next();
            String code =foo.attribute("Code").getValue();
            String name =foo.attributeValue("Name");
            map.put(code,name);
        }
        return map;
    }

    @Override
    public List<String> findDepartNumbers(String code, Date departDate) {
        List<String> flightNumbers = new ArrayList<String>();
        String departFile = XmlConnection.getXmlInfo(QueryFactory.getDepartAirplanes(code,departDate));

        Document document = null;
        try {
            document = DocumentHelper.parseText(departFile);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        List list = document.selectNodes( "//Flight/@Number" );

        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Attribute attribute = (Attribute) iter.next();
            String number = attribute.getValue();
            flightNumbers.add(number);
        }
        return flightNumbers;
    }

    @Override
    public List<String> findArriveNumbers(String code, Date arriveDate) {
        List<String> flightNumbers = new ArrayList<String>();
        String arriveFile = XmlConnection.getXmlInfo(QueryFactory.getArriveAirplanes(code, arriveDate));
//        System.out.println(departFile);
        Document document = null;
        try {
            document = DocumentHelper.parseText(arriveFile);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        List list = document.selectNodes( "//Flight/@Number" );

        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Attribute attribute = (Attribute) iter.next();
            String number = attribute.getValue();
            flightNumbers.add(number);
        }
        return flightNumbers;
    }

    @Override
    public Flight findFlightByNumber(String num, Document document) {
        Flight flight = new Flight();
        Node node = document.selectSingleNode( "//Flight[@Number='"+num+"']" );
        flight.setPlaneType(node.valueOf("@Airplane"));
        int flightTime = Integer.parseInt(node.valueOf("@FlightTime"));
        flight.setFlightTime(flightTime);
        flight.setNumber(num);


        return flight;
    }

    public static void main(String[] args) {
        GregorianCalendar calendar = new GregorianCalendar(2015, 04, 9);
        Date date = calendar.getTime();
        AirportFunctions functions = new AirportInfo();
        List list = functions.findArriveNumbers("BOS", date);
        System.out.println(list);
    }
}
