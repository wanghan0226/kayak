package service;

import beans.SearchKey;
import dao.AirportFunctions;
import dao.Connect;
import daoImpl.ConnectFlights;
import factory.DaoFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import util.QueryFactory;
import util.XmlConnection;

import java.util.*;

/**
 * Created by pianobean on 3/8/15.
 */
public class SearchAirport {
    public static final String FIRST="first";
    public static final String COACH="coach";

    public static String generateName(String keyWords){
        StringBuilder sb = new StringBuilder();
        //use the factory design pattern to decoupling
        AirportFunctions info = DaoFactory.getInstance().getAirportFunctions();
        Map map = info.findCodesAndNames();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> entry = (Map.Entry) iterator.next();
            String key = entry.getKey();
            String compareKey = key.toLowerCase();
            String value = entry.getValue();
            String compareValue = value.toLowerCase();
            if(compareKey.contains(keyWords) || compareValue.contains(keyWords)) {
                String line = value + "(" + key + ")";
                sb.append(line + "|");
            }
        }

        if(sb.length()==0){
            return "";
        }else {
            String result = sb.substring(0, sb.length() - 1);
            return result;
        }
    }

    public static List<List<String>> findMatchFlights(String seatType, int numOfPassenger, Document depart, Document arrive, Document arrNextDay){
        boolean flag = true;
        if(seatType==SearchAirport.FIRST) flag=false;
        List<List<String>> list = new ArrayList<List<String>>();
        Connect connect = DaoFactory.getInstance().getConnectFlights();
        List<SearchKey> departKeys = connect.airportSearchInfo(depart, ConnectFlights.DEPART);
        List<SearchKey> arriveKeys = connect.airportSearchInfo(arrive, ConnectFlights.ARRIVE);
        List<SearchKey> nextDayKeys = connect.airportSearchInfo(arrNextDay,ConnectFlights.ARRIVE);
        if(flag){
            for(SearchKey key: departKeys){
                String code = key.getAirportCode();
                String number = key.getNumber();
                Date date = key.getDate();
                int coach = key.getCoachSeat();
                for(int i=0; i<arriveKeys.size(); i++){
                    SearchKey arrday = arriveKeys.get(i);
                    if(code.equals(arrday.getAirportCode()) && date.compareTo(arrday.getDate())<0 && coach>=numOfPassenger){
                        List<String> pair = new ArrayList();
                        pair.add(number);
                        pair.add(arrday.getNumber());
                        list.add(pair);
                    }
                }
            }
            for(SearchKey key: departKeys){
                String code = key.getAirportCode();
                String number = key.getNumber();
                Date date = key.getDate();
                int coach = key.getCoachSeat();
                for(int i=0; i<nextDayKeys.size(); i++){
                    SearchKey arrday = nextDayKeys.get(i);
                    if(code.equals(arrday.getAirportCode()) && date.compareTo(arrday.getDate())<0 && coach>=numOfPassenger){
                        List<String> pair = new ArrayList();
                        pair.add(number);
                        pair.add(arrday.getNumber());
                        list.add(pair);
                    }
                }
            }
        }else{
            for(SearchKey key: departKeys){
                String code = key.getAirportCode();
                String number = key.getNumber();
                Date date = key.getDate();
                int first = key.getFirstSeat();
                for(int i=0; i<arriveKeys.size(); i++){
                    SearchKey arrday = arriveKeys.get(i);
                    if(code.equals(arrday.getAirportCode()) && date.compareTo(arrday.getDate())<0 && first>=numOfPassenger){
                        List<String> pair = new ArrayList();
                        pair.add(number);
                        pair.add(arrday.getNumber());
                        list.add(pair);
                    }
                }
            }
            for(SearchKey key: departKeys){
                String code = key.getAirportCode();
                String number = key.getNumber();
                Date date = key.getDate();
                int first = key.getFirstSeat();
                for(int i=0; i<nextDayKeys.size(); i++){
                    SearchKey arrday = nextDayKeys.get(i);
                    if(code.equals(arrday.getAirportCode()) && date.compareTo(arrday.getDate())<0 && first>=numOfPassenger){
                        List<String> pair = new ArrayList();
                        pair.add(number);
                        pair.add(arrday.getNumber());
                        list.add(pair);
                    }
                }
            }
        }

        return list;
    }

    public static void main(String[] args) {
//        ConnectFlights connectFlights = new ConnectFlights();
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
       List list = findMatchFlights(SearchAirport.COACH, 2, departdoc,arrivedoc,arrivedoc1);
        System.out.println(list);
    }
}
