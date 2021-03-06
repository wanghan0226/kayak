package daoImpl;

import beans.SearchKey;
import dao.ConnectDao;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import util.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author pianobean
 */
public class ConnectDaoImpl implements ConnectDao {
    public List<SearchKey> airportSearchInfo(Document document, String airportType){
        List<SearchKey> list = new ArrayList<SearchKey>();
        Element root = document.getRootElement();
        for ( Iterator i = root.elementIterator( "Flight" ); i.hasNext(); ) {
            SearchKey key = new SearchKey();
            Element element = (Element) i.next();
            // do something
            String num = element.attribute("Number").getValue();
            String airportCode = element.element(airportType.equals(ConstantVariable.DEPART)?ConstantVariable.ARRIVE:ConstantVariable.DEPART).element("Code").getText();
            String arriveTime = element.element(airportType.equals(ConstantVariable.DEPART)?ConstantVariable.ARRIVE:ConstantVariable.DEPART).element("Time").getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm zzz");
            Date date = null;
            try {
                date = sdf.parse(arriveTime);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
            int firstLeft = Integer.parseInt(element.element("Seating").element("FirstClass").getText());
            int coachLeft = Integer.parseInt(element.element("Seating").element("Coach").getText());
            float firstPrice = PriceParser.floatParser(element.element("Seating").element("FirstClass").attributeValue("Price").substring(1));
            float coachPrice = PriceParser.floatParser(element.element("Seating").element("Coach").attributeValue("Price").substring(1));
            Date departureDate = DateFormater.formatTime(element.element("Departure").element("Time").getText());
            Date arrivalDate = DateFormater.formatTime(element.element("Arrival").element("Time").getText());
//            long timeInterval = arrivalDate.getTime() - departureDate.getTime();
            key.setAirportCode(airportCode);
            key.setDate(date);
            key.setFirstSeat(firstLeft);
            key.setCoachSeat(coachLeft);
            key.setNumber(num);
            key.setFirstPrice(firstPrice);
            key.setCoachPrice(coachPrice);
            key.setDepartureTime(departureDate.getTime());
            key.setArrivalTime(arrivalDate.getTime());
            list.add(key);
        }

        return list;
    }

    public static void main(String[] args) {
        ConnectDaoImpl connectFlights = new ConnectDaoImpl();
        GregorianCalendar calendar = new GregorianCalendar(2015, 04, 9);
        Date date = calendar.getTime();
        String s = XmlConnection.getXmlInfo(QueryFactory.getDepartAirplanes("BOS", date));
        Document document = null;
        try {
            document = DocumentHelper.parseText(s);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        List list = connectFlights.airportSearchInfo(document, ConstantVariable.DEPART);
        Iterator it = list.iterator();
        while(it.hasNext()){
            SearchKey key = (SearchKey) it.next();
            System.out.println(key.getAirportCode()+"  "+key.getNumber());
        }
    }
}
