package serviceImpl;

import dao.AirportDao;
import factory.DaoFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import service.AirportService;
import service.NonStopService;
import util.ConstantVariable;
import util.DateFormater;
import util.QueryFactory;
import util.XmlConnection;

import java.util.*;

/**
 * Created by pianobean on 3/8/15.
 */
public class AirportServiceImpl implements AirportService{
    public String generateName(String keyWords){
        StringBuilder sb = new StringBuilder();
        //use the factory design pattern to decoupling
        AirportDao info = DaoFactory.getInstance().getAirportFunctions();
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

    public Document getDepartDom(String code, Date date){
        String departFile = XmlConnection.getXmlInfo(QueryFactory.getDepartAirplanes(code, date));
        Document document = null;
        try {
            document = DocumentHelper.parseText(departFile);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return document;
    }

    public Document getArriveDom(String code, Date date){
        String departFile = XmlConnection.getXmlInfo(QueryFactory.getArriveAirplanes(code, date));
        Document document = null;
        try {
            document = DocumentHelper.parseText(departFile);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return document;
    }

    public static void main(String[] args) {
        String date = "5/11/2015";
        Date depart = DateFormater.format(date);
        AirportServiceImpl service = new AirportServiceImpl();
        Document dom = service.getDepartDom("BOS", depart);
        NonStopServiceImpl non = new NonStopServiceImpl();
        List list = non.findNonStopFlights(ConstantVariable.COACH, 1, "MIA", dom);
        System.out.println(list);
    }
}
