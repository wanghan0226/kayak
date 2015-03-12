package daoImpl;

import beans.Flight;
import beans.SearchKey;
import dao.Connect;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import util.QueryFactory;
import util.XmlConnection;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pianobean on 3/11/15.
 */
public class ConnectFlights implements Connect {
    public static final String DEPART = "Departure";
    public static final String ARRIVE = "Arrival";
    public List<SearchKey> airportSearchInfo(Document document, String airportType){
        List<SearchKey> list = new ArrayList<SearchKey>();
        Element root = document.getRootElement();
        for ( Iterator i = root.elementIterator( "Flight" ); i.hasNext(); ) {
            SearchKey key = new SearchKey();
            Element element = (Element) i.next();
            // do something
            String num = element.attribute("Number").getValue();
            String airportCode = element.element(airportType.equals(DEPART)?ARRIVE:DEPART).element("Code").getText();
            String arriveTime = element.element(airportType.equals(DEPART)?ARRIVE:DEPART).element("Time").getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMMM dd HH:mm");
            Date date = null;
            try {
                date = sdf.parse(arriveTime);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
            int firstLeft = Integer.parseInt(element.element("Seating").element("FirstClass").getText());
            int coachLeft = Integer.parseInt(element.element("Seating").element("Coach").getText());
            key.setAirportCode(airportCode);
            key.setDate(date);
            key.setFirstSeat(firstLeft);
            key.setCoachSeat(coachLeft);
            key.setNumber(num);
            list.add(key);
        }

        return list;
    }

    public static void main(String[] args) {
        ConnectFlights connectFlights = new ConnectFlights();
        GregorianCalendar calendar = new GregorianCalendar(2015, 04, 9);
        Date date = calendar.getTime();
        String s = XmlConnection.getXmlInfo(QueryFactory.getDepartAirplanes("BOS", date));
        Document document = null;
        try {
            document = DocumentHelper.parseText(s);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        List list = connectFlights.airportSearchInfo(document, ConnectFlights.DEPART);
        Iterator it = list.iterator();
        while(it.hasNext()){
            SearchKey key = (SearchKey) it.next();
            System.out.println(key.getAirportCode()+"  "+key.getNumber());
        }
    }
}
