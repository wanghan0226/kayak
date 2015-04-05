package util;

import beans.Ticket;
import factory.ServiceFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import service.NonStopService;
import service.OneStopService;

import java.util.*;

/**
 * @author pianobean
 */
public class Pagination {
    private static final int SIZE = 10;
    public static List distinguish(List nonStop, List oneStop,int pageNum, String sortingType){
        //首先将两个list合并准备查找
        ArrayList all = new ArrayList(nonStop);
        all.addAll(oneStop);

        switch(sortingType) {
            case "price(low to high)":
                SortTicket.sortTicketByPriceAscending(all);
                break;
            case "price(high to low)":
                SortTicket.sortTicketByPriceDescending(all);
                break;
            default:
                SortTicket.sortTicketByPriceAscending(all);
        }
        return distiguishHelper(all, pageNum);

    }

    public static List distiguishHelper(ArrayList flights, int pageNum){
        List result = new ArrayList<>();
        int start = SIZE * (pageNum - 1) ;
        int end = ( SIZE * pageNum - 1) > (flights.size() - 1 )? (flights.size() - 1) : ( 10 * pageNum - 1);
        for(int i = start ; i <= end ; i ++){
            result.add(flights.get(i));
        }
        return result;
    }
//    public static void main(String[] args) {
//        System.out.println(String.class.getName());
//
//    }


    public static void main(String[] args){

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
        OneStopService oneStopService = ServiceFactory.getInstance().getOneStopService();
        List<Ticket> list1 = oneStopService.validOneStop(ConstantVariable.COACH, 10, departdoc, arrivedoc, arrivedoc1);


        NonStopService nonStopService = ServiceFactory.getInstance().getNonStopService();
        List<Ticket> list2 = nonStopService.findNonStopFlights(ConstantVariable.COACH, 10, "BOS", arrivedoc);


        Pagination p = new Pagination();
        List<Ticket> result = p.distinguish(list1,list2, 3, "price(high to low)");
        for(int i = 0; i < result.size(); i++){
            System.out.println(result.get(i).getPrice());
        }
    }
}
