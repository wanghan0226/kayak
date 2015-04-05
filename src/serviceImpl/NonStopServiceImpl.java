package serviceImpl;

import beans.*;

import dao.AirportDao;
import factory.DaoFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import service.NonStopService;
import util.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author pianobean on 3/12/15.
 */
public class NonStopServiceImpl implements NonStopService{
    @Override
    public List<Ticket> findNonStopFlights(String seatType, int numOfPassenger, String arCode, Document document){
        boolean flag = true;
        if (seatType== ConstantVariable.FIRST) flag=false;
        AirportDao info = DaoFactory.getInstance().getAirportFunctions();
        List<Ticket> result = new ArrayList<Ticket>();
        List choices = document.selectNodes("//Arrival/Code");
        if(flag){//经济舱
            for (Iterator iter = choices.iterator(); iter.hasNext(); ) {
                Element element = (Element) iter.next();
                String code = element.getText();
                if(code.equals(arCode)){
                    Element flightElement = element.getParent().getParent();
                    int coachLeft = Integer.parseInt(flightElement.element("Seating").element("Coach").getText());
                    float price = PriceParser.floatParser(flightElement.element("Seating").element("Coach").attributeValue("Price").substring(1));
                    Date departureTime = DateFormater.formatTime(flightElement.element("Departure").element("Time").getText());
                    Date arrivalTime = DateFormater.formatTime(flightElement.element("Arrival").element("Time").getText());
                    long timeInterval = arrivalTime.getTime() - departureTime.getTime();
                    //判断经济舱是否有足够的位置
                    if(coachLeft>=numOfPassenger){
                        String num = flightElement.attribute("Number").getValue();
                        Flight flight = info.findFlightByNumber(num, document);
                        List<Flight> flightList = new LinkedList<Flight>();
                        flightList.add(flight);
                        TicketContent content = new TicketContent(STOP_NUM.NON_STOP, flightList);
                        List<TicketContent> list = new ArrayList<TicketContent>();
                        list.add(content);
//                        Ticket ticket = new Ticket(FLIGHT_TYPE.SINGLE, list, price, departureTime.getTime(), arrivalTime.getTime(), timeInterval);
                        Ticket ticket = new Ticket(FLIGHT_TYPE.SINGLE, list, price, timeInterval);
                        result.add(ticket);
                    }
                }
            }
        }else {//头等舱
            for (Iterator iter = choices.iterator(); iter.hasNext(); ) {
                Element element = (Element) iter.next();
                String code = element.getText();
                if(code.equals(arCode)){
                    Element flightElement = element.getParent().getParent();
                    int firstLeft = Integer.parseInt(flightElement.element("Seating").element("FirstClass").getText());
                    float price = PriceParser.floatParser(flightElement.element("Seating").element("FirstClass").attributeValue("Price").substring(1));
                    Date departureTime = DateFormater.formatTime(flightElement.element("Departure").element("Time").getText());
                    Date arrivalTime = DateFormater.formatTime(flightElement.element("Arrival").element("Time").getText());
                    long timeInterval = arrivalTime.getTime() - departureTime.getTime();
                    //判断经济舱是否有足够的位置
                    if(firstLeft>=numOfPassenger){
                        String num = flightElement.attribute("Number").getValue();
                        Flight flight = info.findFlightByNumber(num, document);
                        List<Flight> flightList = new LinkedList<Flight>();
                        flightList.add(flight);
                        TicketContent content = new TicketContent(STOP_NUM.NON_STOP,flightList);
                        List<TicketContent> list = new ArrayList<TicketContent>();
                        list.add(content);
//                        Ticket ticket = new Ticket(FLIGHT_TYPE.SINGLE, list, price, departureTime.getTime(), arrivalTime.getTime(), timeInterval);
                        Ticket ticket = new Ticket(FLIGHT_TYPE.SINGLE, list, price, timeInterval);
                        result.add(ticket);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        GregorianCalendar calendar = new GregorianCalendar(2015, 04,12);
        Date date = calendar.getTime();
        String s = XmlConnection.getXmlInfo(QueryFactory.getDepartAirplanes("SFO", date));
        Document document = null;
        try {
            document = DocumentHelper.parseText(s);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        NonStopServiceImpl service = new NonStopServiceImpl();
        List<Ticket> list = service.findNonStopFlights(ConstantVariable.FIRST, 2, "BOS", document);
//        System.out.println(list);
        for(int i = 0; i < list.size(); i++){
            Ticket ticket = list.get(i);
            System.out.println(ticket.getPrice());
            System.out.println(ticket.getFlightType());
            System.out.println(ticket.getTicketContents().get(0).getFlights());
            System.out.println(ticket.getDuration());
//            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//
//            Date time = new Date(ticket.getTimeInterval());
//            String result = formatter.format(time);
//            System.out.println(result);
        }
    }
}
