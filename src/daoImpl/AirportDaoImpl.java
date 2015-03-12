package daoImpl;

import beans.Flight;
import dao.AirportDao;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import util.QueryFactory;
import util.XmlConnection;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pianobean on 3/8/15.
 */
public class AirportDaoImpl implements AirportDao {

    public Map<String, String> findCodesAndNames() {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        Document document = null;
        Class clazz = AirportDaoImpl.class;
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
        //从XML获取所有信息
        Element element = (Element) document.selectSingleNode( "//Flight[@Number='"+num+"']" );
        String type = element.attribute("Airplane").getValue();
        String flightTime = element.attribute("FlightTime").getValue();
        String departCode = element.element("Departure").element("Code").getText();
        String departTime = element.element("Departure").element("Time").getText();
        String arriveCode = element.element("Arrival").element("Code").getText();
        String arriveTime = element.element("Arrival").element("Time").getText();
        Element firstSeat = element.element("Seating").element("FirstClass");
        String firstPrice = firstSeat.attribute("Price").getValue();
        String fPrice = firstPrice.substring(1).replace(",","");
        String firstLeft =  firstSeat.getText();
        Element coachSeat = element.element("Seating").element("Coach");
        String coachPrice = coachSeat.attribute("Price").getValue();
        String cPrice = coachPrice.substring(1).replace(",","");
        String coachLeft =  coachSeat.getText();
        //在此
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMMM dd HH:mm");
        Date departDate = null;
        Date arriveDate = null;
        try {
            departDate = sdf.parse(departTime);
            arriveDate = sdf.parse(arriveTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //将所有信息封装进flight对象
        flight.setPlaneType(type);
        flight.setFlightTime(Integer.parseInt(flightTime));
        flight.setNumber(num);
        flight.setDepartCode(departCode);
        flight.setDepartTime(departDate);
        flight.setArriveCode(arriveCode);
        flight.setArriveTime(arriveDate);
        flight.setFirstPrice(Double.parseDouble(fPrice));
        flight.setFirstLeft(Integer.parseInt(firstLeft));
        flight.setCoachPrice(Double.parseDouble(cPrice));
        flight.setCoachLeft(Integer.parseInt(coachLeft));
        return flight;
    }

    public static void main(String[] args) {
       String s = "-84.427764";
       String ss = s.substring(0,s.indexOf("."));
       int i = Integer.parseInt(ss);
        System.out.println(i);
    }
}
