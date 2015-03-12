package serviceImpl;

import dao.AirportDao;
import factory.DaoFactory;
import service.AirportService;

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
}
