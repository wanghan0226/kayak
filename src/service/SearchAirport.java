package service;

import dao.AirportFunctions;
import daoImpl.AirportInfo;
import factory.DaoFactory;

import java.util.Iterator;
import java.util.Map;
/**
 * Created by pianobean on 3/8/15.
 */
public class SearchAirport {
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

}
