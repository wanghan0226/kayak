package serviceImpl;

import beans.Flight;

import dao.AirportDao;
import factory.DaoFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import service.NonStopService;
import util.ConstantVariable;

import java.util.*;

/**
 * Created by pianobean on 3/12/15.
 */
public class NonStopServiceImpl implements NonStopService{
    public List<Flight> findNonStopFlights(String seatType, int numOfPassenger, String arCode, Document document){
        boolean flag = true;
        if (seatType== ConstantVariable.FIRST) flag=false;
        AirportDao info = DaoFactory.getInstance().getAirportFunctions();
        List<Flight> list = new ArrayList<Flight>();
        List choices = document.selectNodes("//Arrival/Code");
        if(flag){//经济舱
            for (Iterator iter = choices.iterator(); iter.hasNext(); ) {
                Element element = (Element) iter.next();
                String code = element.getText();
                if(code.equals(arCode)){
                    Element flightElement = element.getParent().getParent();
                    int coachLeft = Integer.parseInt(flightElement.element("Seating").element("Coach").getText());
                    //判断经济舱是否有足够的位置
                    if(coachLeft>=numOfPassenger){
                        String num = flightElement.attribute("Number").getValue();
                        Flight flight = info.findFlightByNumber(num, document);
                        list.add(flight);
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
                    //判断经济舱是否有足够的位置
                    if(firstLeft>=numOfPassenger){
                        String num = flightElement.attribute("Number").getValue();
                        Flight flight = info.findFlightByNumber(num, document);
                        list.add(flight);
                    }
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
//        GregorianCalendar calendar = new GregorianCalendar(2015, 04, 9);
//        Date date = calendar.getTime();
//        String s = XmlConnection.getXmlInfo(QueryFactory.getDepartAirplanes("BOS", date));
//        Document document = null;
//        try {
//            document = DocumentHelper.parseText(s);
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//        List list = findNonStopFlights(ConstantVariable.FIRST,46,"ATL",document);
//        System.out.println(list);
    }
}
