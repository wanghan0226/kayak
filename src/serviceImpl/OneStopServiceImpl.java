package serviceImpl;

import beans.*;
import dao.AirportDao;
import dao.ConnectDao;
import factory.DaoFactory;
import factory.ServiceFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import service.OneStopService;
import util.ConstantVariable;
import util.QueryFactory;
import util.SortTicket;
import util.XmlConnection;

import java.net.URL;
import java.util.*;

/**
 * @author pianobean
 */
public class OneStopServiceImpl implements OneStopService{
    private static Document airports;
    static {
        SAXReader reader = new SAXReader();
        Class clazz = OneStopServiceImpl.class;
        URL url =clazz.getClassLoader().getResource("Xml/airports.xml");
        try {
            airports = reader.read(url);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    static class flightsWithInfo{
        List<String> flights;
        float price;
        long time;

        flightsWithInfo(List<String> flights, float price, long time){
            this.flights = flights;
            this.price = price;
            this.time = time;
        }

        float getPrice(){
            return price;
        }

        long getTime() { return time; }

        List<String> getFlights(){
            return flights;
        }
    }
    private static List<flightsWithInfo> findMatchFlights(String seatType, int numOfPassenger, Document depart, Document arrive, Document arrNextDay){
        boolean flag = true;
        if(seatType== ConstantVariable.FIRST) flag=false;
        List<flightsWithInfo> list = new ArrayList<flightsWithInfo>();
        ConnectDao connect = DaoFactory.getInstance().getConnectFlights();
        List<SearchKey> departKeys = connect.airportSearchInfo(depart, ConstantVariable.DEPART);
        List<SearchKey> arriveKeys = connect.airportSearchInfo(arrive, ConstantVariable.ARRIVE);
        List<SearchKey> nextDayKeys = connect.airportSearchInfo(arrNextDay, ConstantVariable.ARRIVE);
        if(flag){
            for(SearchKey key: departKeys){
                String code = key.getAirportCode();
                String number = key.getNumber();
                float priceOne = key.getCoachPrice();
                Date date = key.getDate();
                int coach = key.getCoachSeat();
                long timeStart = key.getDepartureTime();
                for(int i=0; i<arriveKeys.size(); i++){
                    SearchKey arrday = arriveKeys.get(i);
                    float priceTwo = arrday.getCoachPrice();
                    long timeEnd = key.getArrivalTime();
                    if(code.equals(arrday.getAirportCode()) && date.compareTo(arrday.getDate())<0 && coach>=numOfPassenger && arrday.getCoachSeat()>=numOfPassenger){
                        List<String> pair = new ArrayList();
                        pair.add(number);
                        pair.add(arrday.getNumber());
                        flightsWithInfo singleRes  = new flightsWithInfo(pair,priceOne + priceTwo, timeStart + timeEnd);
                        list.add(singleRes);
                    }
                }
            }
            for(SearchKey key: departKeys){
                String code = key.getAirportCode();
                String number = key.getNumber();
                Date date = key.getDate();
                int coach = key.getCoachSeat();
                float priceOne = key.getCoachPrice();
                long timeStart = key.getDepartureTime();
                for(int i=0; i<nextDayKeys.size(); i++){
                    SearchKey arrday = nextDayKeys.get(i);
                    float priceTwo = arrday.getCoachPrice();
                    long timeEnd = key.getArrivalTime();
                    if(code.equals(arrday.getAirportCode()) && date.compareTo(arrday.getDate())<0 && coach>=numOfPassenger && arrday.getCoachSeat()>=numOfPassenger){
                        List<String> pair = new ArrayList();
                        pair.add(number);
                        pair.add(arrday.getNumber());
                        flightsWithInfo singleRes  = new flightsWithInfo(pair,priceOne + priceTwo, timeStart + timeEnd);
                        list.add(singleRes);
                    }
                }
            }
        }else{
            for(SearchKey key: departKeys){
                String code = key.getAirportCode();
                String number = key.getNumber();
                Date date = key.getDate();
                int first = key.getFirstSeat();
                float priceOne = key.getFirstPrice();
                long timeStart = key.getDepartureTime();
                for(int i=0; i<arriveKeys.size(); i++){
                    SearchKey arrday = arriveKeys.get(i);
                    float priceTwo = arrday.getFirstPrice();
                    long timeEnd = key.getArrivalTime();
                    if(code.equals(arrday.getAirportCode()) && date.compareTo(arrday.getDate())<0 && first>=numOfPassenger && arrday.getFirstSeat()>=numOfPassenger){
                        List<String> pair = new ArrayList();
                        pair.add(number);
                        pair.add(arrday.getNumber());
                        flightsWithInfo singleRes  = new flightsWithInfo(pair,priceOne + priceTwo, timeStart + timeEnd);
                        list.add(singleRes);
                    }
                }
            }
            for(SearchKey key: departKeys){
                String code = key.getAirportCode();
                String number = key.getNumber();
                Date date = key.getDate();
                int first = key.getFirstSeat();
                float priceOne = key.getFirstPrice();
                long timeStart = key.getDepartureTime();
                for(int i=0; i<nextDayKeys.size(); i++){
                    SearchKey arrday = nextDayKeys.get(i);
                    float priceTwo = arrday.getFirstPrice();
                    long timeEnd = key.getArrivalTime();
                    if(code.equals(arrday.getAirportCode()) && date.compareTo(arrday.getDate())<0 && first>=numOfPassenger && arrday.getFirstSeat()>=numOfPassenger){
                        List<String> pair = new ArrayList();
                        pair.add(number);
                        pair.add(arrday.getNumber());
                        flightsWithInfo singleRes  = new flightsWithInfo(pair,priceOne + priceTwo, timeStart + timeEnd);
                        list.add(singleRes);
                    }
                }
            }
        }
        return list;
    }
    private static List<Ticket> findValidOneStopFlights(List<flightsWithInfo> choices, Document deDoc, Document arDoc, Document arNextDoc){
        List<Ticket> result = new ArrayList<Ticket>();
//        Iterator it = choices.iterator();
        AirportDao functions = DaoFactory.getInstance().getAirportFunctions();
        for (flightsWithInfo choice: choices){
                List<String> pair = choice.getFlights();
                float price = choice.getPrice();
                long time = choice.getTime();
                String deNum = pair.get(0);
            String arNum = pair.get(1);
            Flight departFlight = functions.findFlightByNumber(deNum, deDoc);
            Flight arriveFlight = null;
            try {
                arriveFlight = functions.findFlightByNumber(arNum,arDoc);
            }catch (Exception e){
                arriveFlight =functions.findFlightByNumber(arNum,arNextDoc);
            }
            String startCode = departFlight.getDepartCode();
            String endCode = arriveFlight.getArriveCode();
            long distance = distance(startCode, endCode);
            long firstHalf = distance(departFlight.getDepartCode(),departFlight.getArriveCode());
            long lastHalf = distance(arriveFlight.getDepartCode(),arriveFlight.getArriveCode());

            if((firstHalf+lastHalf)<distance*2){
//                System.out.println(distance+"---"+(firstHalf+lastHalf));
                List<Flight> validPair = new ArrayList<Flight>();
                validPair.add(departFlight);
                validPair.add(arriveFlight);
                TicketContent content = new TicketContent(STOP_NUM.ONE_STOP, validPair);
                List<TicketContent> contents = new ArrayList<TicketContent>();
                contents.add(content);
                Ticket ticket = new Ticket(FLIGHT_TYPE.SINGLE,contents,price,time);
                result.add(ticket);
            }
        }

        return result;
    }

    @Override
    public List<Ticket> validOneStop(String seatType, int numOfPassenger, Document depart, Document arrive, Document arrNextDay){
        List raw = findMatchFlights(seatType,numOfPassenger,depart,arrive,arrNextDay);
        List<Ticket> list = findValidOneStopFlights(raw,depart,arrive,arrNextDay);
        return list;
    }

    private static long distance(String departCode, String arriveCode){
        Element deAirport = (Element) airports.selectSingleNode("//Airport[@Code='"+departCode+"']");
        Element arAirport = (Element) airports.selectSingleNode("//Airport[@Code='"+arriveCode+"']");
        String deLatitude = deAirport.element("Latitude").getText();
        int y1 = round(deLatitude);
        String deLongitude = deAirport.element("Longitude").getText();
        int x1 = round(deLongitude);
        String arLatitude = arAirport.element("Latitude").getText();
        int y2 = round(arLatitude);
        String arLongitude = arAirport.element("Longitude").getText();
        int x2 = round(arLongitude);

        double distance = Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));
        long x = Math.round(distance);
        return x;
    }
    private static int round(String number){
        String ss = number.substring(0,number.indexOf("."));
        int i = Integer.parseInt(ss);
        return i;
    }
    public static void main(String[] args) {
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
        List<Ticket> list = stopService.validOneStop(ConstantVariable.COACH, 10, departdoc,arrivedoc,arrivedoc1);
        for(int i = 0; i < list.size(); i++){
            Ticket ticket = list.get(i);
            System.out.println(ticket.getPrice());
            System.out.println(ticket.getFlightType());
            System.out.println(ticket.getTicketContents().get(0).getFligts());
        }

        SortTicket.sortTicketByPriceAscending(list);
        System.out.println("------------------华丽分割线------------------------");
        for(int i = 0; i < list.size(); i++){
            Ticket ticket = list.get(i);
            System.out.println(ticket.getPrice());
            System.out.println(ticket.getFlightType());
            System.out.println(ticket.getTicketContents().get(0).getFligts());
            System.out.println(ticket.getTimeInterval());
        }
    }
}
