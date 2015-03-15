package util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @ author pianobean
 * This class set the Connection to the given URL
 * And get the xml file as a String
 *
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
            //open the HttpURLConnection by given url.
            url = new URL(mUrlBase+condition);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            //Read the xml if request succeeded.
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

    //Test the connection
    public static void main(String[] args) {
        GregorianCalendar calendar = new GregorianCalendar(2015, 04, 9);
        Date date = calendar.getTime();
        XmlConnection connection = new XmlConnection();
        String data = connection.getXmlInfo(QueryFactory.getArriveAirplanes("BOS",date));
        System.out.println(data);
    }
}
