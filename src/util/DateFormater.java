package util;

import service.PairFlights;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author  pianobean
 * This class help to format the input date to given format.
 */
public class DateFormater {
    public static Date format(String input){
        SimpleDateFormat format = new SimpleDateFormat("M/d/yyyy");
        Date date = null;
        try {
            date = format.parse(input);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public static Date formatTime(String input){
        SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd HH:mm zzz");
        Date date = null;
        try{
            date = format.parse(input);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }



    public static void main(String[] args) {
        String input = "2015 May 09 12:08 GMT";
        System.out.println(DateFormater.formatTime(input));
    }
}
