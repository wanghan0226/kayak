package util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by pianobean on 3/5/15.
 */
public class XmlConnection {
    private static final String mUrlBase = "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem";

    public static String getXmlInfo(String condition){
        URL url;
        HttpURLConnection connection;
        BufferedReader reader;
        String line;
        StringBuffer result = new StringBuffer();

        try{
            url = new URL(mUrlBase+condition);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if((responseCode>=200)&&(responseCode<=299)){
                InputStream inputStream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = reader.readLine())!=null){
                    result.append(line);
                }
                reader.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void main(String[] args) {
        GregorianCalendar calendar = new GregorianCalendar(2015, 04, 9);
        Date date = calendar.getTime();
        XmlConnection connection = new XmlConnection();
        String data = connection.getXmlInfo(QueryFactory.getArriveAirplanes("BOS",date));
        System.out.println(data);
    }
}
