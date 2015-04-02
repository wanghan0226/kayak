package serviceImpl;

import beans.FLIGHT_TYPE;
import beans.Flight;
import beans.Ticket;
import beans.TicketContent;
import factory.ServiceFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import service.OneStopService;
import service.PairFlights;
import util.ConstantVariable;
import util.QueryFactory;
import util.SortTicket;
import util.XmlConnection;

import java.util.*;

/**
 * Created by pianobean on 3/13/15.
 */
public class PairFlightsImpl implements PairFlights{
    @Override
    public List<Ticket> pairFlight(List<Ticket> goFlights, List<Ticket> comeFlights){
        List list = new ArrayList();
       for(Ticket go : goFlights){
           List<TicketContent> contentOne = go.getTicketContents();
           float goPrice = go.getPrice();
           long goTime = go.getDuration();
//           long goDepatureTime = go.getDepartureTime();
           for(Ticket come: comeFlights){
               List<TicketContent> contentTwo = come.getTicketContents();
               List<TicketContent> contents = new ArrayList<TicketContent>();
               contents.addAll(contentOne);
               contents.addAll(contentTwo);
               float price = goPrice + come.getPrice();
               long comeTime = come.getDuration();
               long time = goTime + comeTime;
//               long comeArrivalTime = come.getArrivalTime();
//               Ticket res = new Ticket(FLIGHT_TYPE.ROUND,contents,price, goDepatureTime,comeArrivalTime,time);
               Ticket res = new Ticket(FLIGHT_TYPE.ROUND,contents,price, time);
               list.add(res);
           }

       }
        return list;
    }

    public static void main(String[] args) {
        GregorianCalendar calendar3 = new GregorianCalendar(2015, 04,12);
        Date date3 = calendar3.getTime();
        String s3 = XmlConnection.getXmlInfo(QueryFactory.getDepartAirplanes("SFO", date3));
        Document document = null;
        try {
            document = DocumentHelper.parseText(s3);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        NonStopServiceImpl service = new NonStopServiceImpl();
        List<Ticket> list1 = service.findNonStopFlights(ConstantVariable.FIRST, 2, "BOS", document);


        GregorianCalendar calendar = new GregorianCalendar(2015, 04, 9);
        GregorianCalendar calendar1 = new GregorianCalendar(2015, 04, 9);
        GregorianCalendar calendar2 = new GregorianCalendar(2015, 04, 10);
        Date date = calendar.getTime();
        Date date1 = calendar1.getTime();
        Date date2 = calendar2.getTime();
        String s = XmlConnection.getXmlInfo(QueryFactory.getDepartAirplanes("BOS", date));
        String s1 = XmlConnection.getXmlInfo(QueryFactory.getArriveAirplanes("SLC", date1));
        String s2 = XmlConnection.getXmlInfo(QueryFactory.getArriveAirplanes("SLC", date2));
        Document departdoc = null;
        Document arrivedoc = null;
        Document arrivedoc1 = null;
        try {
            departdoc = DocumentHelper.parseText(s);
            arrivedoc = DocumentHelper.parseText(s1);
            arrivedoc1 = DocumentHelper.parseText(s2);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        OneStopService stopService = ServiceFactory.getInstance().getOneStopService();
        List<Ticket> list2 = stopService.validOneStop(ConstantVariable.COACH, 10, departdoc,arrivedoc,arrivedoc1);

        PairFlights pairFligt = new PairFlightsImpl();
        List<Ticket> list3 = pairFligt.pairFlight(list1, list2);

        for(int i = 0; i < list3.size(); i++){
            Ticket ticket = list3.get(i);
            System.out.println(ticket.getPrice());
            System.out.println(ticket.getFlightType());
//            System.out.println("go Flight ticket info are :" + ticket.getTicketContents().get(0).getFligts() + " " + ticket.getArrivalTime());
//            System.out.println("come Flight ticket info are :" + ticket.getTicketContents().get(1).getFligts() + " " + ticket.getArrivalTime());
        }

//        SortTicket.sortTicketByDurationAscending(list3);
//        SortTicket.sortTicketByPriceAscending(list3);
        SortTicket.sortTicketByPriceDescending(list3);
        System.out.println("------------------华丽分割线------------------------");
        for(int i = 0; i < list3.size(); i++){
            Ticket ticket = list3.get(i);
            System.out.println(ticket.getPrice());
            System.out.println(ticket.getFlightType());
            System.out.println(ticket.getDuration());
//            System.out.println("go Flight ticket info are :" + ticket.getTicketContents().get(0).getFligts() + " " + ticket.getArrivalTime());
//            System.out.println("come Flight ticket info are :" + ticket.getTicketContents().get(1).getFligts()+ " " + ticket.getArrivalTime());
        }
    }
}