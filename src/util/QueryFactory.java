package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by pianobean on 3/5/15.
 */
public class QueryFactory {
    public static String getAirports(){
        return "?team=Team09&action=list&list_type=airports";
    }
    public static String getAirplanes(){return "?team=Team09&action=list&list_type=airplanes";}
    public static String getDepartAirplanes(String code, Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        String formatDate = sdf.format(date);
        return "?team=Team09&action=list&list_type=departing" +
                "&airport="+code+"&day="+formatDate+"";
    }
    public static String getArriveAirplanes(String code, Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        String formatDate = sdf.format(date);
        return "?team=Team09&action=list&list_type=arriving" +
                "&airport="+code+"&day="+formatDate+"";
    }
    public static void main(String[] args) {
        GregorianCalendar calendar = new GregorianCalendar(2015, 04, 07);
        Date date = calendar.getTime();
        String s = getDepartAirplanes("BOS",date);
        System.out.println(s);
    }

}
